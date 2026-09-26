package com.felipysantsss.telegram_amara_bot.bot;

import com.felipysantsss.telegram_amara_bot.Utils.*;
import com.felipysantsss.telegram_amara_bot.enums.Messages;
import com.felipysantsss.telegram_amara_bot.enums.Plans;
import com.felipysantsss.telegram_amara_bot.enums.UserStatus;
import com.felipysantsss.telegram_amara_bot.enums.WelcomeImages;
import com.felipysantsss.telegram_amara_bot.model.User;
import com.felipysantsss.telegram_amara_bot.repository.UserRepository;
import com.felipysantsss.telegram_amara_bot.services.BucketR2Client;
import com.mercadopago.client.order.OrderClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class AmaraBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    // ----------------------------------------------------------

    // injeção do UserRepository e do r2 client

    private final BucketR2Client r2Client;
    private final UserRepository userRepository;


    public AmaraBot(BucketR2Client r2Client, UserRepository userRepository){
        this.r2Client = r2Client;
        this.userRepository = userRepository;
    }

    // ----------------------------------------------------------



    // pega o token do bot nas variaveis de ambiente
    @Value("${telegram.bot.token}")
    private String botToken;

    // cria o cliente do telegram que enviará as mensagens
    private TelegramClient telegramClient;

    String firstMessage = Messages.START_MESSAGE.getText();

    InlineKeyboardButton button7Dias = InlineKeyboardButton.builder()
            .text(Plans.INTERESSADO_PLAN.getText())
            .callbackData(Plans.INTERESSADO_PLAN.getCallBack())
            .build();

    InlineKeyboardButton button30DiasSafado = InlineKeyboardButton.builder()
            .text(Plans.SAFADO_PLAN.getText())
            .callbackData(Plans.SAFADO_PLAN.getCallBack())
            .build();

    InlineKeyboardButton button30DiasVip = InlineKeyboardButton.builder()
            .text(Plans.VIP_PLAN.getText())
            .callbackData(Plans.VIP_PLAN.getCallBack())
            .build();

    InlineKeyboardRow row1 = new InlineKeyboardRow(List.of(button7Dias));
    InlineKeyboardRow row2 = new InlineKeyboardRow(List.of(button30DiasSafado));
    InlineKeyboardRow row3 = new InlineKeyboardRow(List.of(button30DiasVip));

    List<InlineKeyboardRow> listButtons = new ArrayList(List.of(row1, row2, row3));

    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(listButtons);

    // Pega o token do bot
    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    // Recebe os updates(mensagens ou qualquer coisa que venha do user no telegram)
    @Override
    public void consume(Update update) {

        // verifica se tem mensagem de texto
        if (update.hasMessage() && update.getMessage().hasText()){
            // verifica se a mensagem é "/start"
            if ("/start".equals(update.getMessage().getText())){
                String chatId = update.getMessage().getChat().getId().toString();
                String userName = update.getMessage().getFrom().getFirstName();

                User newUser = new User();
                newUser.setChatId(chatId);
                newUser.setUserName(userName);
                newUser.setUserStatus(UserStatus.INACTIVE);


                if (userRepository.findByChatId(chatId).isEmpty()){
                    userRepository.save(newUser);
                    System.out.println("o usuario foi criado com éxito");
                } else {
                    System.out.println("o usuario já existe");
                }

                // Cria o objeto SendMessage que pega o Id do chat e a Mensagem que será enviada
                SendMessage messageInit = new SendMessage(chatId, firstMessage);
                // coloca os botões na mensagem
                messageInit.setReplyMarkup(keyboardMarkup);

                List<String> listImagesUrls = ImageGenerate.generateImages(r2Client, WelcomeImages.WELCOME_IMAGES.getImages());

                List<InputMedia> mediaList = StringToMediaConverter.convertStringToMediaList(listImagesUrls);

                try {
                    MediaSender.sender(chatId, mediaList, telegramClient);
                    // envia a mensagem com os botões
                    telegramClient.execute(messageInit);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        if (update.hasCallbackQuery()){
            String chatId = update.getCallbackQuery().getFrom().getId().toString();
            String alreadyHaveAPlan =
                    "Você já possui um plano ou está em processo de pagamento, querido!\n" +
                    "Cancele a escolha do plano abaixo (caso ainda não tenha pago) ou espere até a expiração do seu plano, para poder escolher outro.";

            InlineKeyboardButton cancelButton = InlineKeyboardButton.builder()
                    .text("Cancelar escolha de plano")
                    .callbackData("cancel_chosen_plain")
                    .build();

            InlineKeyboardRow row = new InlineKeyboardRow(List.of(cancelButton));

            InlineKeyboardMarkup markup = new InlineKeyboardMarkup(List.of(row));

            AnswerCallbackQuery response =  new AnswerCallbackQuery(update.getCallbackQuery().getId());
            try {
                telegramClient.execute(response);
            } catch (TelegramApiException e){
                System.out.println(e.getMessage());
            }

            if ("cancel_chosen_plain".equals(update.getCallbackQuery().getData())
                    && userRepository.findByChatId(chatId).get().getUserStatus().equals(UserStatus.WAITING_PAYMENT)){
                User client = userRepository.findByChatId(chatId).get();
                try {
                    new OrderClient().cancel(client.getOrderId());

                    client.setUserStatus(UserStatus.INACTIVE);
                    client.setOrderId(null);
                    client.setUserPlan(null);
                    userRepository.save(client);
                    MessageSender.MessageSender(chatId, "Pronto! Pode digitar: /start e escolher outro plano \uD83E\uDEE6", telegramClient);
                } catch (MPApiException e){
                    System.out.println("ERROR: " + e.getApiResponse().getContent());
                    MessageSender.MessageSender(chatId, "Não foi possível cancelar o seu pedido, meu bem! Se já foi pago, iremos disponibilizar o seu plano assim que verificarmos.", telegramClient);
                } catch (MPException e) {
                    System.out.println("ERROR: " + e.getMessage());
                    MessageSender.MessageSender(chatId, "Houve falha ao cancelar o seu pedido, meu amor! Tente novamente.", telegramClient);
                }
            }

            if (
                    userRepository.findByChatId(chatId).get().getUserStatus().equals(UserStatus.WAITING_PAYMENT) ||
                    userRepository.findByChatId(chatId).get().getUserStatus().equals(UserStatus.PROCESSING_PAYMENT) ||
                    userRepository.findByChatId(chatId).get().getUserStatus().equals(UserStatus.ACTIVE)
            ){
                try {
                    SendMessage message = new SendMessage(chatId, alreadyHaveAPlan);
                    message.setReplyMarkup(markup);

                    telegramClient.execute(message);

                }catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            } else {
                String chosenPlanCallBack = update.getCallbackQuery().getData();
                ChosenPlan.chosenPlan(chatId, telegramClient, chosenPlanCallBack, userRepository);
            }


        }
    }

    // transforma o cliente do telegram com base no token do bot
    @PostConstruct
    public void init(){
        telegramClient = new OkHttpTelegramClient(botToken);
    }
}

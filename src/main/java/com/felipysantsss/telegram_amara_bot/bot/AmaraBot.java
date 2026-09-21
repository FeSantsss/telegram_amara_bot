package com.felipysantsss.telegram_amara_bot.bot;

import com.felipysantsss.telegram_amara_bot.utils.PhotoSender;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.felipysantsss.telegram_amara_bot.texts.Messages;

import java.util.ArrayList;
import java.util.List;

@Component
public class AmaraBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    // pega o token do bot nas variaveis de ambiente
    @Value("${telegram.bot.token}")
    private String botToken;

    // cria o cliente do telegram que enviará as mensagens
    private TelegramClient telegramClient;

    String firstMessage = Messages.START_MESSAGE.getText();

    InlineKeyboardButton button7Dias = InlineKeyboardButton.builder()
            .text("\uD83C\uDF38 INTERESSADO Por R$10.90 (7 dias) - LINGERIE \uD83D\uDD1E")
            .callbackData("7dias_plan")
            .build();

    InlineKeyboardButton button30DiasSafado = InlineKeyboardButton.builder()
            .text("\uD83D\uDC8E SAFADO por R$ 24.90* (30 dias) + SURPRESINHA \uD83C\uDF81")
            .callbackData("30dias_safado_plan")
            .build();

    InlineKeyboardButton button30DiasVip = InlineKeyboardButton.builder()
            .text("\uD83D\uDC51 VIP+ por R$ 50.90 (45 dias) - VÍDEOS EXCLUSIVOS")
            .callbackData("30dias_vip_plan")
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
                // Cria o objeto SendMessage que pega o Id do chat e a Mensagem que será enviada
                SendMessage messageInit = new SendMessage(chatId, firstMessage);
                // coloca os botões na mensagem
                messageInit.setReplyMarkup(keyboardMarkup);
                try {
                    PhotoSender.sendImage(chatId, "https://pub-29c79b56b9f44c2a80b005bc022bef94.r2.dev/amara/amara-profile2.jpeg", telegramClient);
                    PhotoSender.sendImage(chatId, "https://pub-29c79b56b9f44c2a80b005bc022bef94.r2.dev/amara/amara-profile.jpeg", telegramClient);
                    PhotoSender.sendImage(chatId, "https://pub-29c79b56b9f44c2a80b005bc022bef94.r2.dev/amara/amara-banner.jpeg", telegramClient);
                    // envia a mensagem com os botões
                    telegramClient.execute(messageInit);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        if (update.hasCallbackQuery()){
            if ("7dias_plan".equals(update.getCallbackQuery().getData())){
                SendMessage message = new SendMessage(update.getCallbackQuery().getFrom().getId().toString(), "oi, teste gostoso");
                try {
                    telegramClient.execute(message);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
        }


    }

    // transforma o cliente do telegram com base no token do bot
    @PostConstruct
    public void init(){
        telegramClient = new OkHttpTelegramClient(botToken);
    }
}

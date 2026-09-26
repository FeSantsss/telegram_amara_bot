package com.felipysantsss.telegram_amara_bot.Utils;

import com.felipysantsss.telegram_amara_bot.enums.Messages;
import com.felipysantsss.telegram_amara_bot.enums.Plans;
import com.felipysantsss.telegram_amara_bot.enums.UserStatus;
import com.felipysantsss.telegram_amara_bot.model.User;
import com.felipysantsss.telegram_amara_bot.repository.UserRepository;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.order.Order;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class ChosenPlan {
    public static void chosenPlan(
            @NonNull String chatId,
            @NonNull TelegramClient telegramClient,
            @NonNull String chosenPlanCallBack,
            @NonNull UserRepository userRepository){
        String synthenticEmail = "user" + chatId + "@amara.bot";

        switch (chosenPlanCallBack){
            case "5days_plan" -> {
                Plans plan = Plans.INTERESSADO_PLAN;
                try{
                    Order order = CreatePayment.orderGenerator(plan, synthenticEmail, chatId);
                    User client = userRepository.findByChatId(chatId).get();
                    client.setUserStatus(UserStatus.WAITING_PAYMENT);
                    client.setOrderId(order.getId());
                    client.setUserPlan(plan);
                    userRepository.save(client);


                    String messageReplaced = Messages.CHOSING_PLAN.getText()
                                    .replace("{chosenPlan}", order.getDescription())
                                            .replace("{orderValue}", order.getCurrency() + order.getTotalAmount());


                    MessageSender.MessageSender(chatId, messageReplaced, telegramClient);
                    MessageSender.MessageSender(chatId, "Copia e cola:", telegramClient);
                    String copiaECola = order.getTransactions()
                                    .getPayments()
                                            .getFirst()
                                                    .getPaymentMethod()
                                                            .getQrCode();

                    MessageSender.MessageSender(chatId, copiaECola, telegramClient);

                    String qrCodeInBase64 = order
                            .getTransactions()
                            .getPayments()
                            .getFirst()
                            .getPaymentMethod()
                            .getQrCodeBase64();

                    InputFile qrCode = Base64ToQrCodeConverter.convertQrCode(qrCodeInBase64);
                    SendPhoto sendQrCode = SendPhoto.builder()
                            .chatId(chatId)
                            .photo(qrCode)
                            .build();

                    telegramClient.execute(sendQrCode);

                } catch (MPException | MPApiException e) {
                    throw new RuntimeException(e);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
            case "20days_safado_plan" -> {
                Plans plan = Plans.SAFADO_PLAN;
                try{
                    Order order = CreatePayment.orderGenerator(plan, synthenticEmail, chatId);
                    User client = userRepository.findByChatId(chatId).get();
                    client.setUserStatus(UserStatus.WAITING_PAYMENT);
                    client.setOrderId(order.getId());
                    client.setUserPlan(plan);
                    userRepository.save(client);

                    String messageReplaced = Messages.CHOSING_PLAN.getText()
                            .replace("{chosenPlan}", order.getDescription())
                            .replace("{orderValue}", order.getCurrency() + order.getTotalAmount());


                    MessageSender.MessageSender(chatId, messageReplaced, telegramClient);
                    MessageSender.MessageSender(chatId, "Copia e cola:", telegramClient);
                    String copiaECola = order.getTransactions()
                            .getPayments()
                            .getFirst()
                            .getPaymentMethod()
                            .getQrCode();

                    MessageSender.MessageSender(chatId, copiaECola, telegramClient);

                    String qrCodeInBase64 = order
                            .getTransactions()
                            .getPayments()
                            .getFirst()
                            .getPaymentMethod()
                            .getQrCodeBase64();

                    InputFile qrCode = Base64ToQrCodeConverter.convertQrCode(qrCodeInBase64);
                    SendPhoto sendQrCode = SendPhoto.builder()
                            .chatId(chatId)
                            .photo(qrCode)
                            .build();

                    telegramClient.execute(sendQrCode);

                } catch (MPException | MPApiException e) {
                    throw new RuntimeException(e);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
            case "30days_vip_plan" -> {
                Plans plan = Plans.VIP_PLAN;
                try{
                    Order order = CreatePayment.orderGenerator(plan, synthenticEmail, chatId);
                    User client = userRepository.findByChatId(chatId).get();
                    client.setUserStatus(UserStatus.WAITING_PAYMENT);
                    client.setOrderId(order.getId());
                    client.setUserPlan(plan);
                    userRepository.save(client);

                    String messageReplaced = Messages.CHOSING_PLAN.getText()
                            .replace("{chosenPlan}", order.getDescription())
                            .replace("{orderValue}", order.getCurrency() + order.getTotalAmount());


                    MessageSender.MessageSender(chatId, messageReplaced, telegramClient);
                    MessageSender.MessageSender(chatId, "Copia e cola:", telegramClient);
                    String copiaECola = order.getTransactions()
                            .getPayments()
                            .getFirst()
                            .getPaymentMethod()
                            .getQrCode();

                    MessageSender.MessageSender(chatId, copiaECola, telegramClient);

                    String qrCodeInBase64 = order
                            .getTransactions()
                            .getPayments()
                            .getFirst()
                            .getPaymentMethod()
                            .getQrCodeBase64();

                    InputFile qrCode = Base64ToQrCodeConverter.convertQrCode(qrCodeInBase64);
                    SendPhoto sendQrCode = SendPhoto.builder()
                            .chatId(chatId)
                            .photo(qrCode)
                            .build();

                    telegramClient.execute(sendQrCode);

                } catch (MPException | MPApiException e) {
                    throw new RuntimeException(e);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}

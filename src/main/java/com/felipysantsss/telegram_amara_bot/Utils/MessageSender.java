package com.felipysantsss.telegram_amara_bot.Utils;

import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class MessageSender {
    public static void MessageSender(@NonNull String chatId,@NonNull String text,@NonNull TelegramClient telegramClient){
        SendMessage sender = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        try {
            telegramClient.execute(sender);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}

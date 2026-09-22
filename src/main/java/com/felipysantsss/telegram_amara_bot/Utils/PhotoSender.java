package com.felipysantsss.telegram_amara_bot.Utils;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class PhotoSender {
    public static void sendImage(String chatId, String url, TelegramClient telegramClient){
        SendPhoto photoSender = SendPhoto
                .builder()
                .chatId(chatId)
                .photo(new InputFile(url))
                .build();

        try {
            telegramClient.execute(photoSender);
        } catch (TelegramApiException e){
            System.out.println(e.getMessage());
        }
    }
}

package com.felipysantsss.telegram_amara_bot.Utils;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

public class MediaSender {
    public static void sender(@NonNull String chatId,@NonNull List<InputMedia> medias,@NonNull TelegramClient telegramClient){
        SendMediaGroup mediaSender = SendMediaGroup
                .builder()
                .chatId(chatId)
                .medias(medias)
                .build();

        try {
            telegramClient.execute(mediaSender);
        } catch (TelegramApiException e){
            System.out.println(e.getMessage());
        }
    }
}

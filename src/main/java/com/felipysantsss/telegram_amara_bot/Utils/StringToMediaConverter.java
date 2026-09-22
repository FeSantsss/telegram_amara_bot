package com.felipysantsss.telegram_amara_bot.Utils;

import com.felipysantsss.telegram_amara_bot.exceptions.ImagesPathListIsEmptyException;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.util.ArrayList;
import java.util.List;

public class StringToMediaConverter {
    // vai receber as listas de urls(string) das imagens
    // vai transformar cada uma em um InputMediaPhoto e colocar e uma lista de InputMedia
    // e vai retornar essa lista
    public static List<InputMedia> convertStringToMediaList(@NonNull List<String> imagesUrls){
        if (imagesUrls.isEmpty()){
            throw new ImagesPathListIsEmptyException("This images urls list is empty!");
        }
        List<InputMedia> mediaList = new ArrayList<>();

        for (String imageUrl : imagesUrls) {
            mediaList.add(new InputMediaPhoto(imageUrl));
        }

        return mediaList;
    }
}

package com.felipysantsss.telegram_amara_bot.Utils;

import com.felipysantsss.telegram_amara_bot.exceptions.ImagePathIsEmptyException;
import com.felipysantsss.telegram_amara_bot.exceptions.ImagesPathListIsEmptyException;
import com.felipysantsss.telegram_amara_bot.services.BucketR2Client;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ImageGenerate {
    // vai receber bucketR2Client e uma lista de string dos caminhos das imagens no bucket ou apenas uma
    // ele vai pegar essas Strings ou essa e usar o generateUrl para colocar em uma lista ou apenas retornar a url da imagem
    public static List<String> generateImages(@NonNull BucketR2Client r2Client, @NonNull List<String> imagesPath){
        List<String> imagesUrls = new ArrayList<>();

        if(imagesPath.isEmpty()){
            throw new ImagesPathListIsEmptyException("This images paths list is empty!");
        }

        for (String imagePath : imagesPath) {
            imagesUrls.add(r2Client.generateUrl(imagePath));
        }

        return imagesUrls;
    }

    public static String generateImage(@NonNull BucketR2Client r2Client, @NonNull String imagePath){
        if(imagePath.isEmpty()){
            throw new ImagePathIsEmptyException("This image path is empty!");
        }

        return r2Client.generateUrl(imagePath);
    }

}

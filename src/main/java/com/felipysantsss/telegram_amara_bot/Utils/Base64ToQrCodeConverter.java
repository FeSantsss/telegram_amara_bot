package com.felipysantsss.telegram_amara_bot.Utils;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Base64ToQrCodeConverter {
    public static InputFile convertQrCode(@NonNull String base64){
        byte[] imageBytes = Base64.getDecoder().decode(base64);
        return new InputFile(new ByteArrayInputStream(imageBytes), "qrcode.png");
    }
}

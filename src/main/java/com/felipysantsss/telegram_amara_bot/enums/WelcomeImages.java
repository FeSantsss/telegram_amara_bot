package com.felipysantsss.telegram_amara_bot.enums;

import java.util.ArrayList;
import java.util.List;

public enum WelcomeImages {
    WELCOME_IMAGES(
            List.of("amara-photo3.jpeg",
                    "Screenshot_20260920_004231_Gallery.jpg",
                    "IMG_20260921_204802_352.jpg",
                    "IMG_20260921_204545_299.jpg"));

    List<String> images;

    WelcomeImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }
}

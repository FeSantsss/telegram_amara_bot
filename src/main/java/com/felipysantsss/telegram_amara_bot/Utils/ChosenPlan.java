package com.felipysantsss.telegram_amara_bot.Utils;

import com.felipysantsss.telegram_amara_bot.enums.Plans;
import com.felipysantsss.telegram_amara_bot.repository.UserRepository;
import lombok.NonNull;
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
            }
            case "20days_safado_plan" -> {
                Plans plan = Plans.SAFADO_PLAN;
            }
            case "30days_vip_plan" -> {
                Plans plan = Plans.VIP_PLAN;
            }
        }

    }
}

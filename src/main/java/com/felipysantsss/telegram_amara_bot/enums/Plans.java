package com.felipysantsss.telegram_amara_bot.enums;

import java.util.ArrayList;
import java.util.List;

public enum Plans {
    INTERESSADO_PLAN(
            "\uD83C\uDF38 INTERESSADO Por R$6.90 (5 dias) - LINGERIE \uD83D\uDD1E",
            "5days_plan",
            5,
            new ArrayList<>(
                    List.of("")
            )),

    SAFADO_PLAN(
            "\uD83D\uDC8E SAFADO por R$ 10.90* (20 dias) + SURPRESINHA \uD83C\uDF81",
            "20days_safado_plan",
            20,
            new ArrayList<>(
                    List.of("")
            )),

    VIP_PLAN(
            "\uD83D\uDC51 VIP+ por R$ 20.90 (30 dias) - VÍDEOS EXCLUSIVOS",
            "30days_vip_plan",
            30,
            new ArrayList<>(
                    List.of("")
            ));

    String text;
    String callBack;
    Integer daysToExpiration;
    List<String> medias;

    Plans(String text, String callBack, Integer daysToExpiration, List<String> medias) {
        this.text = text;
        this.callBack = callBack;
        this.daysToExpiration = daysToExpiration;
        this.medias = medias;
    }

    public String getText() {
        return text;
    }

    public String getCallBack() {
        return callBack;
    }

    public Integer getDaysToExpiration() {
        return daysToExpiration;
    }

    public List<String> getMedias() {
        return medias;
    }
}

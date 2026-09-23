package com.felipysantsss.telegram_amara_bot.enums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum Plans {
    INTERESSADO_PLAN(
            "\uD83C\uDF38 INTERESSADO Por R$6.90 (5 dias) - LINGERIE \uD83D\uDD1E",
            "5days_plan",
            5,
            -1004414302797L,
            new BigDecimal("6.90")),

    SAFADO_PLAN(
            "\uD83D\uDC8E SAFADO por R$ 10.90* (20 dias) + SURPRESINHA \uD83C\uDF81",
            "20days_safado_plan",
            20,
            -1004321327492L,
            new BigDecimal("10.90")),

    VIP_PLAN(
            "\uD83D\uDC51 VIP+ por R$ 20.90 (30 dias) - VÍDEOS EXCLUSIVOS",
            "30days_vip_plan",
            30,
            -1004434426417L,
            new BigDecimal("20.90"));

    String text;
    String callBack;
    Integer daysToExpiration;
    Long channelId;
    BigDecimal price;

    Plans(String text, String callBack, Integer daysToExpiration, Long channelId, BigDecimal price) {
        this.text = text;
        this.callBack = callBack;
        this.daysToExpiration = daysToExpiration;
        this.channelId = channelId;
        this.price = price;
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

    public Long getCanalId() {
        return channelId;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

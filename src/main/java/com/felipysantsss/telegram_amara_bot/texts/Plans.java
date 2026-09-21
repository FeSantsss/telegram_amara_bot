package com.felipysantsss.telegram_amara_bot.texts;

public enum Plans {
    INTERESSADO_PLAN("\uD83C\uDF38 INTERESSADO Por R$10.90 (7 dias) - LINGERIE \uD83D\uDD1E", "7days_plan"),
    SAFADO_PLAN("\uD83D\uDC8E SAFADO por R$ 24.90* (30 dias) + SURPRESINHA \uD83C\uDF81", "30days_safado_plan"),
    VIP_PLAN("\uD83D\uDC51 VIP+ por R$ 50.90 (45 dias) - VÍDEOS EXCLUSIVOS", "45days_vip_plan");

    String text;
    String callBack;

    Plans(String text, String callBack) {
        this.text = text;
        this.callBack = callBack;
    }

    public String getText() {
        return text;
    }

    public String getCallBack() {
        return callBack;
    }
}

package com.felipysantsss.telegram_amara_bot.enums;

public enum Messages {
    START_MESSAGE("Oii... cuidado pra não se perder aqui comigo \uD83D\uDE08\n" +
            "\n" +
            "Eu sou a Amara Eyess, 20 aninhos... \n" +
            "loira, branquinha, carinhosa e toda safada \uD83E\uDEE3\n" +
            "Pareço só uma menina doce e educada...\n" +
            "mas quando a gente fica sozinho eu viro a putinha que obedece tudo o que você mandar \uD83D\uDD25\n" +
            "\n" +
            "\uD83D\uDC40 olha o que te espera no meu VIP.\n" +
            "\n" +
            "❤\uFE0F\u200D\uD83D\uDD25 Fotos de lingerie sexy no INTERESSADO \n" +
            "❤\uFE0F\u200D\uD83D\uDD25 Fotinhas nuas no SAFADO\n" +
            "❤\uFE0F\u200D\uD83D\uDD25 Vídeos exclusivos só para o VIP\n" +
            "\n" +
            "Aqui dentro...\n" +
            "ninguém vê... ninguém ouve... e eu viro a puta tarada que você merece \uD83D\uDCA6\n" +
            "\n" +
            "Você aguenta entrar nesse jogo comigo?\n" +
            "\n" +
            "VEM PRO MEU CANTINHO \uD83D\uDE0F\uD83D\uDCA6\n"),

    CHOSING_PLAN("✨ Prontinho, meu bem! ✨\n" +
            "\n" +
            "Você escolheu o plano: {chosenPlan}.\n" +
            "Valor: {orderValue}\n" +
            "\n" +
            "Agora é só finalizar o pagamento pelo QR Code abaixo ou usar o código copia e cola:\n" +
            "\n" +
            "Assim que o pagamento for confirmado, seu plano será liberado para você. \uD83D\uDDA4\n" +
            "\n" +
            "Tô te esperando… \uD83D\uDE0F\uD83D\uDC8B"),

    RECUSED_PAYMENT("Ops, meu amor… \uD83E\uDD7A\uD83D\uDDA4\n" +
            "Seu pagamento foi cancelado ou recusado, então a assinatura não foi ativada.\n" +
            "Se ainda quiser o seu plano, é só tentar novamente. ✨\n" +
            "Plano: {chosenPlan}\n" +
            "Quando estiver tudo certinho, eu vou estar te esperando por aqui. \uD83D\uDC8B"),


    EXPIRED_PAYMENT("Ei, meu amor… seu pagamento acabou expirando. ⏰\uD83D\uDDA4\n" +
            "Isso significa que o prazo para concluir a compra terminou e o plano não foi ativado.\n" +
            "Mas fica tranquila… se você ainda quiser, é só fazer uma nova tentativa. \uD83D\uDE09✨\n" +
            "Vou estar aqui te esperando. \uD83D\uDC8B"),

    SUCESSED_BUY("Obrigada pela sua compra, meu amor. \uD83D\uDDA4\n" +
            "Fico muito feliz em ter você por aqui. Espero que você aproveite cada detalhe do seu plano… e que volte sempre. \uD83D\uDE0F✨\n" +
            "Um beijo da Amara. \uD83D\uDC8B"),

    EXPIRED_PLAN("Hmm… seu plano chegou ao fim, meu amor. \uD83D\uDDA4\n" +
            "Mas não precisa ficar com saudade de mim… \uD83D\uDE0F\n" +
            "Se quiser continuar comigo e ter acesso novamente, é só assinar um novo plano.\n" +
            "Vem renovar sua assinatura e ficar pertinho de mim mais uma vez. \uD83D\uDC8B✨\n" +
            "Quero você por aqui novamente.");

    private String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

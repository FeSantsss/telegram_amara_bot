package com.felipysantsss.telegram_amara_bot.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class AmaraBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    @Value("${telegram.bot.token}")
    private String botToken;
    private TelegramClient telegramClient;
    String firstMessage = "Oii... cuidado pra não se perder aqui comigo \uD83D\uDE08\n" +
            "\n" +
            "Eu sou a Amara Eyess, 20 aninhos... \n" +
            "loira, branquinha, carinhosa e toda safada \uD83E\uDEE3\n" +
            "Pareço só uma menina doce e educada...\n" +
            "mas quando a gente fica sozinho eu viro a putinha que obedece tudo o que você mandar \uD83D\uDD25\n" +
            "\n" +
            "\uD83D\uDC40 olha o que te espera no meu VIP.\n" +
            "\n" +
            "❤\uFE0F\u200D\uD83D\uDD25 Fotos de lingerie sexy \n" +
            "❤\uFE0F\u200D\uD83D\uDD25Fotinhas nuas no SAFADO+\n" +
            "❤\uFE0F\u200D\uD83D\uDD25 Vídeos exclusivos só pro VIP\n" +
            "\n" +
            "Aqui dentro...\n" +
            "ninguém vê... ninguém ouve... e eu viro a puta tarada que você merece \uD83D\uDCA6\n" +
            "\n" +
            "Você aguenta entrar nesse jogo comigo?\n" +
            "\n" +
            "VEM PRO MEU CANTINHO \uD83D\uDE0F\uD83D\uDCA6\n" +
            "\n" +
            "*\uD83C\uDF38 7 dias- FOTOS SEXY DE LINGERIE \uD83D\uDD1E por R$ 10.90*\n" +
            "\n" +
            "*\uD83D\uDC8E SAFADO 30 dias + SURPRESINHA \uD83C\uDF81 por R$ 24.90*\n" +
            "\n" +
            "*\uD83D\uDC51 VIP+ VÍDEOS EXCLUSIVOS por R$ 50.90*";

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()){
            if ("/start".equals(update.getMessage().getText())){
                SendMessage messageInit = new SendMessage(update.getMessage().getChat().getId().toString(), firstMessage);
                try {
                    telegramClient.execute(messageInit);
                } catch (TelegramApiException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @PostConstruct
    public void init(){
        telegramClient = new OkHttpTelegramClient(botToken);
    }
}

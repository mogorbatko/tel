package com.gorbatko.tel.service;

import com.gorbatko.tel.bot.TavridaElectricTelegramBot;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private static Logger log = Logger.getLogger(SendBotMessageServiceImpl.class.getName());
    private final TavridaElectricTelegramBot tavridaElectricTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(TavridaElectricTelegramBot tavridaElectricTelegramBot) {
        log.info("Loading constructor");
        this.tavridaElectricTelegramBot = tavridaElectricTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        log.info("Sending message");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try { 
            tavridaElectricTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

package com.gorbatko.tel.command;

import com.gorbatko.tel.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет! Я Tavrida Electric Telegram Bot. " +
            "Я помогу тебе быть в курсе последных событий в мире электроэнергетики, отыскать документацию по продуктам TEL" +
            " , а так же организовать консультацию со специалистами поддержки Таврида Электрик.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);

//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("Test button");
//        keyboard.add(row);
//        replyKeyboardMarkup.setKeyboard(keyboard);


    }
}

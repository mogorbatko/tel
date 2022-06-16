package com.gorbatko.tel.bot;

import com.gorbatko.tel.command.CommandContainer;
import com.gorbatko.tel.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

import static com.gorbatko.tel.command.CommandName.NO;

@Component
public class TavridaElectricTelegramBot extends TelegramLongPollingBot {

    private static Logger log = Logger.getLogger(TavridaElectricTelegramBot.class.getName());

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public TavridaElectricTelegramBot() {
        log.info("Loading constructor");
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    @Override
    public String getBotUsername() {
        log.info("Getting BotUsername from application properties");
        return username;
    }

    @Override
    public String getBotToken() {
        log.info("Getting token from application properties");
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Getting message from: ChatId = " + update.getMessage().getChatId() + ", userName = "
                + update.getMessage().getChat().getUserName() + ", text = " + update.getMessage().getText());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }

    }
}

package com.gorbatko.tel.command;

import com.gorbatko.tel.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
@Component
public class ListCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    @Value("#{'${list}'.split(',')}")
    private List<String> list;

    public String getListMessage(List<String> list) {
        String result = list.get(0);
        for (String string :
                list) {
            result = result + "\n" + string;
        }
        return result;
    }

    public String LIST_MESSAGE = getListMessage(list);

    public ListCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), LIST_MESSAGE);

    }
}

package com.gorbatko.tel.command;

import com.gorbatko.tel.beans.ListOfLinks;
import com.gorbatko.tel.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;

public class ListCommand implements Command {

    private static Logger log = Logger.getLogger(ListCommand.class.getName());

    private final SendBotMessageService sendBotMessageService;

    public ListOfLinks listOfLinks;
    public List<String> list = listOfLinks.getList();
    public String listMessage = getListMessage(list);

//    public void setListMessage() {
//        this.list = listOfLinks.getList();
//    }
    public String getListMessage(List<String> list) {
        String result = list.get(0);
        for (String string :
                list) {
            result = result + "\n" + string;
        }
        return result;
    }

    public ListCommand(SendBotMessageService sendBotMessageService) {
        log.info("Loading constructor");
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), listMessage);

    }
}

package com.gorbatko.tel.command;

import com.google.common.collect.ImmutableMap;
import com.gorbatko.tel.service.SendBotMessageService;

import java.util.logging.Logger;

import static com.gorbatko.tel.command.CommandName.*;

public class CommandContainer {
    private static Logger log = Logger.getLogger(CommandContainer.class.getName());
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        log.info("Loading constructor");

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(LIST.getCommandName(), new ListCommand(sendBotMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        log.info("Retrieve command: " + commandMap.getOrDefault(commandIdentifier, unknownCommand));
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}

package com.gorbatko.tel.service;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
}

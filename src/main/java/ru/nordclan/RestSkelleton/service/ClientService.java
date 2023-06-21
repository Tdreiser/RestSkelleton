package ru.nordclan.RestSkelleton.service;

import ru.nordclan.RestSkelleton.entity.Client;
import ru.nordclan.RestSkelleton.entity.Message;

import java.util.List;
import java.util.Optional;

/**
 * @author Shlokov Andrey
 */
public interface ClientService {
    List<String> readAllNamesByPrefix (String prefix);
    Optional<Client> read(Integer id);
    List<Client> read();
    void create(Client client);
    boolean delete(Integer id);
    boolean update(Client client, Integer id);
    List<Message> readClientMessages(Integer id);
    Message readClientMessage(Integer clientId, Integer messageId);
    boolean writeMessage(String text, int id);
    void sendMessage(String topicName, String message);

}

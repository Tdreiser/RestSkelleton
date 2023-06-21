package ru.nordclan.RestSkelleton.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.nordclan.RestSkelleton.entity.Client;
import ru.nordclan.RestSkelleton.entity.Message;
import ru.nordclan.RestSkelleton.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Shlokov Andrey
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String topicName,String msg) {
        kafkaTemplate.send(topicName, msg);
    }

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<String> readAllNamesByPrefix(String prefix) {
        return clientRepository.findByClientNameStartsWith(prefix);
    }

    @Override
    public Optional<Client> read(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> read() {
        return clientRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Client client, Integer id) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public List<Message> readClientMessages(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(Client::getMessages).orElse(null);
    }

    @Override
    public Message readClientMessage(Integer clientId, Integer messageId) {
        Optional<Client> client = clientRepository.findById(clientId);
        return client.flatMap(cl -> cl
                .getMessages()
                .stream()
                .filter(m -> m.getId().equals(clientId))
                .findAny())
                .orElse(null);
    }


    @Override
    public boolean writeMessage(String text, int id) {
        Optional<Client> optClient = read(id);
        if (optClient.isPresent()) {
            Client client = optClient.get();
            Message message = new Message();
            message.setMessage(text);
            client.getMessages().add(message);
            update(client, id);
            return true;
        }
        return false;
    }
}

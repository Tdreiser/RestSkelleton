package ru.nordclan.RestSkelleton.controller;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nordclan.RestSkelleton.entity.Client;
import ru.nordclan.RestSkelleton.entity.Message;
import ru.nordclan.RestSkelleton.service.ClientService;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Shlokov Andrey
 */
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private KafkaConsumer<String, String> consumer;


    @PostMapping
    ResponseEntity<HttpStatus> create(@RequestBody Client client) {
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(params = "id")
    ResponseEntity<Client> readById(@RequestParam Integer id) {

        final Optional<Client> client = clientService.read(id);
        return client.isPresent()
                ? new ResponseEntity<>(client.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "prefix")
    ResponseEntity<List<String>> readByPrefix(@RequestParam String prefix) {
        return new ResponseEntity<>(clientService.readAllNamesByPrefix(prefix), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Client>> read() {
        List<Client> clients = clientService.read();
        clientService.sendMessage("quickstart", "users requested = " + clients.size());

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    ResponseEntity<HttpStatus> update(@PathVariable Integer id, @RequestBody Client client) {

        return new ResponseEntity<>(clientService.update(client, id)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Integer id) {

        return new ResponseEntity<>(clientService.delete(id)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}/messages")
    ResponseEntity<List<Message>> readMessages(@PathVariable(name = "id") Integer id) {

        consumer.subscribe(Collections.singletonList("quickstart"));

        ConsumerRecords<String, String> records =
                consumer.poll(Duration.ofMillis(100));
        if (!records.isEmpty()){
            System.out.println("---------------------new messages begin---------------");
            records.forEach(r-> System.out.println(r.value()));
            System.out.println("---------------------new messages end-----------------");
        }
        List<Message> messages = clientService.readClientMessages(id);
        return messages != null
                ? new ResponseEntity<>(messages, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}/messages/{message_id}")
    ResponseEntity<Message> readMessage(@PathVariable(name = "id") Integer id,
                                        @PathVariable(name = "message_id") Integer messageId) {
        Message message = clientService.readClientMessage(id, messageId);

        return message != null
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{id}/messages")
    ResponseEntity<HttpStatus> writeMessage(@PathVariable(name = "id") Integer id, @RequestBody String message) {

        return new ResponseEntity<>(clientService.writeMessage(message, id)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);

    }
}

package ru.nordclan.RestSkelleton.component;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
/**
 * @author Shlokov Andrey
 */
@Component
public class Listener {
    @KafkaListener(topics = "quickstart",groupId = "foo")
    public void listen(String data) {
        System.out.println(data);
    }
}

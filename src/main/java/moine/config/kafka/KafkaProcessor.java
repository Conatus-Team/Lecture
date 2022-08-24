package moine.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {


//    String INPUT = "authTopic";
//    String OUTPUT = "lectureTopic";

    String INPUT = "event-in";
    String OUTPUT = "event-out";

    @Input(INPUT)
    SubscribableChannel inboundTopic();

//    @Input(INPUT2)
//    SubscribableChannel inboundTopic2();

    @Output(OUTPUT)
    MessageChannel outboundTopic();


}

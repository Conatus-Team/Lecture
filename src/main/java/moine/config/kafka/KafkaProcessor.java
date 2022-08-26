package moine.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {


//    String INPUT = "authTopic";
//    String INPUT2 = "recommendTopic";
//    String OUTPUT = "lectureTopic";
    String INPUT = "myTopic";
    String OUTPUT = "myTopic";

    @Input(INPUT)
    SubscribableChannel inboundTopic();

//    @Input(INPUT2)
//    SubscribableChannel inboundTopic2();

    @Output(OUTPUT)
    MessageChannel outboundTopic();


}

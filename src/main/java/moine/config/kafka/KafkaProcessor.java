package moine.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {


    String INPUT = "signedUpTopic";
    String OUTPUT = "detailShownTopic";
    String OUTPUT2 = "likedTopic";

    @Input(INPUT)
    SubscribableChannel inboundTopic();

    @Output(OUTPUT)
    MessageChannel outboundTopic();

//    @Output(OUTPUT2)
//    MessageChannel outboundTopic2();
}

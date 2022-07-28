package moine.infra;

import moine.config.kafka.KafkaProcessor;
import moine.domain.entity.Lecture;
import moine.domain.event.LetureRecommended;
import moine.domain.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class PolicyHandler{
    @Autowired
    LectureRepository lectureRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLetureRecommended_UpdateRecommendedLecture(@Payload LetureRecommended letureRecommended){

        System.out.println("안녕");

        if(!letureRecommended.validate()) return;
        LetureRecommended event = letureRecommended;
        System.out.println("\n\n##### listener UpdateRecommendedLecture : " + letureRecommended.toJson() + "\n\n");


        

        // Sample Logic //
        System.out.println("크크크ㅡㅡㅡㅋ....");

        Lecture.updateRecommendedLecture(event);
        

        

    }


}



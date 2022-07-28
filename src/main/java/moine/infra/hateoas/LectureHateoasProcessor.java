package moine.infra.hateoas;

import moine.domain.entity.Lecture;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class LectureHateoasProcessor implements RepresentationModelProcessor<EntityModel<Lecture>>  {

    @Override
    public EntityModel<Lecture> process(EntityModel<Lecture> model) {
        
        return model;
    }
    
}


package moine.infra;
import moine.domain.*;

import org.springframework.hateoas.Link;
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


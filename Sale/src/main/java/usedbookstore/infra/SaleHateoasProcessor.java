package usedbookstore.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import usedbookstore.domain.*;

@Component
public class SaleHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Sale>> {

    @Override
    public EntityModel<Sale> process(EntityModel<Sale> model) {
        return model;
    }
}

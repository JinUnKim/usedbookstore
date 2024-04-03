package usedbookstore.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import usedbookstore.domain.*;

@Component
public class PaymentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Payment>> {

    @Override
    public EntityModel<Payment> process(EntityModel<Payment> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/pay")
                .withRel("pay")
        );

        return model;
    }
}

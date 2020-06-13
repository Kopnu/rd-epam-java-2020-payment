package rd.epam.java.payment.resource;

import rd.epam.java.payment.dto.PaymentDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Payment resource.
 *
 * @author Dmitrii_Lopatin
 */
@RequestMapping(value = "/payment")
public interface PaymentResource {

    @PostMapping("/")
    UUID postResponse(@Valid @RequestBody PaymentDto paymentDto);

}

package rd.epam.java.payment.converter;

import rd.epam.java.payment.domain.entity.Accept;
import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.dto.PaymentDto;
import rd.epam.java.payment.service.impl.AcceptServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Converter for {@link Payment}.
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
public class PaymentConverter {

    @Autowired
    private AcceptServiceImpl acceptService;

    public Payment convertToPayment(PaymentDto paymentDto) {
        log.info("convertToPayment() - DTO = {}", paymentDto);
        Payment payment = new Payment();
        if (paymentDto.getAcceptId() != null) {
            Accept accept = acceptService.find(paymentDto.getAcceptId());
            payment = accept.getPaymentPrivateId();
            payment.setKey(paymentDto.getKey());
        }
        log.info("convertToPayment() - payment = {}", payment);
        return payment;
    }
}

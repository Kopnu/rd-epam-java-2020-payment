package rd.epam.java.payment.resource.impl;

import rd.epam.java.payment.converter.PaymentConverter;
import rd.epam.java.payment.domain.entity.Accept;
import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.dto.PaymentDto;
import rd.epam.java.payment.resource.PaymentResource;
import rd.epam.java.payment.service.impl.AcceptServiceImpl;
import rd.epam.java.payment.service.impl.Md5ServiceImpl;
import rd.epam.java.payment.service.impl.PaymentServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * {@link PaymentResource} implementation.
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentResourceImpl implements PaymentResource {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private AcceptServiceImpl acceptService;

    @Autowired
    private Md5ServiceImpl md5Service;

    @Autowired
    private PaymentConverter paymentConverter;

    @Override
    public UUID postResponse(PaymentDto paymentDto) {
        log.info("postResponse() - request = {}", paymentDto);

        md5Service.validateAccountMd5(paymentDto.getReceiverAccount().getAccountId(), paymentDto.getKey());

        Payment payment = paymentConverter.convertToPayment(paymentDto);
        Accept accept = acceptService.find(paymentDto.getAcceptId());
       // accept.setAcceptStatus("Принят в обработку");
        acceptService.update(accept);

        log.info("postResponse() - response = {}", payment.getPaymentPublicId());
        return payment.getPaymentPublicId();
    }
}

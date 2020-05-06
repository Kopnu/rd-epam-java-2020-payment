package rd.epam.java.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.repository.PaymentRepository;
import rd.epam.java.payment.service.PaymentService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link PaymentService} implementation.
 *
 * @author Sergei_Kornilov
 */
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        if (Objects.isNull(payment)) {
            log.error("createPayment() - error: payment is null");
            return null;
        }
        log.debug("createPayment() - create payment {}", payment);
        payment.setPaymentPublicId(UUID.randomUUID());
        paymentRepository.save(payment);
        return paymentRepository.findById(payment.getPaymentPrivateId()).get();
    }

    @Override
    public Payment find(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("find() - error: uuid is null");
            return null;
        }
        log.debug("find() - find payment by uuid {}", uuid);
        return paymentRepository.findById(uuid).get();
    }

    @Override
    public List<Payment> findAll(List<UUID> ids) {
        if (Objects.isNull(ids)) {
            log.error("findAll() - error: ids is null");
            return Collections.emptyList();
        }
        log.debug("findAll() - find list of payments by id list {}", ids);
        return paymentRepository.findByIdList(ids);
    }

    @Override
    public Payment update(Payment payment) {
        if (Objects.isNull(payment)) {
            log.error("update() - error: payment is null");
            return null;
        }
        log.debug("update() - update payment {}", payment);
        return paymentRepository.update(payment).get();
    }

    @Override
    public boolean delete(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("delete() - error: uuid is null");
            return false;
        }
        log.debug("delete() - delete payment by id {}", uuid);
        paymentRepository.delete(uuid);
        return true;
    }
}


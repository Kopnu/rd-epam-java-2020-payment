package rd.epam.java.payment.service.impl;

import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.service.PaymentService;

import java.util.List;
import java.util.UUID;

/**
 * {@link PaymentService} implementation.
 *
 * @author Sergei_Kornilov
 */
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment createPayment(Payment payment) {
        return null;
    }

    @Override
    public Payment find(UUID uuid) {
        return null;
    }

    @Override
    public List<Payment> findAll(List<UUID> ids) {
        return null;
    }

    @Override
    public Payment update(Payment payment) {
        return null;
    }

    @Override
    public boolean delete(UUID uuid) {
        return false;
    }
}

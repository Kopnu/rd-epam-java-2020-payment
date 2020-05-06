package rd.epam.java.payment.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * PaymentServiceImplTest.
 *
 * @author Dmitrii_Lopatin
 */
public class PaymentServiceImplTest {

    private final static Payment VALID_PAYMENT = getPayment();
    private final static UUID VALID_UUID = UUID.randomUUID();
    private final static List<UUID> VALID_UUID_LIST = getValidUuidList();
    private final static UUID INVALID_UUID = UUID.randomUUID();

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Payment payment = getPayment();
        when(paymentService.find(any(UUID.class))).thenReturn(payment);
    }

    @Test
    public void testValidCreatePayment() {
        Payment result = paymentService.createPayment(VALID_PAYMENT);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testValidFind() {
        Payment result = paymentService.find(VALID_UUID);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testInvalidFind() {
        Payment result = paymentService.find(INVALID_UUID);
        assertNotEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testValidFindAll() {
        List<Payment> result = paymentService.findAll(VALID_UUID_LIST);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testValidUpdate() {
        Payment result = paymentService.update(VALID_PAYMENT);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testTrueDelete() {
        Boolean result = paymentService.delete(VALID_UUID);
        assertTrue(result);
    }

    public void testFalseDelete() {
        Boolean result = paymentService.delete(null);
        assertFalse(result);
    }

    private static List<UUID> getValidUuidList() {
        List<UUID> uuids = new ArrayList<>();
        uuids.add(UUID.randomUUID());
        return uuids;
    }

    private static Payment getPayment() {
        Payment payment = new Payment();
        Client client = new Client();
        payment.setPaymentPrivateId(VALID_UUID);
        payment.setKey("8bf69634c58b235978448447e594a6ae");
        payment.setAmount((double) 1000);
        payment.setClientId(client);
        return payment;
    }

}

package rd.epam.java.payment.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.repository.PaymentRepository;
import rd.epam.java.payment.service.PaymentService;

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
    private final Payment VALID_PAYMENT = getPayment();
    private final UUID VALID_UUID = UUID.randomUUID();
    private final List<UUID> VALID_UUID_LIST = getValidUuidList();
    private final UUID INVALID_UUID = UUID.randomUUID();

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Payment payment = getPayment();
        when(paymentService.find(any(UUID.class))).thenReturn(payment);
    }

    @Test
    public void testValidCreatePayment() {
        Payment result = paymentServiceImpl.createPayment(VALID_PAYMENT);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testValidFind() {
        Payment result = paymentServiceImpl.find(VALID_UUID);
        assertEquals(VALID_PAYMENT, result);
    }


    @Test
    public void testValidFindAll() {
        List<Payment> result = paymentServiceImpl.findAll(VALID_UUID_LIST);
        List<Payment> validList = new ArrayList<>();
        validList.add(VALID_PAYMENT);
        assertEquals(validList, result);
    }

    @Test
    public void testValidUpdate() {
        Payment result = paymentServiceImpl.update(VALID_PAYMENT);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test
    public void testTrueDelete() {
        Boolean result = paymentServiceImpl.delete(VALID_UUID);
        assertTrue(result);
    }

    @Test
    public void testFalseDelete() {
        Boolean result = paymentServiceImpl.delete(null);
        assertFalse(result);
    }

    private List<UUID> getValidUuidList() {
        List<UUID> uuids = new ArrayList<>();
        uuids.add(UUID.randomUUID());
        return uuids;
    }

    private Payment getPayment() {
        Client client = new Client();
        return new Payment().setPaymentPrivateId(VALID_UUID)
                .setPaymentPrivateId(VALID_UUID)
                .setPaymentPublicId(VALID_UUID)
                .setKey("8bf69634c58b235978448447e594a6ae")
                .setAmount((double) 1000)
                .setReceiverAccountId(VALID_UUID)
                .setSenderAccountId(VALID_UUID)
                .setClientId(client);
    }
}

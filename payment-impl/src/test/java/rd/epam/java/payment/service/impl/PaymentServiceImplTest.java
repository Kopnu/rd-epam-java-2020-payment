package rd.epam.java.payment.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import rd.epam.java.payment.domain.entity.Payment;
import rd.epam.java.payment.repository.PaymentRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * {@link PaymentServiceImpl} tests.
 *
 * @author Dmitrii_Lopatin
 */
public class PaymentServiceImplTest {
    private final Payment VALID_PAYMENT = getPayment();
    private final UUID VALID_UUID = UUID.randomUUID();

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePaymentWhenPaymentIsNull() {
        Payment result = paymentServiceImpl.createPayment(null);
        assertNull(result);
    }

    @Test
    public void testCreatePayment() {
        doNothing().when(paymentRepository).save(VALID_PAYMENT);
        when(paymentRepository.findById(VALID_PAYMENT.getPaymentPrivateId())).thenReturn(Optional.of(VALID_PAYMENT));

        Payment result = paymentServiceImpl.createPayment(VALID_PAYMENT);

        verify(paymentRepository).save(VALID_PAYMENT);
        assertNotEquals(VALID_PAYMENT.getPaymentPrivateId(), VALID_PAYMENT.getPaymentPublicId());
        assertEquals(VALID_PAYMENT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreatePaymentWhenFindByIdIsNull() {
        doNothing().when(paymentRepository).save(VALID_PAYMENT);
        when(paymentRepository.findById(VALID_PAYMENT.getPaymentPrivateId())).thenReturn(Optional.empty());

        Payment result = paymentServiceImpl.createPayment(VALID_PAYMENT);

        verify(paymentRepository).save(VALID_PAYMENT);
        assertNotEquals(VALID_PAYMENT.getPaymentPrivateId(), VALID_PAYMENT.getPaymentPublicId());
        assertNull(result);
    }

    @Test
    public void testFindWhenUuidIsNull() {
        Payment result = paymentServiceImpl.find(null);
        assertNull(result);
    }

    @Test
    public void testFind() {
        when(paymentRepository.findById(VALID_UUID)).thenReturn(Optional.of(VALID_PAYMENT));
        Payment result = paymentServiceImpl.find(VALID_UUID);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testFindWhenFindByIdIsNull() {
        when(paymentRepository.findById(VALID_UUID)).thenReturn(Optional.empty());
        Payment result = paymentServiceImpl.find(VALID_UUID);
        assertNull(result);
    }

    @Test
    public void testFindAllWhenUuidsIsNull() {
        List<Payment> result = paymentServiceImpl.findAll(null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testFindAll() {
        UUID randomUUID = UUID.randomUUID();
        List<UUID> uuids = List.of(VALID_UUID, randomUUID);
        List<Payment> payments = List.of(VALID_PAYMENT, new Payment().setPaymentPrivateId(randomUUID));
        when(paymentRepository.findByIdList(uuids)).thenReturn(payments);

        List<Payment> result = paymentServiceImpl.findAll(uuids);

        assertEquals(payments, result);
    }

    @Test
    public void testFindAllWhenUuidsIsEmpty() {
        List<UUID> uuids = Collections.emptyList();
        when(paymentRepository.findByIdList(uuids)).thenReturn(Collections.emptyList());

        List<Payment> result = paymentServiceImpl.findAll(uuids);

        assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void testUpdateWhenPaymentIsNull() {
        Payment result = paymentServiceImpl.update(null);
        assertNull(result);
    }

    @Test
    public void testUpdate() {
        when(paymentRepository.update(VALID_PAYMENT)).thenReturn(Optional.of(VALID_PAYMENT));
        Payment result = paymentServiceImpl.update(VALID_PAYMENT);
        assertEquals(VALID_PAYMENT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testUpdateWhenUpdateIsNull() {
        when(paymentRepository.update(VALID_PAYMENT)).thenReturn(Optional.empty());
        Payment result = paymentServiceImpl.update(VALID_PAYMENT);
        assertNull(result);
    }

    @Test
    public void testTrueDelete() {
        doNothing().when(paymentRepository).delete(VALID_UUID);

        boolean result = paymentServiceImpl.delete(VALID_UUID);

        verify(paymentRepository).delete(VALID_UUID);
        assertTrue(result);
    }

    @Test
    public void testFalseDelete() {
        boolean result = paymentServiceImpl.delete(null);
        assertFalse(result);
    }

    private Payment getPayment() {
        return new Payment()
                .setPaymentPrivateId(VALID_UUID)
                .setPaymentPublicId(VALID_UUID);
    }
}

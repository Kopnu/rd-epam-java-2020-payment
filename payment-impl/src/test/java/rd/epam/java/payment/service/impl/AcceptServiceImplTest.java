package rd.epam.java.payment.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import rd.epam.java.payment.domain.entity.Accept;
import rd.epam.java.payment.repository.AcceptRepository;

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
 * {@link AcceptServiceImpl} tests.
 *
 * @author Dmitrii_Lopatin
 */
public class AcceptServiceImplTest {
    private final Accept VALID_ACCEPT = getAccept();
    private final Accept INVALID_ACCEPT_WITHOUT_CALLBACK_URL = getInvalidAcceptWithoutCallbackUrl();
    private final Accept INVALID_ACCEPT_WITHOUT_PAYMENT_URL = getInvalidAcceptWithoutPaymentUrl();
    private final Accept INVALID_ACCEPT_WITHOUT_KEY = getInvalidAcceptWithoutKey();
    private final UUID VALID_UUID = UUID.randomUUID();
    private final String VALID_STATUS_ID = "Принят в обработку";

    @Mock
    private AcceptRepository acceptRepository;

    @InjectMocks
    private AcceptServiceImpl acceptServiceImpl;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAcceptWhenAcceptIsNull() {
        Accept result = acceptServiceImpl.createAccept(null);
        assertNull(result);
    }

    @Test
    public void testCreateAcceptWhenAcceptCallbackUrlIsNull() {
        Accept result = acceptServiceImpl.createAccept(INVALID_ACCEPT_WITHOUT_CALLBACK_URL);
        assertNull(result);
    }

    @Test
    public void testCreateAcceptWhenAcceptPaymentUrlIsNull() {
        Accept result = acceptServiceImpl.createAccept(INVALID_ACCEPT_WITHOUT_PAYMENT_URL);
        assertNull(result);
    }

    @Test
    public void testCreateAcceptWhenAcceptKeyIsNull() {
        Accept result = acceptServiceImpl.createAccept(INVALID_ACCEPT_WITHOUT_KEY);
        assertNull(result);
    }

    @Test
    public void testCreateAccept() {
        doNothing().when(acceptRepository).save(VALID_ACCEPT);
        when(acceptRepository.findById(VALID_ACCEPT.getAcceptId())).thenReturn(Optional.of(VALID_ACCEPT));

        Accept result = acceptServiceImpl.createAccept(VALID_ACCEPT);

        verify(acceptRepository).save(VALID_ACCEPT);
        assertEquals(VALID_ACCEPT.getAcceptStatusId(), VALID_STATUS_ID);
        assertEquals(VALID_ACCEPT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreateAcceptWhenFindByIdIsNull() {
        doNothing().when(acceptRepository).save(VALID_ACCEPT);
        when(acceptRepository.findById(VALID_ACCEPT.getAcceptId())).thenReturn(Optional.empty());

        Accept result = acceptServiceImpl.createAccept(VALID_ACCEPT);

        verify(acceptRepository).save(VALID_ACCEPT);
        assertEquals(VALID_ACCEPT.getAcceptStatusId(), VALID_STATUS_ID);
        assertNull(result);
    }

    @Test
    public void testFindWhenUuidIsNull() {
        Accept result = acceptServiceImpl.find(null);
        assertNull(result);
    }

    @Test
    public void testFind() {
        when(acceptRepository.findById(VALID_UUID)).thenReturn(Optional.of(VALID_ACCEPT));
        Accept result = acceptServiceImpl.find(VALID_UUID);
        assertEquals(VALID_ACCEPT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testFindWhenFindByIdIsNull() {
        when(acceptRepository.findById(VALID_UUID)).thenReturn(Optional.empty());
        Accept result = acceptServiceImpl.find(VALID_UUID);
        assertNull(result);
    }

    @Test
    public void testFindAllWhenUuidsIsNull() {
        List<Accept> result = acceptServiceImpl.findAll(null);
        assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void testFindAll() {
        UUID randomUUID = UUID.randomUUID();
        List<UUID> uuids = List.of(VALID_UUID, randomUUID);
        List<Accept> accepts = List.of(VALID_ACCEPT, new Accept().setAcceptId(randomUUID));
        when(acceptRepository.findByIdList(uuids)).thenReturn(accepts);

        List<Accept> result = acceptServiceImpl.findAll(uuids);

        assertEquals(accepts, result);
    }

    @Test
    public void testFindAllWhenUuidsIsEmpty() {
        List<UUID> uuids = Collections.emptyList();
        when(acceptRepository.findByIdList(uuids)).thenReturn(Collections.emptyList());

        List<Accept> result = acceptServiceImpl.findAll(uuids);

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testUpdateWhenPaymentIsNull() {
        Accept result = acceptServiceImpl.update(null);
        assertNull(result);
    }

    @Test
    public void testUpdate() {
        when(acceptRepository.update(VALID_ACCEPT)).thenReturn(Optional.of(VALID_ACCEPT));
        Accept result = acceptServiceImpl.update(VALID_ACCEPT);
        assertEquals(VALID_ACCEPT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testUpdateWhenUpdateIsNull() {
        when(acceptRepository.update(VALID_ACCEPT)).thenReturn(Optional.empty());
        Accept result = acceptServiceImpl.update(VALID_ACCEPT);
        assertNull(result);
    }

    @Test
    public void testTrueDelete() {
        doNothing().when(acceptRepository).delete(VALID_UUID);

        boolean result = acceptServiceImpl.delete(VALID_UUID);

        verify(acceptRepository).delete(VALID_UUID);
        assertTrue(result);
    }

    @Test
    public void testFalseDelete() {
        boolean result = acceptServiceImpl.delete(null);
        assertFalse(result);
    }

    private Accept getAccept() {
        return new Accept()
                .setAcceptId(VALID_UUID)
                .setClientCallbackUrl("https://clientCallbackUrl")
                .setClientPaymentUrl("https://clientAcceptUrl")
                .setKey("8bf69634c58b235978448447e594a6ae");
    }

    private Accept getInvalidAcceptWithoutCallbackUrl() {
        return new Accept()
                .setAcceptId(VALID_UUID)
                .setClientPaymentUrl("https://clientAcceptUrl")
                .setKey("8bf69634c58b235978448447e594a6ae");
    }

    private Accept getInvalidAcceptWithoutPaymentUrl() {
        return new Accept()
                .setAcceptId(VALID_UUID)
                .setClientCallbackUrl("https://clientCallbackUrl")
                .setKey("8bf69634c58b235978448447e594a6ae");
    }

    private Accept getInvalidAcceptWithoutKey() {
        return new Accept()
                .setAcceptId(VALID_UUID)
                .setClientCallbackUrl("https://clientCallbackUrl")
                .setClientPaymentUrl("https://clientAcceptUrl");
    }
}

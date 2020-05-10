package rd.epam.java.payment.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.repository.ClientRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * {@link ClientServiceImpl} tests.
 *
 * @author Mihail_Sevryugin
 */
public class ClientServiceImplTest {
    private final UUID VALID_UUID = UUID.randomUUID();
    private final Client VALID_CLIENT = getClient();

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateClientWhenClientIsNull() {
        Client result = clientServiceImpl.createClient(null);
        assertNull(result);
    }

    @Test
    public void testCreateClient() {
        doNothing().when(clientRepository).save(VALID_CLIENT);
        when(clientRepository.findById(VALID_CLIENT.getId())).thenReturn(Optional.of(VALID_CLIENT));
        Client result = clientServiceImpl.createClient(VALID_CLIENT);
        verify(clientRepository).save(VALID_CLIENT);
        assertEquals(VALID_CLIENT, result);
    }

    @Test
    public void testFindWhenUuidIsNull() {
        Client result = clientServiceImpl.find(null);
        assertNull(result);
    }

    @Test
    public void testFind() {
        when(clientRepository.findById(VALID_UUID)).thenReturn(Optional.of(VALID_CLIENT));
        Client result = clientServiceImpl.find(VALID_UUID);
        assertEquals(VALID_CLIENT,result);
    }

    @Test
    public void testFindAllWhenUuidsIsNull() {
        List<Client> result = clientServiceImpl.findAll(null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testFindAll() {
        UUID randomUuid = UUID.randomUUID();
        List<UUID> uuids = List.of(VALID_UUID, randomUuid);
        List<Client> clients = List.of(VALID_CLIENT, new Client().setId(randomUuid));
        when(clientRepository.findByIdList(uuids)).thenReturn(clients);
        List<Client> result = clientServiceImpl.findAll(uuids);
        assertEquals(clients, result);
    }

    @Test
    public void testFindAllWhenUuidsIsEmpty() {
        List<UUID> uuids = Collections.emptyList();
        when(clientRepository.findByIdList(uuids)).thenReturn(Collections.emptyList());
        List<Client> result = clientServiceImpl.findAll(uuids);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testUpdateWhenClientIsNull() {
        Client result = clientServiceImpl.update(null);
        assertNull(result);
    }

    @Test
    public void testUpdate() {
        when(clientRepository.update(VALID_CLIENT)).thenReturn(Optional.of(VALID_CLIENT));
        Client result = clientServiceImpl.update(VALID_CLIENT);
        assertEquals(VALID_CLIENT, result);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testUpdateWhenUpdateIsNull() {
        when(clientRepository.update(VALID_CLIENT)).thenReturn(Optional.empty());
        Client result = clientServiceImpl.update(VALID_CLIENT);
        assertNull(result);
    }

    @Test
    public void testTrueDelete() {
        doNothing().when(clientRepository).delete(VALID_UUID);
        boolean result = clientServiceImpl.delete(VALID_UUID);
        verify(clientRepository).delete(VALID_UUID);
        assertTrue(result);
    }

    @Test
    public void testFalseDelete() {
        boolean result = clientServiceImpl.delete(null);
        assertFalse(result);
    }

    private Client getClient() {
        return new Client()
                .setId(VALID_UUID);
    }
}
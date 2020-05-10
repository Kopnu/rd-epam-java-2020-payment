package rd.epam.java.payment.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.repository.ClientRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
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
    public void testFindAll() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }

    private Client getClient() {
        return new Client()
                .setId(VALID_UUID)
                .setName("name")
                .setDescription("description")
                .setPayments(Collections.emptyList())
                .setAccounts(Collections.emptyList());
    }
}
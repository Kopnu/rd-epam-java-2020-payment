package rd.epam.java.payment.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;
import rd.epam.java.payment.domain.entity.Account;
import rd.epam.java.payment.domain.entity.AccountStatus;
import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.repository.AccountRepository;
import rd.epam.java.payment.repository.ClientRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * AccountServiceImplTest.
 *
 * @author Aleksey_Lukyanov
 */

public class AccountServiceImplTest {

    private static final UUID VALID_UUID = UUID.randomUUID();
    private final Account VALID_ACCOUNT = getValidAccount();

    private final String VALID_STATUS_ID = "Активен";

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccount() {
        when(accountRepository.findByAccNumber(VALID_ACCOUNT.getAccountNumber()))
                .thenReturn(Optional.of(VALID_ACCOUNT));
        when(clientRepository.findById(VALID_ACCOUNT.getClientId().getId()))
                .thenReturn(Optional.of(VALID_ACCOUNT.getClientId()));
        when(accountRepository.findById(VALID_ACCOUNT.getAccountId())).thenReturn(Optional.of(VALID_ACCOUNT));

        Account result = accountServiceImpl.createAccount(VALID_ACCOUNT);

        verify(accountRepository).save(VALID_ACCOUNT);
        assertEquals(VALID_ACCOUNT.getAccountStatus().getStatus(), VALID_STATUS_ID);
        assertEquals(VALID_ACCOUNT, result);
    }

    @Test
    public void testCreateAccountWhenAccountIsNull() {
        Account result = accountServiceImpl.createAccount(null);
        assertNull(result);
    }

    @Test
    public void testCreateAccountWhenClientIsNull() {
        Account result = accountServiceImpl.createAccount(getAccountWithoutClient());
        assertNull(result);
    }

    @Test
    public void testCreateAccountWhenClientDoesntFound() {
        when(clientRepository.findById(VALID_ACCOUNT.getClientId().getId())).thenReturn(Optional.empty());
        Account result = accountServiceImpl.createAccount(getAccountWithoutClient());
        assertNull(result);
    }

    @Test
    public void testFind() {
        when(accountRepository.findById(VALID_ACCOUNT.getAccountId())).thenReturn(Optional.of(VALID_ACCOUNT));
        Account result = accountServiceImpl.find(VALID_UUID);
        assertEquals(VALID_ACCOUNT, result);
    }

    @Test
    public void testFindWhenUuidIsNull() {
        Account result = accountServiceImpl.find(null);
        assertNull(result);
    }

    @Test
    public void testFindWhenFindByUuidReturnsEmpty() {
        when(accountRepository.findById(VALID_UUID)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> accountServiceImpl.find(VALID_UUID));
    }

    @Test
    public void testFindAllWhenIdsNull() {
        List<Account> result = accountServiceImpl.findAll(null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testFindAll() {
        List<UUID> list = new ArrayList<>();
        list.add(VALID_UUID);
        list.add(VALID_UUID);
        when(accountRepository.findByIdList(list)).thenReturn(new ArrayList<>());
        List<Account> result = accountServiceImpl.findAll(list);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testUpdateWhenAccountIsNull() {
        Account result = accountServiceImpl.update(null);
        assertNull(result);
    }

    @Test
    public void testUpdate() {
        when(accountRepository.update(VALID_ACCOUNT)).thenReturn(Optional.of(VALID_ACCOUNT));
        Account result = accountServiceImpl.update(VALID_ACCOUNT);
        assertEquals(VALID_ACCOUNT, result);
    }

    @Test
    public void testDeleteWhenUuidIsNull() {
        boolean result = accountServiceImpl.delete(null);
        assertFalse(result);
    }

    @Test
    public void testDelete() {
        boolean result = accountServiceImpl.delete(VALID_UUID);
        assertTrue(result);
    }

    private Account getValidAccount() {
        return new Account()
                .setAccountId(VALID_UUID)
                .setAccountStatus(new AccountStatus("Активен"))
                .setClientId(new Client())
                .setInn("")
                .setKpp("")
                .setOgrn("");
    }

    private Account getAccountWithoutClient() {
        return new Account()
                .setAccountId(VALID_UUID)
                .setAccountStatus(new AccountStatus("Активен"))
                .setClientId(null)
                .setInn("")
                .setKpp("")
                .setOgrn("");
    }
}
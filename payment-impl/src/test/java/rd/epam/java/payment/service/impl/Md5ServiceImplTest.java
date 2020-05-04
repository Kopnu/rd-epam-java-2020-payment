package rd.epam.java.payment.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rd.epam.java.payment.domain.entity.Account;
import rd.epam.java.payment.service.AccountService;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class Md5ServiceImplTest {

    private final static List<String> FIELDS = List.of("payment", "test", "md5", "string");
    private final static String VALID_MD5 = "8bf69634c58b235978448447e594a6ae";
    private final static String INVALID_MD5 = "2c00dfe59ae82d0bcfb0571bf7840974";

    @Mock
    private AccountService accountService;

    @InjectMocks
    private Md5ServiceImpl md5Service;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Следующее можно делать и в @Test методах, если нужно настроить индивидуально.
        // Но помним, что каждый тест в классе не индивидуален и настройки моков в отдельных тестах
        // приведут к изменениям во всех из данного класса.
        Account account = getAccount();
        when(accountService.find(any(UUID.class))).thenReturn(account);
    }

    @Test
    public void testValidGenerateMd5() {
        String result = md5Service.generateMd5(FIELDS);
        assertEquals(VALID_MD5, result);
    }

    @Test
    public void testTrueValidateAccountMd5() {
        Boolean result = md5Service.validateAccountMd5(UUID.randomUUID(), VALID_MD5);
        assertTrue(result);
    }

    @Test
    public void testFalseValidateAccountMd5() {
        Boolean result = md5Service.validateAccountMd5(UUID.randomUUID(), INVALID_MD5);
        assertFalse(result);
    }

    private Account getAccount() {
        return new Account()
                .setOgrn(FIELDS.get(0))
                .setKpp(FIELDS.get(1))
                .setInn(FIELDS.get(2))
                .setAccountNumber(FIELDS.get(3));
    }

}

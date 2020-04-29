package rd.epam.java.payment.service.impl;

import rd.epam.java.payment.domain.entity.Account;
import rd.epam.java.payment.service.AccountService;

import java.util.List;
import java.util.UUID;

/**
 * {@link AccountService} implementation.
 *
 * @author Sergei_Kornilov
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public Account find(UUID uuid) {
        return null;
    }

    @Override
    public List<Account> findAll(List<UUID> ids) {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public boolean delete(UUID uuid) {
        return false;
    }
}

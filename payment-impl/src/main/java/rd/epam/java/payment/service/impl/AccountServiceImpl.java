package rd.epam.java.payment.service.impl;

import rd.epam.java.payment.domain.entity.Account;
import rd.epam.java.payment.domain.entity.AccountStatus;
import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.repository.AccountRepository;
import rd.epam.java.payment.repository.ClientRepository;
import rd.epam.java.payment.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 * {@link AccountService} implementation.
 *
 * @author Aleksey_Lukyanov
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Override
    public Account createAccount(Account account) {
        if (Objects.isNull(account)) {
            log.error("createAccount() - error account is null");
            return null;
        }

        if(Objects.isNull(account.getClientId())) {
            log.error("createAccount() - error account doesn't contain client");
            return null;
        }

        Optional<Client> client = clientRepository.findById(account.getClientId().getId());
        if (client.isEmpty()) {
            log.error("createAccount() - error assigned client doesn't exist");
            return null;
        }

        Optional<Account> acc = accountRepository.findByAccNumber(account.getAccountNumber());
        if (acc.isEmpty()) {
            log.error("createAccount() - error account already exist");
            return null;
        }

        account.setAccountNumber(getRandomAccNumber());

        log.debug("createAccount() - create account {}", account);

        account.setAccountStatus(new AccountStatus("Активен"));
        accountRepository.save(account);
        return accountRepository.findById(account.getAccountId()).get();
    }

    @Override
    public Account find(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("find() - error uuid is null");
            return null;
        }
        log.debug("find() - find account by uuid : {}", uuid);
        return accountRepository.findById(uuid).get();
    }

    @Override
    public List<Account> findAll(List<UUID> ids) {
        if (Objects.isNull(ids)) {
            log.error("findAll() - error ids is null");
            return Collections.emptyList();
        }
        log.debug("findAll() - find list of accounts by ids = {}", ids);
        return accountRepository.findByIdList(ids);
    }

    @Override
    public Account update(Account account) {
        if (Objects.isNull(account)) {
            log.error("update() - error account is null");
            return null;
        }
        log.debug("update() - update account : {}", account);
        return accountRepository.update(account).get();
    }

    @Override
    public boolean delete(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("delete() - error uuid is null");
            return false;
        }
        log.debug("delete() - delete account by id : {}", uuid);
        accountRepository.delete(uuid);
        return true;
    }

    private static String getRandomAccNumber() {
        Random rnd = new Random();
        double d = rnd.nextDouble() * 1000000000000d;
        return String.format("%12.0f", d);
    }
}

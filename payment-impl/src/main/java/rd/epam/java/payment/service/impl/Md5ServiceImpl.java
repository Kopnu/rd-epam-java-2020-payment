package rd.epam.java.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import rd.epam.java.payment.domain.entity.Account;
import rd.epam.java.payment.service.AccountService;
import rd.epam.java.payment.service.Md5Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link Md5Service} implementation.
 *
 * @author Sergei_Kornilov
 */
@Slf4j
@RequiredArgsConstructor
public class Md5ServiceImpl implements Md5Service {

    private final AccountService accountService;

    @Override
    public String generateMd5(List<String> fields) {
        log.info("generateMd5() - generate md5 from fields = {}", fields);
        String stringOptional = fields.stream()
                .reduce((str1, str2) -> str1 + ":" + str2)
                .orElse("");
        String md5 = DigestUtils.md5Hex(stringOptional);
        log.debug("generateMd5() - generated md5 hex = {}", md5);
        return md5;
    }

    @Override
    public Boolean validateAccountMd5(UUID accountId, String md5) {
        boolean result = false;
        Account account = accountService.find(accountId);
        if (Objects.nonNull(account)) {
            List<String> fields = List.of(account.getOgrn(), account.getKpp(), account.getInn(), account.getAccountNumber());
            String generateMd5 = generateMd5(fields);
            result = generateMd5.equals(md5);
        }
        return result;
    }
}

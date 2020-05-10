package rd.epam.java.payment.service;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with md5.
 *
 * @author Sergei_Kornilov
 */
public interface Md5Service {

    /**
     * Generate md5 from fields.
     *
     * @param fields fields
     * @return generated md5 string
     */
    String generateMd5(List<String> fields);

    /**
     * Validate an incoming account's md5 with an account from the system.
     *
     * @param accountId uuid account
     * @param md5       hash string
     * @return check result
     */
    Boolean validateAccountMd5(UUID accountId, String md5);
}

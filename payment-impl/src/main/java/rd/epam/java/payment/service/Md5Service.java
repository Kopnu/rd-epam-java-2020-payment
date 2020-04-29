package rd.epam.java.payment.service;

import java.util.List;

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

}

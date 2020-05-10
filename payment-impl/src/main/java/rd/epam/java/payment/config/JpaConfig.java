package rd.epam.java.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * JPA Configuration.
 *
 * @author Dmitrii_Lopatin
 */
@Configuration
public class JpaConfig {

    private static final String PERSISTENCE_UNIT_NAME = "payment-unit";

    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    }
}

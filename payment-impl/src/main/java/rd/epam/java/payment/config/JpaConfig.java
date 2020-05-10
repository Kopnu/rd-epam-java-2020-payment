package rd.epam.java.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * JpaConfig.
 *
 * @author Dmitrii_Lopatin
 */
@Configuration
public class JpaConfig {

    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("payment-unit").createEntityManager();
    }
}

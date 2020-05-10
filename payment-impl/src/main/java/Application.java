import rd.epam.java.payment.config.JpaConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;

@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd");
        EntityManager entityManager = (EntityManager) context.getBean("entityManager");
        log.info(entityManager.toString());
        log.error("Hello error home!");
        log.warn("Hello warn home!");
        log.info("Hello info home!");
        log.debug("Hello debug home!");
        log.trace("Hello trace home!");
    }
}

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("rd.epam.java.payment");
        log.error("Hello error home!");
        log.warn("Hello warn home!");
        log.info("Hello info home!");
        log.debug("Hello debug home!");
        log.trace("Hello trace home!");
    }
}

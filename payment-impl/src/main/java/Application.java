import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.error("Hello error home!");
        log.warn("Hello warn home!");
        log.info("Hello info home!");
        log.debug("Hello debug home!");
        log.trace("Hello trace home!");
    }
}

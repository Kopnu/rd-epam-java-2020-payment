package rd.epam.java.payment.domain.entity;

/**
 * AccountEnum.
 *
 * @author Mihail_Sevryugin
 */
public enum AccountStatus {
    ACTIVE("Активен"),
    CLOSED("Закрыт");

    private final String string;

    AccountStatus(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}

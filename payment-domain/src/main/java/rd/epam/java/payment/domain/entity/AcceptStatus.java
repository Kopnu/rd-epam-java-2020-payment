package rd.epam.java.payment.domain.entity;

/**
 * AcceptStatus.
 *
 * @author Mihail_Sevryugin
 */

public enum AcceptStatus {
    ACCEPTED_FOR_PROCESSING("Принят в обработку"),
    EXTENDED("Выставлен"),
    PAID("Оплачен"),
    ERROR("Ошибка");

    private final String string;


    AcceptStatus(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}

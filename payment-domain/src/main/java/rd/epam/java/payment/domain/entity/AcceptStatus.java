package rd.epam.java.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration of possible accept statuses
 *
 * @author Mihail_Sevryugin
 */
@Getter
@AllArgsConstructor
public enum AcceptStatus {
    ACCEPTED_FOR_PROCESSING("Принят в обработку"),
    EXTENDED("Выставлен"),
    PAID("Оплачен"),
    ERROR("Ошибка");

    private final String string;
}

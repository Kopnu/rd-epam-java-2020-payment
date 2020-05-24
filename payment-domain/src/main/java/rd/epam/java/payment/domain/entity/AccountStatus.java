package rd.epam.java.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration of possible account statuses
 *
 * @author Mihail_Sevryugin
 */
@Getter
@AllArgsConstructor
public enum AccountStatus {
    ACTIVE("Активен"),
    CLOSED("Закрыт");

    private final String string;
}

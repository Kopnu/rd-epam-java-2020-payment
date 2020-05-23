package rd.epam.java.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for account status
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

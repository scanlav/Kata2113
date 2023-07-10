import org.example.TipService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipServiceTest {
    TipService tipService = new TipService();

    //Если проверяем только сервис, то и тестирование неверных данных не нужно, т.к. их обработка не
    // реализована.
    @ParameterizedTest(name = "Граничное значение {0}")
    @CsvSource({"-1", "0", "1", "499", "500", "501", "999", "1000", "1001"})
    void roundTipTest(BigDecimal num) {
        BigDecimal expected;

        if (num.compareTo(BigDecimal.valueOf(1000)) < 0) {
            expected = num.multiply(BigDecimal.valueOf(1.1));
        } else {
            expected = num.multiply(BigDecimal.valueOf(1.05));
        }

        assertEquals(expected, tipService.roundTips(num));
    }
}


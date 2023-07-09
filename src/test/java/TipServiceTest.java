import org.example.TipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TipServiceTest {
    TipService tipService = new TipService();

    @DisplayName("Граничные значения")
    @ParameterizedTest(name = "Проверка №{index}. Проверяемое число: {0}")
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

    @DisplayName("Передача в метод значения Null")
    @ParameterizedTest
    @NullSource
    void roundTipNullTest(BigDecimal num) {
        Assertions.assertThrows(NullPointerException.class, () -> tipService.roundTips(num));
    }
}

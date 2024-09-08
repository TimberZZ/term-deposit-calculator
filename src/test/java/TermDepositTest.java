import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TermDepositCalculator;
import vo.InterestPaidType;
import vo.TermDepositParams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alex
 *
 * Unit test for calculate logics.
 */
public class TermDepositTest {

    TermDepositCalculator termDepositCalculator;
    TermDepositParams params;

    @BeforeEach
    void setup() {
        termDepositCalculator = new TermDepositCalculator();
        params = new TermDepositParams();
    }

    @Test
    public void testSimpleTermDeposit() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setMonths(0);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        int result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10330, result);
    }

    @Test
    public void testTermDepositWithDiffAmount() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        int result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10330, result);
        params.setPrincipal(18000);
        result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(18594, result);
    }

    @Test
    public void testTermDepositWithDiffTerm() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        int result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10330, result);
        params.setMonths(6);
        result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10385, result);
    }

    @Test
    public void testTermDepositWithDiffInterest() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        int result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10330, result);
        params.setInterestRate(1.4);
        result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10420, result);

    }

    @Test
    public void testTermDepositWithDiffInterestPaid() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        int result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10330, result);

        params.setInterestPaidType(InterestPaidType.Monthly);
        result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10335, result);

        params.setInterestPaidType(InterestPaidType.Quarterly);
        result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10335, result);

        params.setInterestPaidType(InterestPaidType.Annually);
        result = termDepositCalculator.calculateFinalBalance(params);
        assertEquals(10334, result);
    }

    @Test
    public void testInvalidPrincipal() {
        params.setPrincipal(10);
        params.setYears(3);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
        params.setPrincipal(-10000);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
        params.setPrincipal(2000000);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
    }

    @Test
    public void testInvalidInterestRate() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setInterestRate(-1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
        params.setInterestRate(20);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
    }

    @Test
    public void testInvalidYears() {
        params.setPrincipal(10000);
        params.setYears(-1);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
        params.setYears(7);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
    }

    @Test
    public void testInvalidMonths() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setMonths(13);
        params.setInterestRate(1.1);
        params.setInterestPaidType(InterestPaidType.Maturity);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
        params.setMonths(-1);
        assertThrows(NumberFormatException.class, () -> termDepositCalculator.calculateFinalBalance(params));
    }

    @Test
    public void testInvalidInterestPaidType() {
        params.setPrincipal(10000);
        params.setYears(3);
        params.setMonths(0);
        params.setInterestRate(1.1);
        params.setInterestPaidType(null);
        assertThrows(IllegalArgumentException.class, () -> termDepositCalculator.calculateFinalBalance(params));
    }
}

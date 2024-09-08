package vo;

import lombok.Data;

/**
 * @author Alex
 *
 * Input params for calculate.
 */
@Data
public class TermDepositParams {
    private int principal;
    private double interestRate;
    private int years;
    private int months;
    private InterestPaidType interestPaidType;
}

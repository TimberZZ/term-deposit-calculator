package util;

import vo.InterestPaidType;

/**
 * @author Alex
 *
 * Static functions to validate input values.
 */
public class CalculatorParamsValidator {

    public static void validatePrincipal(int principal) {
        if (principal < 1000 || principal > 1500000) {
            throw new NumberFormatException();
        }
    }

    public static void validateInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 15) {
            throw new NumberFormatException();
        }
    }

    public static void validateYears(int years) {
        if (years < 0 || years > 5) {
            throw new NumberFormatException();
        }
    }

    public static void validateMonths(int months) {
        if (months < 0 || months > 11) {
            throw new NumberFormatException();
        }
    }

    public static void validateInterestPaidType(InterestPaidType interestPaidType) {
        if (interestPaidType == null) {
            throw new IllegalArgumentException();
        }
    }

}

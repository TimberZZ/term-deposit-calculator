package service;

import util.CalculatorParamsValidator;
import vo.TermDepositParams;

/**
 * @author Alex
 *
 * Core logic for the calculator.
 */
public class TermDepositCalculator {

    /**
     * calculate final balance based on user inputs.
     * at Maturity: principal * (1 + monthly_rate * months)
     * Monthly: principal * (1 + monthly_rate) ^ months
     * Quarterly: principal * (1 + quarterly_rate) ^ quarters
     * Annually: principal * (1 + annual_rate) ^ years
     * @param params input params from user client
     * @return final balance (round to nearest int value)
     */
    public int calculateFinalBalance(TermDepositParams params) {
        validateParams(params);
        int months = params.getYears() * 12 + params.getMonths();
        double rate = params.getInterestRate() / 100;
        double finalAmount = switch (params.getInterestPaidType()) {
            case Maturity -> params.getPrincipal() * (1 + (rate / 12) * months);
            case Monthly -> params.getPrincipal() * Math.pow(1 + (rate / 12), months);
            case Quarterly -> params.getPrincipal() * Math.pow(1 + (rate / 4), (double) months / 3);
            case Annually -> params.getPrincipal() * Math.pow(1 + rate, (double) months / 12);
        };

        // Round to the nearest int
        return (int) Math.round(finalAmount);
    }

    private void validateParams(TermDepositParams params) {
        CalculatorParamsValidator.validatePrincipal(params.getPrincipal());
        CalculatorParamsValidator.validateInterestRate(params.getInterestRate());
        CalculatorParamsValidator.validateYears(params.getYears());
        CalculatorParamsValidator.validateMonths(params.getMonths());
        CalculatorParamsValidator.validateInterestPaidType(params.getInterestPaidType());
    }
}

package client;

import service.TermDepositCalculator;
import util.CalculatorParamsValidator;
import vo.InterestPaidType;
import vo.TermDepositParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alex
 *
 * Create UI for the calculator.
 */
class TermDepositCalculatorFrame extends JFrame {
    private final JTextField principalField;
    private final JTextField annualRateField;
    private final JTextField yearsField;
    private final JTextField monthsField;
    private final JComboBox<InterestPaidType> interestPaidCombo;
    private final JTextArea resultArea;

    /**
     * define the UI frame and some default input values.
     */
    public TermDepositCalculatorFrame() {
        setTitle("Term Deposit Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Create and add components
        add(new JLabel("<html><span style='font-size:16px;'>Start deposit amount ($):</span></html>", SwingConstants.CENTER));
        principalField = new JTextField("10000");
        add(principalField);

        add(new JLabel("<html><span style='font-size:16px;'>Interest rate (annual %):</span></html>", SwingConstants.CENTER));
        annualRateField = new JTextField("1.1");
        add(annualRateField);

        add(new JLabel("<html><span style='font-size:16px;'>Investment term (years):</span></html>", SwingConstants.CENTER));
        yearsField = new JTextField("3");
        add(yearsField);

        add(new JLabel("<html><span style='font-size:16px;'>Investment term (months):</span></html>", SwingConstants.CENTER));
        monthsField = new JTextField("0");
        add(monthsField);

        add(new JLabel("<html><span style='font-size:16px;'>Interest paid:</span></html>", SwingConstants.CENTER));
        interestPaidCombo = new JComboBox<>(new InterestPaidType[] {InterestPaidType.Monthly, InterestPaidType.Quarterly, InterestPaidType.Annually, InterestPaidType.Maturity});
        add(interestPaidCombo);

        JButton calculateButton = new JButton("Final Balance");
        add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        // Add action listener for the button
        calculateButton.addActionListener(new CalculateButtonListener());
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TermDepositParams params = validateAndGetParamsFromField();

                TermDepositCalculator termDepositCalculator = new TermDepositCalculator();

                double finalBalance = termDepositCalculator.calculateFinalBalance(params);

                resultArea.setText(String.format("$%.0f", finalBalance));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TermDepositCalculatorFrame.this,
                        "An Error occur, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private TermDepositParams validateAndGetParamsFromField() {
            TermDepositParams params = new TermDepositParams();
            validateAndGetPrincipal(params);
            validateAndGetInterestRate(params);
            validateAndGetYears(params);
            validateAndGetMonths(params);
            validateAndGetInterestPaidType(params);
            return params;
        }

        private void validateAndGetPrincipal(TermDepositParams params) {
            try {
                int principal = Integer.parseInt(principalField.getText());
                CalculatorParamsValidator.validatePrincipal(principal);
                params.setPrincipal(principal);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TermDepositCalculatorFrame.this,
                        "Invalid principal. Please enter between 1000 and 1500000.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void validateAndGetInterestRate(TermDepositParams params) {
            try {
                double interestRate = Double.parseDouble(annualRateField.getText());
                CalculatorParamsValidator.validateInterestRate(interestRate);
                params.setInterestRate(interestRate);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TermDepositCalculatorFrame.this,
                        "Invalid interest rate. Please enter between 0 and 15.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void validateAndGetYears(TermDepositParams params) {
            try {
                int years = Integer.parseInt(yearsField.getText());
                CalculatorParamsValidator.validateYears(years);
                params.setYears(years);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TermDepositCalculatorFrame.this,
                        "Invalid years. Please enter between 0 and 5.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void validateAndGetMonths(TermDepositParams params) {
            try {
                int months = Integer.parseInt(monthsField.getText());
                CalculatorParamsValidator.validateMonths(months);
                params.setMonths(months);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TermDepositCalculatorFrame.this,
                        "Invalid months. Please enter between 0 and 11.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void validateAndGetInterestPaidType(TermDepositParams params) {
            try {
                InterestPaidType interestPaidType = (InterestPaidType) interestPaidCombo.getSelectedItem();
                CalculatorParamsValidator.validateInterestPaidType(interestPaidType);
                params.setInterestPaidType(interestPaidType);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(TermDepositCalculatorFrame.this,
                        "Invalid interest paid type. Please select one option.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }

    }
}


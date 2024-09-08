package client;

import javax.swing.*;

/**
 * @author Alex
 *
 * Main class to run for the calculator.
 */
public class TermDepositCalculatorGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TermDepositCalculatorFrame().setVisible(true));
    }
}


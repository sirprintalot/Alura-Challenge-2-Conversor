package Converter;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

public class ConverterGUI extends JFrame {

    private final JComboBox<String> menu1;
    private final JComboBox<String> menu2;
    private final JTextField inputField;


    public ConverterGUI() {
//         set the frame
        setTitle("Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
//        setUndecorated(true);
//        getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Border mainBorder = new LineBorder(new Color(186, 186, 215), 17, false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 1, 1, 0));
        mainPanel.setBackground(new Color(186, 186, 215));
        mainPanel.setBorder(mainBorder);


//         components
        Converter converter = new Converter();
        SortedMap<String, Double> sortedCurrencies = new TreeMap<>(converter.getConversionRates());
        menu1 = new JComboBox<>(sortedCurrencies.keySet().toArray(new String[0]));
        menu1.setFont(new Font("Monospaced", Font.PLAIN, 15));

        menu2 = new JComboBox<>(sortedCurrencies.keySet().toArray(new String[0]));
        menu2.setFont(new Font("Monospaced", Font.PLAIN, 15));

//        for (String currency : Converter.conversionRates.keySet()) {
//            menu1.addItem(currency);
//            menu2.addItem(currency);
//        }

//        LABELS
        JLabel convertLabel = new JLabel("Convert: ");
        convertLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(convertLabel);
        mainPanel.add(menu1);

        inputField = new JTextField();

        JLabel clearLabel = new JLabel("To: ");
        clearLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(clearLabel);
        mainPanel.add(menu2);

        JLabel inputFieldLabel = new JLabel("Enter the amount to be converted: ");
        inputFieldLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(inputFieldLabel);
        mainPanel.add(inputField);

//        Dialog font
        UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.BOLD, 16));

//        BUTTONS
        JButton convertButton = new JButton("Convert values");
        convertButton.setFont(new Font("Monospaced", Font.BOLD, 16));

        JButton invertButton = new JButton("Invert values");
        invertButton.setFont(new Font("Monospaced", Font.BOLD, 16));

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Monospaced", Font.BOLD, 16));

        mainPanel.add(convertButton);
        mainPanel.add(invertButton);
        mainPanel.add(clearButton);

        add(mainPanel);
//        pack();
        setVisible(true);

//        BUTTONS LISTENERS
        clearButton.addActionListener(e -> inputField.setText(""));
//
        invertButton.addActionListener(e -> {
            if (menu1.getSelectedItem() == menu2.getSelectedItem()) {
                JOptionPane.showMessageDialog(null, "there the same currency!");
            } else {
                int selectedIndex1 = menu1.getSelectedIndex();
                int selectedIndex2 = menu2.getSelectedIndex();

                menu1.setSelectedIndex(selectedIndex2);
                menu2.setSelectedIndex(selectedIndex1);
            }
        });

        convertButton.addActionListener(e -> {
            try {
                String fromCurrency = (String) menu1.getSelectedItem();
                String toCurrency = (String) menu2.getSelectedItem();
                double amount;
                try {
                    try {
                        Converter.compareOptions(fromCurrency, toCurrency);
                    } catch (Exception ignored) {

                    }

                    amount = converter.validateInput(inputField.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Only numbers allowed!");
                    inputField.setText("");
                    return;
                }
                double result = converter.convertCurrency(amount, fromCurrency, toCurrency);
                JOptionPane.showMessageDialog(null,
                        amount + " " + fromCurrency + " are: " + result + " " + toCurrency + "s");
            } catch (NumberFormatException nfe) {
                System.out.println("error");
            }

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConverterGUI::new);
    }
}


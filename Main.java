import models.*;
import utilities.Validator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utilities.Converter;

public class Main extends JFrame {

    private JTextField binField;
    private JTextField hexField;
    private JTextField decField;
    private JTextField octField;
    
    private String currentInput = "0";
    private String firstOperand = null;
    private BinaryOperation currentOperation = null;
    private boolean newInput = false;

    public Main() {
        setTitle("Calculator");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeUI();
    }

    private void initializeUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(32, 32, 32));
        add(root);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(32, 32, 32));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Programmer");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        topPanel.add(title, BorderLayout.WEST);

        root.add(topPanel, BorderLayout.NORTH);

        JPanel basePanel = new JPanel(new GridLayout(4, 2, 5, 5));
        basePanel.setBackground(new Color(32, 32, 32));
        basePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        basePanel.add(createLabel("HEX"));
        hexField = createValue("0");
        basePanel.add(hexField);

        basePanel.add(createLabel("DEC"));
        decField = createValue("0");
        basePanel.add(decField);

        basePanel.add(createLabel("OCT"));
        octField = createValue("0");
        basePanel.add(octField);

        basePanel.add(createLabel("BIN"));
        binField = createValue("0");
        basePanel.add(binField);

        root.add(basePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 6, 5, 5));
        buttonPanel.setBackground(new Color(32, 32, 32));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addDisabled(buttonPanel, "AND");
        addDisabled(buttonPanel, "OR");
        addDisabled(buttonPanel, "NOT");
        addDisabled(buttonPanel, "XOR");
        addDisabled(buttonPanel, "<<");
        addDisabled(buttonPanel, ">>");

        addDisabled(buttonPanel, "A");
        addDisabled(buttonPanel, "B");
        addDisabled(buttonPanel, "C");
        addDisabled(buttonPanel, "D");
        addDisabled(buttonPanel, "(");
        addDisabled(buttonPanel, ")");

        addDisabled(buttonPanel, "E");
        addDisabled(buttonPanel, "F");
        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addOperation(buttonPanel, "/", new Division());

        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addOperation(buttonPanel, "*", new Multiplication());

        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addOperation(buttonPanel, "-", new Subtraction());

        addClearButton(buttonPanel, "C");
        addButton(buttonPanel, "0");
        addDisabled(buttonPanel, ".");
        addOperation(buttonPanel, "+", new Addition());

        addEqualButton(buttonPanel, "=");

        root.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }

    private JTextField createValue(String value) {
        JTextField field = new JTextField(value);
        field.setEditable(false);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setFont(new Font("Segoe UI", Font.BOLD, 18));
        field.setBackground(new Color(45, 45, 45));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return field;
    }

    private void addButton(JPanel panel, String text) {
        JButton btn = createButton(text);
        btn.addActionListener(e -> {
            if (newInput || currentInput.equals("0")) {
                currentInput = text;
                newInput = false;
            } else {
                currentInput += text;
            }
            updateDisplay();
        });
        panel.add(btn);
    }

    private void addOperation(JPanel panel, String text, BinaryOperation op) {
        JButton btn = createButton(text);
        btn.addActionListener(e -> {
            if (firstOperand != null && currentOperation != null && !newInput) {
                calculateResult();
            }
            if (firstOperand == null) {
                firstOperand = currentInput;
            }
            
            currentOperation = op;
            newInput = true;
        });
        panel.add(btn);
    }

    private void addClearButton(JPanel panel, String text) {
        JButton btn = createButton(text);
        btn.addActionListener(e -> clearCalculator());
        panel.add(btn);
    }

    private void addEqualButton(JPanel panel, String text) {
        JButton btn = createButton(text);
        btn.setBackground(new Color(0, 120, 215));
        btn.addActionListener(e -> {
            if (firstOperand != null && currentOperation != null) {
                calculateResult();
                firstOperand = null;
                currentOperation = null;
            }
        });
        panel.add(btn);
    }

    private void addDisabled(JPanel panel, String text) {
        JButton btn = createButton(text);
        btn.setEnabled(false);
        panel.add(btn);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(60, 63, 65));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }

    private void calculateResult() {
        try {
            if (firstOperand == null || currentOperation == null) {
                return;
            }
            
            String result = currentOperation.calculate(firstOperand, currentInput);
            currentInput = result;
            updateDisplay();
            newInput = true;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            clearCalculator();
        }
    }

    private void updateDisplay() {
        try {
            String displayBinary = currentInput.replaceAll("^0+(?!$)", "");
            if (displayBinary.isEmpty()) {
                displayBinary = "0";
            }
            
            binField.setText(displayBinary);
            
            int decimal = Converter.binaryToDecimal(currentInput);
            decField.setText(String.valueOf(decimal));
            
            hexField.setText(Integer.toHexString(decimal).toUpperCase());
            
            octField.setText(Integer.toOctalString(decimal));
            
        } catch (Exception e) {
            binField.setText(currentInput);
        }
    }

    private void clearCalculator() {
        currentInput = "0";
        firstOperand = null;
        currentOperation = null;
        newInput = false;
        updateDisplay();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
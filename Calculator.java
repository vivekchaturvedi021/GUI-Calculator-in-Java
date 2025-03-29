//Calculator in Java 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String num1 = "", num2 = "", operator = "";

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Times New Roman", Font.BOLD, 44));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setPreferredSize(new Dimension(300, 100));
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5)); // Updated to 5 rows
        String[] buttons = {
            "7", "8", "9", "/", 
             "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", "%", "=", "+", 
            "sin", "cos", "tan", "X"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 34));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (operator.isEmpty()) {
                num1 += command;
            } else {
                num2 += command;
            }
            textField.setText(num1 + operator + num2);
        } else if (command.equals("C")) {
            num1 = num2 = operator = "";
            textField.setText("");
        } else if (command.equals("X")) {
            if (!operator.isEmpty()) {
                num2 = num2.length() > 0 ? num2.substring(0, num2.length() - 1) : "";
            } else {
                num1 = num1.length() > 0 ? num1.substring(0, num1.length() - 1) : "";
            }
            textField.setText(num1 + operator + num2);
        } else if (command.equals("=")) {
            if (!num1.isEmpty() && (!num2.isEmpty() || operator.equals("sin") || operator.equals("cos") || operator.equals("tan"))) {
                double result = calculate(Double.parseDouble(num1), num2.isEmpty() ? 0 : Double.parseDouble(num2), operator);
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = operator = "";
            }
        } else {
            if (command.equals("sin") || command.equals("cos") || command.equals("tan")) {
                operator = command;
                textField.setText(operator + "(" + num1 + ")");
            } else {
                if (!num1.isEmpty() && num2.isEmpty()) {
                    operator = command;
                    textField.setText(num1 + operator);
                }
            }
        }
    }

    private double calculate(double n1, double n2, String op) {
        return switch (op) {
            case "+" -> n1 + n2;
            case "-" -> n1 - n2;
            case "*" -> n1 * n2;
            case "/" -> n2 != 0 ? n1 / n2 : 0;
            case "%" -> n2 != 0 ? n1 % n2 : 0;
            case "sin" -> Math.sin(Math.toRadians(n1));
             case "cos" -> Math.cos(Math.toRadians(n1));
            case "tan" -> Math.tan(Math.toRadians(n1));
            default -> 0;
        };
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
 
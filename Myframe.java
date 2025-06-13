import javax.script.ScriptException;
import javax.swing.*;  //For JFrame
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Myframe extends JFrame{

    private JLabel display; // Global so all buttons can update it
    private StringBuilder input = new StringBuilder(); // Stores the current input

    Myframe(){      //constructor

        this.setTitle("Calculator");
        this.setSize(500,600);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); // Centers the Window to the screen
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //------------------Display------------------
        display = new JLabel("0", SwingConstants.RIGHT); //for display of calculator
        display.setVisible(true);
        display.setSize(200,500);
        this.add(display,BorderLayout.NORTH);

        display.setFont(new Font("Arial", Font.PLAIN, 80));
        display.setBorder(BorderFactory.createLineBorder(Color.white,1));
        display.setBackground(Color.DARK_GRAY);
        display.setForeground(Color.WHITE);
        display.setOpaque(true);

        //------------------Buttons-------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 0, 0));
        this.add(buttonPanel,BorderLayout.CENTER);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFocusable(false);
            button.addActionListener(e -> handleInput(text));

            buttonPanel.add(button);
        }
        // Clear button
        JButton clearButton = new JButton("C");
        clearButton.setSize(150,200);
        clearButton.setFont(new Font("Arial", Font.BOLD, 24));
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusable(false);
        clearButton.addActionListener(e -> {
            input.setLength(0);
            display.setText("0");
        });
        this.add(clearButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void handleInput(String value) {

        if (value.equals("=")) {
            try {
                double result = evaluate(input.toString());
                display.setText(String.valueOf(result));
                input.setLength(0);
                input.append(result); 
            } catch (Exception e) {
                display.setText("Error");
                input.setLength(0);
            }
        } else {
            input.append(value);
            display.setText(input.toString());
        }
    }
    private double  evaluate(String input){
        char operator = 0;
        int operatorIndex = -1;

        for (int i = 0; i < input.length(); i++) { // Find the operator
            char c = input.charAt(i);
            if ("+-*/".indexOf(c) != -1) {
                operator = c;
                operatorIndex = i;
                break;
            }
        }

        if (operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        // Split numbers
        
        double num1 = Double.parseDouble(input.substring(0, operatorIndex));
        double num2 = Double.parseDouble(input.substring(operatorIndex + 1));

        return switch (operator) {  // Perform operation
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalArgumentException("Unknown operator");
        };
    }
}


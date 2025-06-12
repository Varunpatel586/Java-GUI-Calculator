import javax.swing.*;
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

        String[] buttons = { "1", "2", "3", "+" ,"4", "5", "6","-", "7", "8", "9", "*" ,"0", ".", "=" , "/" };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFocusable(false);
            button.addActionListener(e -> handleInput(text));

            buttonPanel.add(button);
        }
    }
        private void handleInput(String value) {
            if (value.equals("C")) {
                input.setLength(0); // Clear input
            } else {
                input.append(value); // Append button value
            }
            display.setText(input.length() > 0 ? input.toString() : "0");  // Update label
        }
}

package package1;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.Font;

//import package1.App;
import package2.Calculation;
import package2.DateCheck;

public class AppScreen {
    
    private static JLabel errorOutput;
    private static JLabel dateOutput;
    
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Calendar Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 300);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Font titleFont = new Font("Times New Roman", Font.BOLD, 40);
        Font timesFont = new Font("Times New Roman", Font.PLAIN, 20);

        JLabel titleLabel = new JLabel("Welcome to the CALENDAR CALCULATOR!");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel cmdLabel = new JLabel("Enter a past date in MM/DD/YYYY: ");
        cmdLabel.setFont(timesFont);
        cmdLabel.setForeground(Color.BLACK);
        cmdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Where the date input will be entered
        JTextField dateField = new JTextField(20);
        dateField.setFont(timesFont);
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateField.setMaximumSize(new Dimension(400, 50));

        //Click on the button or press "Enter"
        JButton button = new JButton("Get Day!");
        button.setFont(timesFont);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLUE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getRootPane().setDefaultButton(button);

        //For invalid cases
        errorOutput = new JLabel(" ");
        errorOutput.setFont(timesFont);
        errorOutput.setForeground(Color.RED);
        errorOutput.setAlignmentX(Component.CENTER_ALIGNMENT);

        //For correct dates
        dateOutput = new JLabel(" ");
        dateOutput.setFont(timesFont);
        dateOutput.setForeground(new Color(0, 128, 0));
        dateOutput.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userDate = dateField.getText().trim();

                errorOutput.setText(" ");
                dateOutput.setText(" ");

                String[] dateSplitter = userDate.split("/");

                try {

                    for (String part : dateSplitter) {
                        Integer.parseInt(part);
                    }

                    if (dateSplitter.length != 3) {
                        errorOutput.setText("Invalid date! Please use the MM/DD/YYYY format!");
                        dateOutput.setText(" ");
                        return;
                    }

                    int month = Integer.parseInt(dateSplitter[0]);
                    int day = Integer.parseInt(dateSplitter[1]);
                    int year = Integer.parseInt(dateSplitter[2]);

                    if (!DateCheck.isValidDate(month, day, year)) {
                        errorOutput.setText("Invalid date! Please try again!\n");
                        dateOutput.setText(" ");
                        return;
                    }

                    if (!DateCheck.isValidYear(year)) {
                        errorOutput.setText("Invalid year! Year must be 1582 to the present!\n");
                        dateOutput.setText(" ");
                        return;
                    }

                    if (!DateCheck.notFuture(month, day, year)) {
                        errorOutput.setText("Invalid date! Date cannot be in the future!\n");
                        dateOutput.setText(" ");
                        return;
                    }

                    int finalNum = Calculation.finalCombo(month, day, year);
                    String dotw = Calculation.dayOfTheWeek(finalNum);

                    if (DateCheck.isPresent(month, day, year)) {
                        dateOutput.setText(userDate + " is a " + dotw + "!\n");
                        errorOutput.setText(" ");
                        return;
                    }

                    dateOutput.setText(userDate + " was a " + dotw + "!\n");
                    errorOutput.setText(" ");
                }

                catch (NumberFormatException ex) {
                    errorOutput.setText("Invalid input! Make sure the month, day, and year are all numbers");
                    dateOutput.setText(" ");
                }
            }
        });

        
        //Visual arrangements
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));

        panel.add(cmdLabel);
        panel.add(dateField);

        panel.add(Box.createVerticalStrut(10));
        panel.add(errorOutput);
        panel.add(dateOutput);
        panel.add(button);
        panel.add(Box.createVerticalStrut(10));
        
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
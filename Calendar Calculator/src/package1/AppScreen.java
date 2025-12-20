package package1;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

//import package1.App;
import package2.Calculation;
import package2.DateCheck;

public class AppScreen {
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
        frame.setSize(800, 800);

        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Font titleFont = new Font("Times New Roman", Font.BOLD, 40);
        Font timesFont = new Font("Times New Roman", Font.PLAIN, 20);

        JLabel titleLabel = new JLabel("Welcome to the CALENDAR CALCULATOR!");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel cmdLabel = new JLabel("Enter date in MM-DD-YYYY: ");
        cmdLabel.setFont(timesFont);
        titleLabel.setForeground(Color.BLACK);
        cmdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField dateField = new JTextField(20);
        dateField.setFont(timesFont);
        dateField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Get Day!");
        button.setFont(timesFont);
        titleLabel.setForeground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userDate = dateField.getText();
                String[] dateSplitter = userDate.split("-");
                if (dateSplitter.length != 3) {
                    JOptionPane.showMessageDialog(frame, "Invalid date! Please use the MM-DD-YYYY format!");
                    return;
                }

                try {
                    int month = Integer.parseInt(dateSplitter[0]);
                    int day = Integer.parseInt(dateSplitter[1]);
                    int year = Integer.parseInt(dateSplitter[2]);

                    if (!DateCheck.isValidDate(month, day, year)) {
                        JOptionPane.showMessageDialog(frame, "This is an invalid date! Please try again!\n");
                        return;
                    }

                    if (!DateCheck.isValidYear(year)) {
                        JOptionPane.showMessageDialog(frame, "Invalid year! Year must be 1582 to the present!\n");
                        return;
                    }

                    if (!DateCheck.notFuture(month, day, year)) {
                        JOptionPane.showMessageDialog(frame, "Invalid date! Date cannot be in the future!\n");
                        return;
                    }

                    //JOptionPane.showMessageDialog(frame, "Calculating day of the week! Please wait ...\n");

                    int finalNum = Calculation.finalCombo(month, day, year);
                    String dotw = Calculation.dayOfTheWeek(finalNum);
                    JOptionPane.showMessageDialog(frame, userDate + " was a " + dotw);
                }

                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Make sure the month, day, and year are all numbers");
                }
            }
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));

        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        inputRow.setBackground(Color.YELLOW);
        inputRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputRow.add(cmdLabel);
        inputRow.add(dateField);

        panel.add(inputRow);
        panel.add(Box.createVerticalStrut(1));
        panel.add(button);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
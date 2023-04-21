import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends JFrame implements ActionListener {

    private JTextField localDateTimeField;
    private JTextField timeZoneField;
    private JLabel outputLabel;

    public Main() {
        // Set up the UI components
        JLabel localDateTimeLabel = new JLabel("Enter local Date and time (yyyy-MM-dd HH:mm): ");
        localDateTimeField = new JTextField(20);

        JLabel timeZoneLabel = new JLabel("Enter Country TIme Zone (e.g. America/New_York): ");
        timeZoneField = new JTextField(20);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        outputLabel = new JLabel("");

        // Add the components to the JFrame
        setLayout(new GridLayout(4, 2));
        add(localDateTimeLabel);
        add(localDateTimeField);
        add(timeZoneLabel);
        add(timeZoneField);
        add(convertButton);
        add(new JLabel());
        add(new JLabel("Converted date and time: "));
        add(outputLabel);

        // Set the properties of the JFrame
        setTitle("Date and Time Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Get the user input
        String inputDateTime = localDateTimeField.getText();
        String inputTimeZone = timeZoneField.getText();

        // Convert the date and time
        LocalDateTime localDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(inputTimeZone));
        String outputDateTime = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss"));

        // Display the converted date and time
        outputLabel.setText(outputDateTime);
    }

    public static void main(String[] args) {
        new Main();
    }
}

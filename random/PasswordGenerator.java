import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PasswordGenerator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Password Generator and Encryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 2000);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Password Generator and Encryption");
        mainPanel.add(titleLabel);

        JButton generatorButton = new JButton("Password Generator");
        generatorButton.setFont(new Font("Arial", Font.PLAIN, 80)); // Adjust the font size
        generatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordGenerator();
            }
        });
        mainPanel.add(generatorButton);

        JButton encryptionButton = new JButton("Password Encryption");
        encryptionButton.setFont(new Font("Arial", Font.PLAIN, 120)); // Adjust the font size
        encryptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordEncryption();
            }
        });
        mainPanel.add(encryptionButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public static void passwordGenerator() {
        String originalWord = JOptionPane.showInputDialog("Please enter the word or characters:");
        int desiredLength = Integer.parseInt(JOptionPane.showInputDialog("Please enter the desired password length:"));

        String[] passwordOptions = generatePasswordOptions(originalWord, desiredLength);

        StringBuilder message = new StringBuilder();
        message.append("Generated Password Options:\n");
        for (String option : passwordOptions) {
            message.append(option).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString());
    }

    public static void passwordEncryption() {
        JTextArea originalPasswordArea = new JTextArea(50, 100);
        JScrollPane originalPasswordScrollPane = new JScrollPane(originalPasswordArea);
        int option = JOptionPane.showOptionDialog(null, originalPasswordScrollPane, "Enter the password to encrypt:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            String originalPassword = originalPasswordArea.getText();
            int shiftKey = Integer.parseInt(JOptionPane.showInputDialog("Please enter the shift key for encryption:"));

            String encryptedPassword = encryptPassword(originalPassword, shiftKey);
            JOptionPane.showMessageDialog(null, "Encrypted Password: " + encryptedPassword);
        }
        }

    public static String[] generatePasswordOptions(String originalWord, int desiredLength) {
        String[] passwordOptions = new String[20];

        for (int i = 0; i < passwordOptions.length; i++) {
            String randomChars = generateRandomChars(desiredLength - originalWord.length());
            String passwordOption = originalWord + randomChars;
            passwordOptions[i] = passwordOption;
        }

        return passwordOptions;
    }

    public static String generateRandomChars(int length) {
        Random random = new Random();
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder randomCharsBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            char randomChar = validChars.charAt(randomIndex);
            randomCharsBuilder.append(randomChar);
        }

        return randomCharsBuilder.toString();
    }

    public static String encryptPassword(String password, int shiftKey) {
        StringBuilder encryptedPassword = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) ((ch + shiftKey - 'A') % 26 + 'A');
                encryptedPassword.append(encryptedChar);
            } else if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) ((ch + shiftKey - 'a') % 26 + 'a');
                encryptedPassword.append(encryptedChar);
            } else {
                // For non-alphabetic characters, keep them unchanged
                encryptedPassword.append(ch);
            }
        }

        return encryptedPassword.toString();
    }

    public static String decryptPassword(String encryptedPassword, int shiftKey) {
        StringBuilder decryptedPassword = new StringBuilder();

        for (int i = 0; i < encryptedPassword.length(); i++) {
            char ch = encryptedPassword.charAt(i);

            if (Character.isUpperCase(ch)) {
                char decryptedChar = (char) ((ch - shiftKey - 'A' + 26) % 26 + 'A');
                decryptedPassword.append(decryptedChar);
            } else if (Character.isLowerCase(ch)) {
                char decryptedChar = (char) ((ch - shiftKey - 'a' + 26) % 26 + 'a');
                decryptedPassword.append(decryptedChar);
            } else {
                // For non-alphabetic characters, keep them unchanged
                decryptedPassword.append(ch);
            }
        }

        return decryptedPassword.toString();
    }
}

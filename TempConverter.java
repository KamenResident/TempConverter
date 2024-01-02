import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * An application used to convert temperature values in either Celsius or Fahrenheit.
 */
public class TempConverter extends JFrame {

    /**
     * Constructor for the temperature converter.
     */
    public TempConverter() {
        init();
        createComponents();
    }

    /**
     * Used to initialize the contents
     */
    private void init() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("clouds.jpg")))));
            setLayout(new FlowLayout());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the necessary components for the application.
     */
    private void createComponents() {
     
        // Main panel to house the buttons and text fields.
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        mainPanel.setSize(500, 200);

        Border outline = BorderFactory.createLineBorder(Color.BLACK);

        // Fahrenheit panel and its components
        JPanel fahrenheitPanel = new JPanel();
        fahrenheitPanel.setBounds(0, 0, 500, 100);
        fahrenheitPanel.setBackground(Color.WHITE);
        fahrenheitPanel.setBorder(outline);
        JTextField textfield = new JTextField(3);       
        JLabel label = new JLabel("Fahrenheit (F):");           
        JButton validButton = new JButton("To Celsius");
        validButton.setBackground(Color.ORANGE);
        fahrenheitPanel.add(label);
        fahrenheitPanel.add(textfield);
        fahrenheitPanel.add(validButton);

        // Celsius panel and its components
        JPanel celsiusPanel = new JPanel();
        celsiusPanel.setBounds(0, 200, 500, 100);
        celsiusPanel.setBackground(Color.WHITE);
        celsiusPanel.setBorder(outline);       
        JTextField textfield2 = new JTextField(3);                 
        JLabel label2 = new JLabel("Celsius (C):");      
        JButton validButton2 = new JButton("To Fahrenheit");
        validButton2.setBackground(Color.CYAN);
        celsiusPanel.add(label2);
        celsiusPanel.add(textfield2);
        celsiusPanel.add(validButton2);
        
        activateButton(validButton, textfield, textfield2, 0);
        activateButton(validButton2, textfield2, textfield, 1);

        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);       
        changeFont(fahrenheitPanel, font);       
        changeFont(celsiusPanel, font);

        // Establish alignment of the Fahrenheit and Celsius panels.
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(fahrenheitPanel)
                .addComponent(celsiusPanel))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()           
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(fahrenheitPanel))           
            .addComponent(celsiusPanel)
        );

        mainPanel.setOpaque(false);
        add(mainPanel);
    }

    /**
     * Used to add functionality to the buttons for temperature conversion.
     * 
     * @param jb is a Jbutton that enables temperature conversion upon pressing.
     * @param tf is the input text field.
     * @param tf2 is the output text field.
     * @param choice is a value used to determine if the button is meant for Fahrenheit 
     *        or Celsius values.
     */
    private void activateButton(JButton jb, JTextField tf, JTextField tf2, int choice) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double d = Double.parseDouble(tf.getText());
                    if (choice == 0) {
                        d = (d - 32) * (5.0 / 9.0);
                    } else {
                        d = (d * (9.0 / 5.0)) + 32;
                    }
                    tf2.setText(String.format("%.1f", d));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, 
                                                    "Please enter a numerical temperature value.", 
                                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                tf.setText("");
            }
        });
    }

    /**
     * Used to change the font of a component and its children.
     * 
     * @param component is the main parent component.
     * @param font is the font used to change from the default font.
     */
    private static void changeFont (Component component, Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            for (Component comp : ((Container) component).getComponents()) {
                changeFont(comp, font);
            }
        }
    }

}
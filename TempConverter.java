import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;
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

    private void init() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createComponents() {
        
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        mainPanel.setSize(500, 200);

        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("clouds.jpg")))));
            setLayout(new FlowLayout());
        } catch (IOException e) {
            e.printStackTrace();
        }

        

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 100);
        panel.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 200, 500, 100);
        panel2.setBackground(Color.WHITE);

        JTextField textfield = new JTextField(3);
        JTextField textfield2 = new JTextField(3);
        JButton validButton = new JButton("To Celsius");
        JButton validButton2 = new JButton("To Fahrenheit");
        JLabel label = new JLabel("Fahrenheit (F):");
        JLabel label2 = new JLabel("Celsius (C):");

        activateButton(validButton, textfield, textfield2, 0);
        activateButton(validButton2, textfield2, textfield, 1);
  
        panel.add(label);
        panel.add(textfield);
        panel.add(validButton);
        panel2.add(label2);
        panel2.add(textfield2);
        panel2.add(validButton2);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panel)
                .addComponent(panel2))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()           
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(panel))           
            .addComponent(panel2)
        );

        mainPanel.setOpaque(false);
        add(mainPanel);
    }

    private void activateButton(JButton jb, JTextField tf, JTextField tf2, int degree) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double d = Double.parseDouble(tf.getText());
                    if (degree == 0) {
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

}
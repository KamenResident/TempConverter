import javax.swing.SwingUtilities;

public class MainLauncher {
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                TempConverter tempConverter = new TempConverter();
                tempConverter.setVisible(true);
            }
        });
    }
}
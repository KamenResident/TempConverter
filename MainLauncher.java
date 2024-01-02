import javax.swing.SwingUtilities;

/**
 * Main launcher used to start up the application.
 */
public class MainLauncher {
    
    /**
     * Main method for starting up the application.
     * 
     * @param args is a collection of arguments.
     */
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

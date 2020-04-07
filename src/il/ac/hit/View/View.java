package il.ac.hit.View;

import il.ac.hit.Exceptions.DBActionsException;

import javax.swing.*;

/**
 * The <code>View</code> class is the main class which runs the program
 */
public class View {
    /**
     * The <code>main</code> method is the one that initiates the whole program and creates a <code>TableHomeFrame</code>
     * object for the user UI
     * @param args the argument the main method receives
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TableHomeFrame window = new TableHomeFrame();
            } catch (DBActionsException e) {
                e.printStackTrace();
            }
            //in case there is an exception we haven't encountered.
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


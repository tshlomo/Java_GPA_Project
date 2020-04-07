package il.ac.hit.View;

import il.ac.hit.Exceptions.DBActionsException;

import javax.swing.*;

public class View {
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


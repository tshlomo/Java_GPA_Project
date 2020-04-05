package il.ac.hit.View;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TableHomeFrame window = new TableHomeFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


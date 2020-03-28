package View;

import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TableHomeFrame window = new TableHomeFrame();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}


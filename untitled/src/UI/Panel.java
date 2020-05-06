package UI;

import javax.swing.*;

public class Panel {
    private BoxLayout boxLayout;
    private JPanel panel;

    // EFFECTS: constructs a new JPanel according to the design layout
    public Panel() {
        panel = new JPanel();
        boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
    }

    public JPanel getPanel() {
        return panel;
    }
}

package view;
import javax.swing.JPanel;

public abstract class CardPanel extends JPanel {
    public abstract String getName();

    public abstract void update();
}
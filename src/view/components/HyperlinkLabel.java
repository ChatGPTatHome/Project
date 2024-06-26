package view.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * HyperlinkLabel is a button with no background or border
 * that attempts to mimic a JLabel.
 * 
 * @author Hai Duong
 */
public class HyperlinkLabel extends JPanel {
    /**
     * Creates a HyperlinkLabel with the given text and action listener.
     * 
     * @param text text to display.
     * @param listener action to perform when clicked.
     * @author Hai Duong
     */
    public HyperlinkLabel(String text, ActionListener listener) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel container = new JPanel(new FlowLayout());
        JButton button = new JButton();

        button.setText(String.format("<html><a color=#0000EE>%s</a><html>", text));
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setBackground(Color.WHITE);
        button.addActionListener(listener);

        container.add(button);
        this.add(container);
    }
}

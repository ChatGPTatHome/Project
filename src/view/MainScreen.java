package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Anthony Chapkin, Hai Duong, Jeremiah Brenio, Windie Le.
 *
 * @version v1.00
 * 
 *          Displays an empty GUI.
 */
public class MainScreen {
    // constants
    /** A ToolKit for centering the main window. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimensions of the user's screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /** Scaling size of the window. */
    private static final int SCALE = 3;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panel;
    private JMenuBar menuBar;

    public MainScreen() {
        // JFRAME STUFF
        this.frame = new JFrame("ProjectHub");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);

        // position the frame in the center of the screen
        this.frame.setLocation(SCREEN_WIDTH / 2 - this.frame.getWidth() / 2,
                SCREEN_HEIGHT / 2 - this.frame.getHeight() / 2);

        // JPANEL STUFF
        this.cardLayout = new CardLayout();
        
        this.panel = new JPanel(this.cardLayout);
        this.frame.add(this.panel);

        // JMENU STUFF
        this.menuBar = new JMenuBar();
        this.frame.setJMenuBar(this.menuBar);
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add.
     */
    public void addCard(CardPanel card) {
        JMenu menu = new JMenu(card.getName());
        menu.addActionListener(e -> this.showCard(card));

        this.panel.add(card);
        
        this.menuBar.add(menu);
    }

    /**
     * Shows the given card.
     * 
     * @param card The card to show.
     */
    public void showCard(CardPanel card) {
        this.cardLayout.show(this.panel, card.getName());
    }

    /**
     * Reveals the JFrame.
     */
    public void start() {
        frame.setVisible(true);
    }
}
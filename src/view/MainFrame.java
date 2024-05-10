package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Models;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Anthony Chapkin, Hai Duong, Jeremiah Brenio, Windie Le.
 *
 * @version v1.00
 * 
 *          Displays an empty GUI.
 */
public class MainFrame {
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

    private Models models;
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panel;
    private JMenuBar menuBar;

    public MainFrame() {
        // SETUP
        this.models = new Models();
        
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
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException 
     */
    public CardPanel addCard(Class<? extends CardPanel> cardClass) {
        return this.addCard(cardClass, false);
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add.
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    public CardPanel addCard(Class<? extends CardPanel> cardClass, boolean focus) {
        CardPanel card;
        try {
            Constructor<?> cardConstructor = cardClass.getConstructor(Models.class);
            card = (CardPanel)(cardConstructor.newInstance(new Object[] { this.models }));
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad card class.");
        }
        
        JMenu menu = new JMenu(card.getName());
        
        menu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                focusCard(card);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        this.panel.add(card, card.getName());
        this.menuBar.add(menu);

        return focus ? this.focusCard(card) : card;
    }

    /**
     * Shows the given card.
     * 
     * @param card The card to show.
//     */
    public CardPanel focusCard(CardPanel card) {
        card.update();
        this.cardLayout.show(this.panel, card.getName());

        return card;
    }

    /**
     * Reveals the JFrame.
     */
    public void start() {
        frame.setVisible(true);
    }
}
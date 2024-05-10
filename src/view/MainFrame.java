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

    private final Models models;
    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel panel;
    private final JMenuBar menuBar;

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
     * Gets the Models object from this MainFrame. Use cautiously.
     */
    public Models getModelSource() {
        return this.models;
    }

    private static boolean hasModelConstructor(Class<? extends CardPanel> cardClass) {
        for (Constructor<?> constructor : cardClass.getDeclaredConstructors()) {
            if ((constructor.getParameterCount() == 1) 
            && (constructor.getParameterTypes()[0] == Models.class))
                return true;
        }

        return false;
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add without focus.
     */
    public CardPanel addCard(Class<? extends CardPanel> cardClass) {
        return this.addCard(cardClass, false);
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add.
     * @boolean focus Whether to focus on this card on start.
     */
    public CardPanel addCard(CardPanel card) {
        return this.addCard(card, false);
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add.
     * @boolean focus Whether to focus on this card on start.
     */
    public CardPanel addCard(Class<? extends CardPanel> cardClass, boolean focus) {
        CardPanel card;

        try {
            if (MainFrame.hasModelConstructor(cardClass)) {
                Constructor<?> cardConstructor = cardClass.getConstructor(Models.class);
                card = (CardPanel)(cardConstructor.newInstance(new Object[] { this.models }));
            } else {
                Constructor<?> cardConstructor = cardClass.getConstructor();
                card = (CardPanel)(cardConstructor.newInstance());
                card.setModelSource(this.models);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Bad card class.");
        }
        
        return this.addCard(card, focus);
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add.
     * @boolean focus Whether to focus on this card on start.
     */
    public CardPanel addCard(CardPanel card, boolean focus) {
        this.addMenuTab(card);
        this.panel.add(card, card.getName());

        return focus ? this.focusCard(card) : card;
    }

    /**
     * Adds a card to the JMenu.
     * 
     * @param card the card to add.
     */
    private void addMenuTab(CardPanel card) {
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
        
        this.menuBar.add(menu);
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
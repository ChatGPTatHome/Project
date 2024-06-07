package view;

import javax.swing.ImageIcon;
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
 * Contains the main JFrame used by the app which
 * contains all Screens.
 *
 * @author Anthony Chapkin
 * @author Hai Duong
 * @author Jeremiah Brenio
 * @author Windie Le
 *
 * @version v1.00
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
    private static final int SCALE = 2;

    /**
     * A Models field that contains all model Objects
     * used and shared by all classes.
     */
    private final Models models;

    /**
     * The main JFrame that contains all GUI.
     */
    private final JFrame frame;

    /**
     * A CardLayout to allow easy switching
     * between Screen in the frame.
     */
    private final CardLayout cardLayout;

    /**
     * Panel that all other GUI sits upon.
     */
    private final JPanel panel;

    /**
     * A JMenuBar used to switch between Screens.
     */
    private final JMenuBar menuBar;

    /**
     * Constructs this MainFrame, and sets up the frame,
     * panel, and menuBar.
     *
     * @author Hai Duong
     */
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

        // ICON
        this.frame.setIconImage(new ImageIcon("src/data/PH.png").getImage());
    }

    /**
     * Gets the Models object from this MainFrame. Use cautiously.
     *
     * @return the Models instance used by most classes.
     *
     * @author Hai Duong
     */
    public Models getModelSource() {
        return this.models;
    }

    /**
     * Checks of a model has a constructor.
     *
     * @param cardClass the Screen Class to be checked
     *                  for a constructor.
     * @return true or false that constructor exists.
     *
     * @author Hai Duong
     */
    private static boolean hasModelConstructor(Class<? extends Screen> cardClass) {
        for (Constructor<?> constructor : cardClass.getDeclaredConstructors()) {
            if ((constructor.getParameterCount() == 1)
                    && (constructor.getParameterTypes()[0] == Models.class))
                return true;
        }

        return false;
    }

    /**
     * Adds the given card. Does not focus card
     * but does create a MenuTab.
     * 
     * @param cardClass The CardPanel Class to add without focus.
     * @return the Screen that was added.
     *
     * @author Hai Duong
     * @author Anthony Chapkin - Made it compatible with ProjectHub.
     */
    public Screen addCard(Class<? extends Screen> cardClass) {
        return this.addCard(cardClass, false, true);
    }

    /**
     * Adds the given card. Does not focus
     * the card nor does it create a MenuTab.
     * 
     * @param card The CardPanel to add.
     * @return the Screen that was added.
     *
     * @author Hai Duong
     * @author Anthony Chapkin - Made it compatible with ProjectHub.
     */
    public Screen addCard(Screen card) {
        return this.addCard(card, false, true);
    }

    /**
     * Adds the given card.
     * 
     * @param cardClass The CardPanel Class to add.
     * @param focus boolean if we should focus the
     *              added card.
     * @param createMenuTab boolean if we should create a
     *                      MenuTab for the added Screen.
     * @return the Screen we added.
     *
     * @author Hai Duong
     * @author Anthony Chapkin - Made it compatible with ProjectHub.
     */
    public Screen addCard(Class<? extends Screen> cardClass, boolean focus, boolean createMenuTab) {
        Screen card;

        try {
            if (MainFrame.hasModelConstructor(cardClass)) {
                Constructor<?> cardConstructor = cardClass.getConstructor(Models.class);
                card = (Screen) (cardConstructor.newInstance(new Object[] { this.models }));
            } else {
                Constructor<?> cardConstructor = cardClass.getConstructor();
                card = (Screen) (cardConstructor.newInstance());
                card.setModelSource(this.models);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Bad card class.");
        }

        return this.addCard(card, focus, createMenuTab);
    }

    /**
     * Adds the given card.
     * 
     * @param card The CardPanel to add.
     * @param focus Whether to focus on this card on start.
     * @param createMenuTab Whether to create a MenuTab for the
     *                      card or not.
     * @return the Screen we added.
     *
     * @author Hai Duong
     * @author Anthony Chapkin - Made it compatible with ProjectHub.
     */
    public Screen addCard(Screen card, boolean focus, boolean createMenuTab) {
        if (createMenuTab) {
            this.addMenuTab(card);
        }
        this.panel.add(card, card.getName());

        return focus ? this.focusCard(card) : card;
    }

    /**
     * Adds a card to the JMenu.
     * 
     * @param card the card to add.
     *
     * @author Hai Duong
     */
    private void addMenuTab(Screen card) {
        JMenu menu = new JMenu(card.getName());

        menu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                focusCard(card);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.menuBar.add(menu);
    }

    /**
     * Shows the given card.
     * 
     * @param card The card to show.
     * @return the Screen that was focused.
     *
     * @author Hai Duong
     */
    public Screen focusCard(Screen card) {
        card.update();
        this.cardLayout.show(this.panel, card.getName());

        return card;
    }

    /**
     * Focuses a Screen by its name.
     *
     * @param cardName the Screens name
     *                 as a String.
     *
     * @author Anthony Chapkin
     */
    public void focusCard(String cardName) {
        this.cardLayout.show(this.panel, cardName);
    }

    /**
     * Reveals the JFrame.
     *
     * @author Jeremiah Brenio
     */
    public void start() {
        frame.setVisible(true);
    }

    /**
     * Toggles the MenuBar to be visible or not.
     *
     * @param isVisible true or false if MenuBar should
     *                  be visible. Is a boolean.
     *
     * @author Anthony Chapkin
     */
    public void toggleMenuBar(boolean isVisible) {
        this.menuBar.setVisible(isVisible);
    }

}
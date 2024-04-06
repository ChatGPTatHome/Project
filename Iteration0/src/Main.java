import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Anthony Chapkin, Hai Duong, Jeremiah Brenio, Windie Le.
 *
 * @version v0.00
 * 
 *          Displays an empty GUI with minimal functionality.
 */
public class Main {
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

    public static void main(String[] args) {

        JFrame frame = new JFrame("ChatGPT at Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);

        // position the frame in the center of the screen
        frame.setLocation(SCREEN_WIDTH / 2 - frame.getWidth() / 2,
                SCREEN_HEIGHT / 2 - frame.getHeight() / 2);

        frame.setVisible(true);
    }
}
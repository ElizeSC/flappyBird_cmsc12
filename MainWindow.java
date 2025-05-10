import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public MainWindow() {
        setTitle("Flappy Bird");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create layout and main container
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Create StartPanel, and when Enter is pressed, switch to GamePanel
        StartPanel startPanel = new StartPanel(() -> {
            GamePanel gamePanel = new GamePanel();  // No name needed
            mainContainer.add(gamePanel, "game");
            cardLayout.show(mainContainer, "game");
            SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);
        });
        

        // Add panels to main container
        mainContainer.add(startPanel, "start");

        add(mainContainer);
        setVisible(true);

        // Start with StartPanel in focus
        // Change this in MainWindow constructor:
        SwingUtilities.invokeLater(() -> {
            startPanel.setFocusable(true);
            startPanel.requestFocusInWindow();
            System.out.println("Focus requested for StartPanel");
        });
        
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}

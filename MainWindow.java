import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private StartPanel startPanel;
    private GamePanel gamePanel;

    public MainWindow() {
        setTitle("Flappy Bird");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create layout and main container
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        
        // Create StartPanel
        startPanel = new StartPanel(new StartGameHandler());
        mainContainer.add(startPanel, "start");
        
        add(mainContainer);
        setVisible(true);

        // Focus on StartPanel
        startPanel.setFocusable(true);
        startPanel.requestFocusInWindow();
        System.out.println("Focus requested for StartPanel");
    }
    
    // Inner class to handle game start
    private class StartGameHandler implements Runnable {
        @Override
        public void run() {
            String playerName = startPanel.getPlayerName();
            if (playerName.trim().isEmpty()) {
                playerName = "Anonymous";
            }
            
            gamePanel = new GamePanel(playerName);
            mainContainer.add(gamePanel, "game");
            cardLayout.show(mainContainer, "game");
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gamePanel.requestFocusInWindow();
                }
            });
        }
    }
    
    public static void main(String[] args) {
        new MainWindow();
    }
}

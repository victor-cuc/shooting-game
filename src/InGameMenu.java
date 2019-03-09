
import javax.swing.*;

public class InGameMenu {
    private JButton pauseButton;
    private JButton restartButton;
    private JButton mainMenuButton;
    private JButton quitButton;
    private JPanel mainPanel;

    private Game game;

    public InGameMenu(Game game) {
        this.game = game;

        quitButton.addActionListener(e -> System.exit(0));

        restartButton.addActionListener(e -> game.restartLevel());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

import javax.swing.*;

public class InGameMenu {
    private JButton pauseButton;
    private JButton resetButton;
    private JButton mainMenuButton;
    private JButton quitButton;
    private JPanel mainPanel;

    private Game game;

    public InGameMenu(Game game) {
        this.game = game;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

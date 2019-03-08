import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameMenu {
    private JButton pauseButton;
    private JButton resetButton;
    private JButton mainMenuButton;
    private JButton quitButton;
    private JPanel mainPanel;

    private Game game;

    public InGameMenu(Game game) {
        this.game = game;

        quitButton.addActionListener(e -> System.exit(0));

        resetButton.addActionListener(e -> game.restartLevel());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

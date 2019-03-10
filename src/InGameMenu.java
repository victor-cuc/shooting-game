
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameMenu {
    private JButton pauseButton;
    private JButton restartButton;
    private JButton mainMenuButton;
    private JPanel mainPanel;

    private Game game;

    public InGameMenu(Game game) {
        this.game = game;

        restartButton.addActionListener(e -> game.restartLevel());
        mainMenuButton.addActionListener(e -> game.mainMenu());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

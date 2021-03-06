
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameMenu {
    private JButton restartButton;
    private JButton mainMenuButton;
    private JPanel mainPanel;
    private JButton toggleMusicButton;

    private Game game;

    public InGameMenu(Game game) {
        this.game = game;

        restartButton.addActionListener(e -> game.restartLevel());
        mainMenuButton.addActionListener(e -> game.mainMenu());
        toggleMusicButton.addActionListener(e -> game.toggleSoundtrack());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

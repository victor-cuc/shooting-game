import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private final Game game;
    private JButton playButton;
    private JButton quitButton;
    private JPanel mainPanel;

    public MainMenu(Game game) {
        this.game = game;

        playButton.addActionListener(e -> game.playGame());
        quitButton.addActionListener(e -> game.quit());
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}

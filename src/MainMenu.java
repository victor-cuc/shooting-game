import javax.swing.*;

public class MainMenu {
    private final Game game;
    private JButton playButton;
    private JButton quitButton;
    private JPanel mainPanel;
    private JComboBox chooseLevelDropdown;
    private JButton playSelectedLevelButton;

    public MainMenu(Game game) {
        this.game = game;

        playButton.addActionListener(e -> game.playGame());
        quitButton.addActionListener(e -> game.quit());
//        chooseLevelDropdown.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                switch (chooseLevelDropdown.getSelectedItem().toString()) {
//                    case "Level 1":
//                        System.out.println("Level 1 from dropdown");
//                    case "Level 2":
//                        System.out.println("Level 2 from dropdown");
//                }
//            }
//        });
        playSelectedLevelButton.addActionListener(e -> {
            int index = chooseLevelDropdown.getSelectedIndex();
            switch (index) {
                case 0:
                    game.playLevel(1);
                    break;
                case 1:
                    game.playLevel(2);
                    break;
                case 2:
                    game.playLevel(3);
                    break;
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMenu extends JPanel {
    private JButton onePlayer;
    private JButton twoPlayers;
    private JLabel title;

    public MyMenu() {

        setLayout(null);

        onePlayer = new JButton("1 Player");
        twoPlayers = new JButton("2 Players");
        title = new JLabel("Checkers");

        onePlayer.setBounds(105, 180, 175, 30);
        twoPlayers.setBounds(105, 215, 175, 30);
        title.setBounds(71, 50, 250, 50);
        title.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 60));

        add(onePlayer);
        add(twoPlayers);
        add(title);

        onePlayer.addActionListener(e -> {
            Choose.OpenOnePlayerPanel();
        });
        twoPlayers.addActionListener(e -> {
            Choose.OpenTwoPlayersPanel();
        });
    }
}

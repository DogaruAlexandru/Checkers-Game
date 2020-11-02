import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMenu extends JPanel {
    private final JLabel title;
    private final JButton onePlayer;
    private final JButton twoPlayers;

    public MyMenu() {

        setLayout(null);

        title = new JLabel("Checkers");
        onePlayer = new JButton("1 Player");
        twoPlayers = new JButton("2 Players");

        title.setBounds(71, 50, 250, 50);
        onePlayer.setBounds(105, 180, 175, 30);
        twoPlayers.setBounds(105, 215, 175, 30);

        title.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 60));
        onePlayer.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        twoPlayers.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        add(onePlayer);
        add(twoPlayers);
        add(title);

        onePlayer.addActionListener(e -> Choose.OpenOnePlayerPanel());
        twoPlayers.addActionListener(e -> Choose.OpenTwoPlayersPanel());
    }
}

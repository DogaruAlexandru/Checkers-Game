import java.awt.*;
import javax.swing.*;

public class MyMenu extends JPanel {
    private final JLabel title;
    private final JButton onePlayer;
    private final JButton twoPlayers;

    public MyMenu() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));

        title = new JLabel("Checkers");
        onePlayer = new JButton("1 Player");
        twoPlayers = new JButton("2 Players");

        title.setBounds(75, 52, 250, 50);
        onePlayer.setBounds(105, 200, 175, 30);
        twoPlayers.setBounds(105, 235, 175, 30);

        title.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 60));
        onePlayer.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        twoPlayers.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        title.setForeground(Color.black);

        add(onePlayer);
        add(twoPlayers);
        add(title);

        onePlayer.addActionListener(e -> Choose.OpenOnePlayerPanel());
        twoPlayers.addActionListener(e -> Choose.OpenTwoPlayersPanel());
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(Choose.background, 0, 0, null);
        drawTitleFrame(g);
    }

    private void drawTitleFrame(Graphics g) {
        g.setColor(Choose.myBrown);
        g.fillRect(60, 40, 265, 70);
        g.setColor(Color.BLACK);
        g.drawRect(60, 40, 265, 70);
        g.setColor(Color.BLACK);

        g.setColor(Choose.myWhite);
        g.fillRect(65, 45, 255, 60);
        g.setColor(Color.BLACK);
        g.drawRect(65, 45, 255, 60);
    }
}

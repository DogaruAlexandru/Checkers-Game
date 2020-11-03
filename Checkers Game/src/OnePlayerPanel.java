import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class OnePlayerPanel extends JPanel {
    private final MyBoard myBoard;
    private final JLabel player1;
    private final JLabel computer;
    private final JLabel score;
    private final JButton reset;
    private final JButton back;

    public OnePlayerPanel() {
        setLayout(null);

        myBoard = new MyBoard();
        player1 = new JLabel("Player1");
        score = new JLabel("0-0");
        computer = new JLabel("Computer");
        reset = new JButton("Reset");
        back = new JButton("Back");

        player1.setBounds(730, 150, 180, 50);
        score.setBounds(765, 200, 55, 50);
        computer.setBounds(707, 242, 180, 50);
        reset.setBounds(715, 470, 175, 30);
        back.setBounds(715, 505, 175, 30);

        player1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        score.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        computer.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        reset.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        back.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        add(player1);
        add(score);
        add(computer);
        add(reset);
        add(back);

        back.addActionListener(e -> Choose.OpenMenu());
        reset.addActionListener(e -> Choose.OpenTwoPlayersPanel());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(50, 50, 560, 560);
        for (int index1 = 70; index1 < 590; index1 += 65)
            for (int index2 = 70; index2 < 590; index2 += 65)
                g.drawRect(index1, index2, 65, 65);
    }
}
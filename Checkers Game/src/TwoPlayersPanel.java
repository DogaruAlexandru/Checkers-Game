import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class TwoPlayersPanel extends JPanel {
    private final MyBoard board;
    private final JLabel player1;
    private final JLabel player2;
    private final JLabel score;
    private final JButton reset;
    private final JButton back;

    public TwoPlayersPanel() {
        setLayout(null);

        board = new MyBoard();
        player1 = new JLabel("Player1");
        score = new JLabel("0-0");
        player2 = new JLabel("Player2");
        reset = new JButton("Reset");
        back = new JButton("Back");

        player1.setBounds(730, 150, 180, 50);
        score.setBounds(765, 200, 55, 50);
        player2.setBounds(728, 242, 180, 50);
        reset.setBounds(715, 470, 175, 30);
        back.setBounds(715, 505, 175, 30);

        player1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        score.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        player2.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        reset.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        back.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        player1.setForeground(Color.black);
        score.setForeground(Color.black);
        player2.setForeground(Color.black);

        add(player1);
        add(score);
        add(player2);
        add(reset);
        add(back);

        back.addActionListener(e -> Choose.OpenMenu());
        reset.addActionListener(e -> Choose.OpenOnePlayerPanel());
    }

    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(Choose.background, 0, 0, null);

        g.setColor(Choose.myBrown);
        g.fillRect(50, 50, 560, 560);
//        g.setColor(Choose.myBlack);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 560, 560);
        for (int index1 = 70; index1 < 590; index1 += 65)
            for (int index2 = 70; index2 < 590; index2 += 65) {
                if (board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].isBlack()) {
//                    g.setColor(Choose.myBlack);
                    g.setColor(Color.BLACK);
                    g.fillRect(index1, index2, 65, 65);
                } else {
                    g.setColor(Choose.myWhite);
                    g.fillRect(index1, index2, 65, 65);
                }
                if (board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].getPiece() != null) {
                    board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].getPiece().drawPiece(g, index1, index2);
                }
            }
        g.setColor(Color.BLACK);
        g.drawRect(70, 70, 520, 520);
    }
}
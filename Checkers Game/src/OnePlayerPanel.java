import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class OnePlayerPanel extends JPanel {
    private final MyBoard board;
    private final JLabel player1;
    private final JLabel computer;
    private final JLabel score;
    private final JButton reset;
    private final JButton back;
    private Point click;

    public OnePlayerPanel() {
        setLayout(null);

        board = new MyBoard();
        player1 = new JLabel("Player1");
        score = new JLabel("0-0");
        computer = new JLabel("Computer");
        reset = new JButton("Reset");
        back = new JButton("Back");
        click = new Point();

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

        player1.setForeground(Color.BLACK);
        score.setForeground(Color.BLACK);
        computer.setForeground(Color.BLACK);

        add(player1);
        add(score);
        add(computer);
        add(reset);
        add(back);

        back.addActionListener(e -> Choose.OpenMenu());
        reset.addActionListener(e -> Choose.OpenTwoPlayersPanel());

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //todo reorganizat if urile
                if (click.x == 0 && click.y == 0) {
                    click = e.getPoint();
                    board.getBoardTable()[(click.x - 70) / 65][(click.y - 70) / 65].setSelected(true);
                    board.findPossibleMoves((click.x - 70) / 65, (click.y - 70) / 65);
                    board.findPossibleAttacks((click.x - 70) / 65, (click.y - 70) / 65);
                    //poate inca o poza pusa peste pisele pe care le iei
                } else {
                    if (board.getBoardTable()[(click.x - 70) / 65][(click.y - 70) / 65].isPossibleMove()) {
                        //todo implementeaza mutarea
                    } else {
                        click.x = 0;
                        click.y = 0;
                    }
                }
                board.resetFlags();
                repaint();
            }
        });
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
                if (board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].isSelected())
                    g.drawImage(Choose.selected, 0, 0, null);
                else if (board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].isPossibleMove())
                    g.drawImage(Choose.possible, 0, 0, null);

                if (board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].isBlack()) {
//                    g.setColor(Choose.myBlack);
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Choose.myWhite);
                }
                g.fillRect(index1, index2, 65, 65);

                if (board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].getPiece() != null) {
                    board.getBoardTable()[(index1 - 70) / 65][(index2 - 70) / 65].getPiece().drawPiece(g, index1, index2);
                }
            }
        g.setColor(Color.BLACK);
        g.drawRect(70, 70, 520, 520);
    }
}
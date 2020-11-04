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
    private boolean blackTurn;
    private Point click;

    public OnePlayerPanel() {
        setLayout(null);

        board = new MyBoard();
        player1 = new JLabel("Player1");
        score = new JLabel("0-0");
        computer = new JLabel("Computer");
        reset = new JButton("Reset");
        back = new JButton("Back");
        blackTurn = true;
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

        if (blackTurn) {//nu inteleg de ce indicii nu sunt invers
            board.getBoardTable()[1][2].setSelected(true);
            board.getBoardTable()[3][2].setSelected(true);
            board.getBoardTable()[5][2].setSelected(true);
            board.getBoardTable()[7][2].setSelected(true);
        } else {
            board.getBoardTable()[0][5].setSelected(true);
            board.getBoardTable()[2][5].setSelected(true);
            board.getBoardTable()[4][5].setSelected(true);
            board.getBoardTable()[6][5].setSelected(true);
        }

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 70 && e.getY() > 70 && e.getX() < 590 && e.getY() < 590)
                    if (board.getBoardTable()[(e.getX() - 70) / 65][(e.getY() - 70) / 65].getPiece() != null) {
                        if (blackTurn) {
                            if (click.x == 0 && click.y == 0) {
                                click = e.getPoint();
                                board.getBoardTable()[(click.x - 70) / 65][(click.y - 70) / 65].setSelected(true);
                                board.findPossibleMoves((click.x - 70) / 65, (click.y - 70) / 65);
                                board.findPossibleAttacks((click.x - 70) / 65, (click.y - 70) / 65);
                            } else {
                                if (board.getBoardTable()[(click.x - 70) / 65][(click.y - 70) / 65].isPossibleMove()) {
                                    //todo implementeaza mutarea

                                } else {
                                    click.x = 0;
                                    click.y = 0;
                                }
                            }
                            //blackTurn
                            board.resetFlags();
                            repaint();
                        } else ;
                    }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(Choose.background, 0, 0, null);

        g.setColor(Choose.myBrown);
        g.fillRect(50, 50, 560, 560);
        g.setColor(Choose.myBlack);
        //g.setColor(Color.BLACK);
        g.drawRect(50, 50, 560, 560);
        for (int xIndex = 70; xIndex < 590; xIndex += 65)
            for (int yIndex = 70; yIndex < 590; yIndex += 65) {
                if (board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].isBlack())
                    g.setColor(Choose.myBlack);
                else
                    g.setColor(Choose.myWhite);
                g.fillRect(xIndex, yIndex, 65, 65);

                if (board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].isSelected())
                    g.drawImage(Choose.selected, xIndex, yIndex, null);
                else if (board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].isPossibleMove())
                    g.drawImage(Choose.possible, xIndex, yIndex, null);

                if (board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].getPiece() != null) {
                    board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].getPiece().drawPiece(g, xIndex, yIndex);
                }
            }
        g.setColor(Color.BLACK);
        g.drawRect(70, 70, 520, 520);
    }
}
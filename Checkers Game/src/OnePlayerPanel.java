import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class OnePlayerPanel extends JPanel {
    private MyBoard board;
    private final JLabel score;
    private final JButton restart;
    private final JButton reset;
    private final JButton back;
    private final JLabel endLabel;
    private int redScore;
    private int blackScore;
    private final JLabel youLabel;
    private final JLabel computerLabel;

    public OnePlayerPanel() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));

        youLabel = new JLabel("You");
        computerLabel = new JLabel("PC");
        board = new MyBoard(youLabel, computerLabel);
        score = new JLabel(blackScore + ":" + redScore);
        restart = new JButton("Restart game");
        endLabel = new JLabel();
        reset = new JButton("Reset score");
        back = new JButton("Back");
        redScore = 0;
        blackScore = 0;

        score.setBounds(765, 195, 55, 50);
        restart.setBounds(715, 435, 155, 30);
        reset.setBounds(715, 470, 155, 30);
        back.setBounds(715, 505, 155, 30);
        youLabel.setBounds(755, 150, 180, 50);
        computerLabel.setBounds(768, 242, 180, 50);

        score.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        restart.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        reset.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        back.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        endLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 70));

        score.setForeground(Color.BLACK);
        endLabel.setForeground(Color.CYAN);

        add(score);
        add(restart);
        add(reset);
        add(back);
        add(youLabel);
        add(computerLabel);
        add(endLabel);

        endLabel.setVisible(false);
        board.possibilities();

        back.addActionListener(e -> Choose.OpenMenu());
        reset.addActionListener(e -> {
            resetScore();
            repaint();
        });
        restart.addActionListener(e -> {
            restart();
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!board.isGameEnded()) {
                    if (e.getX() > 70 && e.getY() > 70 && e.getX() < 590 && e.getY() < 590) {
                        int mouseXIndex = board.fromXCoordinateToXIndex(e.getX());
                        int mouseYIndex = board.fromYCoordinateToYIndex(e.getY());
                        if (board.getBoardTable()[mouseXIndex][mouseYIndex].isPossibleMove()) {
                            if (board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece() != null) {
                                board.selectPieceToUse(mouseXIndex, mouseYIndex);
                            } else {
                                board.executeMove(mouseXIndex, mouseYIndex);
                            }
                        } else if (board.getBoardTable()[mouseXIndex][mouseYIndex].isPossibleAttack()) {
                            board.executeAttack(mouseXIndex, mouseYIndex);
                        } else {
                            board.resetFlags();
                            board.possibilities();
                        }
                        if (board.isGameEnded()) {
                            changeScore();
                            score.setText(blackScore + ":" + redScore);
                        }
                        repaint();
                    }
                }
            }
        });
    }

//    private void AI() {
//        for (int index1 = 0; index1 < 8; ++index1)
//            for (int index2 = 0; index2 < 8; ++index2) {
//                if (board.getBoardTable()[index1][index2].isPossibleAttack()) {
//
//                    return;
//                }
//                if (board.getBoardTable()[index1][index2].isPossibleMove()) {
//
//                    return;
//                }
//            }
//    }

    private void restart() {
        remove(youLabel);
        remove(computerLabel);
        board = new MyBoard(youLabel, computerLabel);
        board.possibilities();
        youLabel.setBounds(755, 150, 180, 50);
        computerLabel.setBounds(768, 242, 180, 50);
        add(youLabel);
        add(computerLabel);
        endLabel.setVisible(false);
    }

    private void resetScore() {
        redScore = 0;
        blackScore = 0;
        score.setText(blackScore + ":" + redScore);
    }

    private void changeScore() {
        if (!board.isBlackTurn())
            ++blackScore;
        else
            ++redScore;
    }

    private void drawEndScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(100, 150, 460, 290);
        g.setColor(Choose.myWhite);
        g.fillRect(120, 170, 420, 250);
        g.setColor(Color.BLACK);
        g.drawRect(120, 170, 420, 250);

        endLabel.setVisible(true);
        if (!board.isBlackTurn()) {
            endLabel.setBounds(175, 170, 420, 250);
            endLabel.setText("You Won!");
        } else {
            endLabel.setBounds(190, 170, 420, 250);
            endLabel.setText("PC Won!");
        }
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(Choose.background, 0, 0, null);
        board.draw(g);
        if (board.isGameEnded())
            drawEndScreen(g);
    }
}
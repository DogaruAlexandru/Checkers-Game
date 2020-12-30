import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class TwoPlayersPanel extends JPanel {
    protected MyBoard board;
    protected  JLabel score;
    protected  JLabel blackLabel;
    protected  JLabel redLabel;
    protected JLabel endLabel;

    protected  JButton restart;
    protected  JButton reset;
    protected  JButton back;

    protected int redScore;
    protected int blackScore;

    public TwoPlayersPanel() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));

        blackLabel = new JLabel("Black");
        redLabel = new JLabel("Red");
        board = new MyBoard(blackLabel, redLabel);
        score = new JLabel(blackScore + ":" + redScore);
        restart = new JButton("Restart game");
        reset = new JButton("Reset score");
        back = new JButton("Back");
        endLabel = new JLabel();
        redScore = 0;
        blackScore = 0;

        score.setBounds(765, 195, 55, 50);
        restart.setBounds(715, 435, 155, 30);
        reset.setBounds(715, 470, 155, 30);
        back.setBounds(715, 505, 155, 30);
        blackLabel.setBounds(742, 150, 180, 50);
        redLabel.setBounds(757, 242, 180, 50);

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
        add(blackLabel);
        add(redLabel);
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

    public TwoPlayersPanel(boolean b) {
    }

    protected void restart() {
        remove(blackLabel);
        remove(redLabel);
        board = new MyBoard(blackLabel, redLabel);
        board.possibilities();
        blackLabel.setBounds(742, 150, 180, 50);
        redLabel.setBounds(757, 242, 180, 50);
        add(blackLabel);
        add(redLabel);
        endLabel.setVisible(false);
    }

    protected void resetScore() {
        redScore = 0;
        blackScore = 0;
        score.setText(blackScore + ":" + redScore);
    }

    protected void changeScore() {
        if (!board.isBlackTurn())
            ++blackScore;
        else
            ++redScore;
    }

    protected void drawEndScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(100, 150, 460, 290);
        g.setColor(Choose.myWhite);
        g.fillRect(120, 170, 420, 250);
        g.setColor(Color.BLACK);
        g.drawRect(120, 170, 420, 250);

        endLabel.setVisible(true);
        if (!board.isBlackTurn()) {
            endLabel.setBounds(150, 170, 420, 250);
            endLabel.setText("Black Won!");
        } else {
            endLabel.setBounds(180, 170, 420, 250);
            endLabel.setText("Red Won!");
        }
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(Choose.background, 0, 0, null);
        board.draw(g);
        if (board.isGameEnded())
            drawEndScreen(g);
    }
}
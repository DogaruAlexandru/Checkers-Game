import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class OnePlayerPanel extends TwoPlayersPanel {

    public OnePlayerPanel() {
        super(false);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));

        blackLabel = new JLabel("Black");
        redLabel = new JLabel("Red");
        board = new MyBoard(blackLabel, redLabel);
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
                if (!board.isGameEnded())
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
                        if (!board.isBlackTurn() && !board.isGameEnded())
                            AIPLay();
                        repaint();
                    }
            }
        });
    }

    private void AIPLay() {
        while (!board.isBlackTurn()) {
            Point currentPosition = AIChoice();
            if (board.getBoardTable()[currentPosition.x][currentPosition.y].isPossibleMove()) {
                if (board.getBoardTable()[currentPosition.x][currentPosition.y].getPiece() != null) {
                    board.selectPieceToUse(currentPosition.x, currentPosition.y);
                } else {
                    board.executeMove(currentPosition.x, currentPosition.y);
                }
            } else if (board.getBoardTable()[currentPosition.x][currentPosition.y].isPossibleAttack()) {
                board.executeAttack(currentPosition.x, currentPosition.y);
            } else {
                board.resetFlags();
                board.possibilities();
            }
            if (board.isGameEnded()) {
                changeScore();
                score.setText(blackScore + ":" + redScore);
            }
            paintImmediately(70, 70, 520, 520);
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    private Point AIChoice() {
        Vector<Point> options = new Vector<>();
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2) {
                if (board.getBoardTable()[index1][index2].isPossibleAttack())
                    options.add(new Point(index1, index2));
                if (board.getBoardTable()[index1][index2].isPossibleMove())
                    options.add(new Point(index1, index2));
            }
        int index = (int) (Math.random() * options.size());
        return options.elementAt(index);
    }
}



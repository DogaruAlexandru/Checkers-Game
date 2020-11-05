import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class OnePlayerPanel extends JPanel {
    private MyBoard board;
    private final JLabel player1;
    private final JLabel computer;
    private final JLabel score;
    private final JButton reset;
    private final JButton back;
    private boolean blackTurn;
    private boolean gameEnded;
    private int clickXIndex;
    private int clickYIndex;

    public OnePlayerPanel() {
        setLayout(null);

        board = new MyBoard();
        player1 = new JLabel("Player1");
        score = new JLabel("0-0");
        computer = new JLabel("Computer");
        reset = new JButton("Reset");
        back = new JButton("Back");
        blackTurn = true;
        gameEnded = false;

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
        reset.addActionListener(e -> reset());

        if (blackTurn) {//nu inteleg de ce indicii nu sunt invers
            board.getBoardTable()[1][2].setPossibleMove(true);
            board.getBoardTable()[3][2].setPossibleMove(true);
            board.getBoardTable()[5][2].setPossibleMove(true);
            board.getBoardTable()[7][2].setPossibleMove(true);
        } else {
            board.getBoardTable()[0][5].setPossibleMove(true);
            board.getBoardTable()[2][5].setPossibleMove(true);
            board.getBoardTable()[4][5].setPossibleMove(true);
            board.getBoardTable()[6][5].setPossibleMove(true);
        }

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 70 && e.getY() > 70 && e.getX() < 590 && e.getY() < 590) {
                    int mouseXIndex = fromXCoordinateToXIndex(e.getX());
                    int mouseYIndex = fromYCoordinateToYIndex(e.getY());
                    //intra cand se alege piesa care se doreste mutata
                    if (board.getBoardTable()[mouseXIndex][mouseYIndex].isPossibleMove())
                        if (board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece() != null) {
                            clickXIndex = mouseXIndex;
                            clickYIndex = mouseYIndex;
                            board.resetFlags();
                            board.getBoardTable()[clickXIndex][clickYIndex].setSelected(true);
                            if (board.existPossibleAttacks(clickXIndex, clickYIndex))
                                board.findPossibleAttacks(clickXIndex, clickYIndex);
                            else
                                board.findPossibleMoves(clickXIndex, clickYIndex);
                        } else {
                            //intra cand se doreste mutarea piesei gasite anterior
                            board.move(clickXIndex, clickYIndex, mouseXIndex, mouseYIndex);
                            if ((mouseYIndex == 0 || mouseYIndex == 7) &&
                                    !board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece().isKinged())
                                makeKing(mouseXIndex, mouseYIndex);
                            board.resetFlags();
                            blackTurn = !blackTurn;
                            possibilities();
                        }
                    else if (board.getBoardTable()[mouseXIndex][mouseYIndex].isPossibleAttack()) {
                        //intra cand se doreste atacul piesei gasite anterior
                        board.attack(clickXIndex, clickYIndex, mouseXIndex, mouseYIndex);
                        if ((mouseYIndex == 0 || mouseYIndex == 7) &&
                                !board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece().isKinged())
                            makeKing(mouseXIndex, mouseYIndex);
                        board.resetFlags();
                        if (board.existPossibleAttacks(mouseXIndex, mouseYIndex)) {
                            //intra daca se mai poate ataca in aceasi tura cu aceasta piesa
                            clickXIndex = mouseXIndex;
                            clickYIndex = mouseYIndex;
                            board.getBoardTable()[mouseXIndex][mouseYIndex].setSelected(true);
                            board.findPossibleAttacks(mouseXIndex, mouseYIndex);
                        } else {
                            blackTurn = !blackTurn;
                            possibilities();
                        }
                    } else {
                        board.resetFlags();
                        possibilities();
                    }
                    repaint();
                }
            }
        });
    }

    private void makeKing(int mouseXIndex, int mouseYIndex) {
        if (board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece().isBlack()) {
            if (mouseYIndex == 7)
                board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece().setKinged(true);
        } else if (mouseYIndex == 0)
            board.getBoardTable()[mouseXIndex][mouseYIndex].getPiece().setKinged(true);
    }

    private void restart() {
        board=new MyBoard();
        blackTurn = true;
        gameEnded = false;
        repaint();
    }

    private void reset() {
        resetScore();
        restart();
    }

    private void resetScore() {
    }

    private void possibilities() {
        boolean attackFound;
        boolean moveFound = false;
        attackFound = searchPossibleAttackingPieces();
        if (!attackFound)
            moveFound = searchPossibleMovingPieces();
        gameEnded = isGameEnded(attackFound, moveFound);
    }

    private boolean isGameEnded(boolean attackFound, boolean moveFound) {
        if (!moveFound && !attackFound) {
            if (blackTurn)
                System.out.println("Red won!");
            else
                System.out.println("Black won!");
            return true;
        }
        if (board.getRedPieces() == 0) {
            System.out.println("Black won!");
            return true;
        }
        if (board.getBlackPieces() == 0) {
            System.out.println("Red won!");
            return true;
        }
        return false;
    }

    private int fromXCoordinateToXIndex(int xCoordinate) {
        return (xCoordinate - 70) / 65;
    }

    private int fromYCoordinateToYIndex(int yCoordinate) {
        return (yCoordinate - 70) / 65;
    }

    private boolean searchPossibleAttackingPieces() {
        boolean attackFound;
        attackFound = false;
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if (board.getBoardTable()[index1][index2].getPiece() != null &&
                        board.getBoardTable()[index1][index2].getPiece().isBlack() == blackTurn)
                    if (board.existPossibleAttacks(index1, index2)) {
                        board.getBoardTable()[index1][index2].setPossibleMove(true);
                        attackFound = true;
                    }
        return attackFound;
    }

    private boolean searchPossibleMovingPieces() {
        boolean moveFound = false;
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if (board.getBoardTable()[index1][index2].getPiece() != null &&
                        board.getBoardTable()[index1][index2].getPiece().isBlack() == blackTurn)
                    if (board.existPossibleMoves(index1, index2)) {
                        board.getBoardTable()[index1][index2].setPossibleMove(true);
                        moveFound = true;
                    }
        return moveFound;
    }

    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(Choose.background, 0, 0, null);

        g.setColor(Choose.myBrown);
        g.fillRect(50, 50, 560, 560);
        g.setColor(Choose.myBlack);
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
                else if (board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].isPossibleMove() ||
                        board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].isPossibleAttack())
                    g.drawImage(Choose.possible, xIndex, yIndex, null);

                if (board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].getPiece() != null) {
                    board.getBoardTable()[(xIndex - 70) / 65][(yIndex - 70) / 65].getPiece().drawPiece(g, xIndex, yIndex);
                }
            }
        g.setColor(Color.BLACK);
        g.drawRect(70, 70, 520, 520);
    }
}
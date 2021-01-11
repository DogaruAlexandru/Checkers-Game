import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class OnePlayerPanel extends TwoPlayersPanel {

    public OnePlayerPanel() {
        super();
    }

    @Override
    protected void Pressing(MouseEvent e) {
        if (!board.isGameEnded())
            if (e.getX() > 70 && e.getY() > 70 && e.getX() < 590 && e.getY() < 590) {
                Play(board.fromXCoordinateToXIndex(e.getX()), board.fromYCoordinateToYIndex(e.getY()));
                if (!board.isBlackTurn() && !board.isGameEnded())
                    AIPLay();
                repaint();
            }
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
                ChangeScore();
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



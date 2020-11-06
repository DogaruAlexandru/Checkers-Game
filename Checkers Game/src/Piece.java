import java.awt.Graphics;

public class Piece {
    private final boolean black;
    private boolean kinged;

    public Piece(boolean black) {
        this.black = black;
        kinged = false;
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isKinged() {
        return kinged;
    }

    public void setKinged(boolean kinged) {
        this.kinged = kinged;
    }

    public void drawPiece(Graphics g, int x, int y) {
        if (black) {
            if (kinged)
                g.drawImage(Choose.blackKingedPiece, x, y, null);
            else
                g.drawImage(Choose.blackPiece, x, y, null);
        } else if (kinged)
            g.drawImage(Choose.redKingedPiece, x, y, null);
        else
            g.drawImage(Choose.redPiece, x, y, null);
    }
}
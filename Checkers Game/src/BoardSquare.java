public class BoardSquare {
    private final boolean black;
    private boolean possibleMove;
    private boolean possibleAttack;
    private boolean selected;
    private Piece piece;

    BoardSquare(boolean black) {
        this.black = black;
        possibleMove = false;
        possibleAttack = false;
        selected = false;
        piece = null;
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isPossibleMove() {
        return possibleMove;

    }

    public boolean isPossibleAttack() {
        return possibleAttack;
    }

    public boolean isSelected() {
        return selected;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPossibleMove(boolean possibleMove) {
        this.possibleMove = possibleMove;
    }

    public void setPossibleAttack(boolean possibleAttack) {
        this.possibleMove = possibleAttack;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}

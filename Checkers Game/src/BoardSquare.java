public class BoardSquare {
    private final boolean black;
    private boolean possibleMove;
    private Piece piece;

    //nesigur de utilizare
    //variabila care ajuta la gasirea miscarilor posibile

    BoardSquare(boolean black) {
        this.black = black;
        possibleMove = false;//ajutor la afisare
        piece = null;
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isPossibleMove() {
        return possibleMove;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPossibleMove(boolean possibleMove) {
        this.possibleMove = possibleMove;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}

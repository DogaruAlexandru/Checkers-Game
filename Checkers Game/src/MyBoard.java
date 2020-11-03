public class MyBoard {
    private BoardSquare[][] boardTable;
    private int whitePiecesLeft;
    private int blackPiecesLeft;

    public MyBoard() {
        whitePiecesLeft = 12;
        blackPiecesLeft = 12;

        boardTable = new BoardSquare[8][8];
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if ((index1 + index2) % 2 == 0)
                    boardTable[index1][index2] = new BoardSquare(false);
                else
                    boardTable[index1][index2] = new BoardSquare(true);

        for (int posY = 0; posY < 3; ++posY)
            for (int posX = 0; posX < 8; ++posX)
                if ((posY + posX) % 2 == 0)
                    boardTable[posX][posY + 5].setPiece(new Piece(false));
                else
                    boardTable[posX][posY].setPiece(new Piece(true));

    }

    public BoardSquare[][] getBoardTable() {
        return boardTable;
    }

    private void findPossibleMovesDown(int xIndex, int yIndex) {
        if (yIndex < 8) {
            if (xIndex > 0 && boardTable[xIndex - 1][yIndex + 1].getPiece() == null)
                boardTable[xIndex - 1][yIndex + 1].setPossibleMove(true);
            if (xIndex < 8 && boardTable[xIndex + 1][yIndex + 1].getPiece() == null)
                boardTable[xIndex + 1][yIndex + 1].setPossibleMove(true);
        }
    }

    private void findPossibleMovesUp(int xIndex, int yIndex) {
        if (yIndex > 0) {
            if (xIndex > 0 && boardTable[xIndex - 1][yIndex - 1].getPiece() == null)
                boardTable[xIndex - 1][yIndex - 1].setPossibleMove(true);
            if (xIndex < 8 && boardTable[xIndex + 1][yIndex - 1].getPiece() == null)
                boardTable[xIndex + 1][yIndex - 1].setPossibleMove(true);
        }
    }

    public void findPossibleMoves(int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].getPiece().isKinged()) {
            findPossibleMovesDown(xIndex, yIndex);
            findPossibleMovesUp(xIndex, yIndex);
        } else {
            if (boardTable[xIndex][yIndex].getPiece().isBlack())
                findPossibleMovesDown(xIndex, yIndex);
            else
                findPossibleMovesUp(xIndex, yIndex);
        }
    }

    private void findPossibleAttacksDown(int xIndex, int yIndex) {
        if (yIndex < 7) {
            if (xIndex > 1 && boardTable[xIndex - 2][yIndex + 2].getPiece() == null &&
                    boardTable[xIndex - 1][yIndex + 1].getPiece() != null &&
                    boardTable[xIndex - 1][yIndex + 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex - 2][yIndex + 2].setPossibleMove(true);
            if (xIndex < 7 && boardTable[xIndex + 2][yIndex + 2].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex + 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex + 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex + 2][yIndex + 2].setPossibleMove(true);
        }
    }

    private void findPossibleAttacksUp(int xIndex, int yIndex) {
        if (yIndex > 1) {
            if (xIndex > 1 && boardTable[xIndex - 2][yIndex - 2].getPiece() == null &&
                    boardTable[xIndex - 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex - 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex - 2][yIndex - 2].setPossibleMove(true);
            if (xIndex < 7 && boardTable[xIndex + 1][yIndex - 1].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex + 2][yIndex - 2].setPossibleMove(true);
        }
    }

    public void findPossibleAttacks(int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].getPiece().isKinged()) {
            findPossibleAttacksDown(xIndex, yIndex);
            findPossibleAttacksUp(xIndex, yIndex);
        } else {
            if (boardTable[xIndex][yIndex].getPiece().isBlack())
                findPossibleAttacksDown(xIndex, yIndex);
            else
                findPossibleAttacksUp(xIndex, yIndex);
        }
    }

    public void resetFlags() {
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2) {
                boardTable[index1][index2].setPossibleMove(false);
                boardTable[index1][index2].setSelected(false);
            }
    }
}

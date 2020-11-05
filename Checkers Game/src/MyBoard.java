import java.util.Vector;

public class MyBoard {
    private BoardSquare[][] boardTable;
    private Vector<Piece> redPieces;
    private Vector<Piece> blackPieces;

    public MyBoard() {
        redPieces = new Vector<>(12);
        blackPieces = new Vector<>(12);

        boardTable = new BoardSquare[8][8];
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if ((index1 + index2) % 2 == 0)
                    boardTable[index1][index2] = new BoardSquare(false);
                else
                    boardTable[index1][index2] = new BoardSquare(true);
        for (int posY = 0; posY < 3; ++posY)
            for (int posX = 0; posX < 8; ++posX)
                if ((posY + posX) % 2 == 0) {
                    boardTable[posX][posY + 5].setPiece(new Piece(false));
                    redPieces.add(boardTable[posX][posY + 5].getPiece());
                } else {
                    boardTable[posX][posY].setPiece(new Piece(true));
                    blackPieces.add(boardTable[posX][posY].getPiece());
                }
    }

    public BoardSquare[][] getBoardTable() {
        return boardTable;
    }

    public Vector<Piece> getRedPieces() {
        return redPieces;
    }

    public Vector<Piece> getBlackPieces() {
        return blackPieces;
    }

    private void findPossibleMovesDown(int xIndex, int yIndex) {
        if (yIndex < 7) {
            if (xIndex > 0 && boardTable[xIndex - 1][yIndex + 1].getPiece() == null)
                boardTable[xIndex - 1][yIndex + 1].setPossibleMove(true);
            if (xIndex < 7 && boardTable[xIndex + 1][yIndex + 1].getPiece() == null)
                boardTable[xIndex + 1][yIndex + 1].setPossibleMove(true);
        }
    }

    private void findPossibleMovesUp(int xIndex, int yIndex) {
        if (yIndex > 0) {
            if (xIndex > 0 && boardTable[xIndex - 1][yIndex - 1].getPiece() == null)
                boardTable[xIndex - 1][yIndex - 1].setPossibleMove(true);
            if (xIndex < 7 && boardTable[xIndex + 1][yIndex - 1].getPiece() == null)
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
        if (yIndex < 6) {
            if (xIndex > 1 && boardTable[xIndex - 2][yIndex + 2].getPiece() == null &&
                    boardTable[xIndex - 1][yIndex + 1].getPiece() != null &&
                    boardTable[xIndex - 1][yIndex + 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex - 2][yIndex + 2].setPossibleAttack(true);
            if (xIndex < 6 && boardTable[xIndex + 2][yIndex + 2].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex + 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex + 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex + 2][yIndex + 2].setPossibleAttack(true);
        }
    }

    private void findPossibleAttacksUp(int xIndex, int yIndex) {
        if (yIndex > 1) {
            if (xIndex > 1 && boardTable[xIndex - 2][yIndex - 2].getPiece() == null &&
                    boardTable[xIndex - 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex - 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex - 2][yIndex - 2].setPossibleAttack(true);
            if (xIndex < 6 && boardTable[xIndex + 1][yIndex - 1].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex + 2][yIndex - 2].setPossibleAttack(true);
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

    private boolean existPossibleAttacksDown(int xIndex, int yIndex) {
        if (yIndex < 6) {
            if (xIndex > 1 && boardTable[xIndex - 2][yIndex + 2].getPiece() == null &&
                    boardTable[xIndex - 1][yIndex + 1].getPiece() != null &&
                    boardTable[xIndex - 1][yIndex + 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                return true;
            return xIndex < 6 && boardTable[xIndex + 2][yIndex + 2].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex + 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex + 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack();
        }
        return false;
    }

    private boolean existPossibleAttacksUp(int xIndex, int yIndex) {
        if (yIndex > 1) {
            if (xIndex > 1 && boardTable[xIndex - 2][yIndex - 2].getPiece() == null &&
                    boardTable[xIndex - 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex - 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                return true;
            return xIndex < 6 && boardTable[xIndex + 1][yIndex - 1].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack();
        }
        return false;
    }

    public boolean existPossibleAttacks(int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].getPiece().isKinged())
            return (existPossibleAttacksDown(xIndex, yIndex) || existPossibleAttacksUp(xIndex, yIndex));

        else if (boardTable[xIndex][yIndex].getPiece().isBlack())
            return existPossibleAttacksDown(xIndex, yIndex);
        else
            return existPossibleAttacksUp(xIndex, yIndex);
    }

    private boolean existPossibleMovesDown(int xIndex, int yIndex) {
        if (yIndex < 7) {
            if (xIndex > 0 && boardTable[xIndex - 1][yIndex + 1].getPiece() == null)
                return true;
            return xIndex < 7 && boardTable[xIndex + 1][yIndex + 1].getPiece() == null;
        }
        return false;
    }

    private boolean existPossibleMovesUp(int xIndex, int yIndex) {
        if (yIndex > 0) {
            if (xIndex > 0 && boardTable[xIndex - 1][yIndex - 1].getPiece() == null)
                return true;
            return xIndex < 7 && boardTable[xIndex + 1][yIndex - 1].getPiece() == null;
        }
        return false;
    }

    public boolean existPossibleMoves(int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].getPiece().isKinged())
            return (existPossibleMovesDown(xIndex, yIndex) || existPossibleMovesUp(xIndex, yIndex));

        else if (boardTable[xIndex][yIndex].getPiece().isBlack())
            return existPossibleMovesDown(xIndex, yIndex);
        else
            return existPossibleMovesUp(xIndex, yIndex);
    }

    public void resetFlags() {
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2) {
                boardTable[index1][index2].setPossibleMove(false);
                boardTable[index1][index2].setPossibleAttack(false);
                boardTable[index1][index2].setSelected(false);
            }
    }

    public void move(int xIndexOld, int yIndexOld, int xIndexNew, int yIndexNew) {
        boardTable[xIndexNew][yIndexNew].setPiece(boardTable[xIndexOld][yIndexOld].getPiece());
        boardTable[xIndexOld][yIndexOld].setPiece(null);
    }

    public void attack(int xIndexOld, int yIndexOld, int xIndexNew, int yIndexNew) {
        boardTable[xIndexNew][yIndexNew].setPiece(boardTable[xIndexOld][yIndexOld].getPiece());
        boardTable[xIndexOld][yIndexOld].setPiece(null);
        if (boardTable[(xIndexOld - xIndexNew) / 2 + xIndexNew][(yIndexOld - yIndexNew) / 2 + yIndexNew].getPiece().isBlack())
            blackPieces.remove(boardTable[(xIndexOld - xIndexNew) / 2 + xIndexNew][(yIndexOld - yIndexNew) / 2 + yIndexNew].getPiece());
        else
            redPieces.remove(boardTable[(xIndexOld - xIndexNew) / 2 + xIndexNew][(yIndexOld - yIndexNew) / 2 + yIndexNew].getPiece());
        boardTable[(xIndexOld - xIndexNew) / 2 + xIndexNew][(yIndexOld - yIndexNew) / 2 + yIndexNew].setPiece(null);
    }
}
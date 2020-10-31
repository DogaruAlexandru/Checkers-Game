public class MyBoard {
    private BoardSquare[][] boardTable;
    private byte whitePiecesLeft;
    private byte blackPiecesLeft;

    public MyBoard() {
        whitePiecesLeft = 12;
        blackPiecesLeft = 12;

        boardTable = new BoardSquare[8][8];
        for (byte index1 = 0; index1 < 8; ++index1)
            for (byte index2 = 0; index2 < 8; ++index2)
                if ((index1 + index2) % 2 == 0)
                    boardTable[index1][index2] = new BoardSquare(false);
                else
                    boardTable[index1][index2] = new BoardSquare(true);

        for (byte posY = 0; posY < 3; ++posY)
            for (byte posX = 0; posX < 8; ++posX)
                if ((posY + posX) % 2 == 0)
                    boardTable[posX][posY + 5].setPiece(new Piece(false, posX, posY + 5));
                else
                    boardTable[posX][posY].setPiece(new Piece(true, posX, posY));

    }
}

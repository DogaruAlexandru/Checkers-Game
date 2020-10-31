public class MyBoard {
    BoardSquare[][] boardTable;

    public MyBoard() {
        boardTable = new BoardSquare[8][8];
        byte index1, index2;
        for (index1 = 0; index1 < 8; index1 += 2)
            for (index2 = 0; index2 < 8; index2 += 2) {
                boardTable[index1][index2] = new BoardSquare(false);
                boardTable[index1][index2] = new BoardSquare(true);
            }
    }
}

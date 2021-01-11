import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

public class MyBoard {
    private final BoardSquare[][] boardTable;
    private int redPieces;
    private int blackPieces;
    private int clickXIndex;
    private int clickYIndex;
    private boolean blackTurn;
    private boolean gameEnded;
    private final JLabel firstLabel;
    private final JLabel secondLabel;

    public MyBoard(JLabel firstLabel, JLabel secondLabel) {
        redPieces = 12;
        blackPieces = 12;
        blackTurn = true;
        gameEnded = false;

        this.firstLabel = firstLabel;
        this.secondLabel = secondLabel;
        firstLabel .setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        secondLabel .setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        firstLabel.setForeground(Color.BLUE);
        secondLabel.setForeground(Color.BLACK);

        boardTable = new BoardSquare[8][8];
        createTiles();
        createPieces();
    }

    private void createPieces() {
        for (int posY = 0; posY < 3; ++posY)
            for (int posX = 0; posX < 8; ++posX)
                if ((posY + posX) % 2 == 0)
                    boardTable[posX][posY + 5].setPiece(new Piece(false));
                else
                    boardTable[posX][posY].setPiece(new Piece(true));
    }

    private void createTiles() {
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if ((index1 + index2) % 2 == 0)
                    boardTable[index1][index2] = new BoardSquare(false);
                else
                    boardTable[index1][index2] = new BoardSquare(true);
    }

    public BoardSquare[][] getBoardTable() {
        return boardTable;
    }

    public boolean isBlackTurn() {
        return blackTurn;
    }

    public boolean isGameEnded() {
        return gameEnded;
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
            if (xIndex < 6 && boardTable[xIndex + 2][yIndex - 2].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack())
                boardTable[xIndex + 2][yIndex - 2].setPossibleAttack(true);
        }
    }

    private void findPossibleAttacks(int xIndex, int yIndex) {
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
            return xIndex < 6 && boardTable[xIndex + 2][yIndex - 2].getPiece() == null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece() != null &&
                    boardTable[xIndex + 1][yIndex - 1].getPiece().isBlack() != boardTable[xIndex][yIndex].getPiece().isBlack();
        }
        return false;
    }

    private boolean existPossibleAttacks(int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].getPiece().isKinged())
            return (existPossibleAttacksDown(xIndex, yIndex) || existPossibleAttacksUp(xIndex, yIndex));
        if (boardTable[xIndex][yIndex].getPiece().isBlack())
            return existPossibleAttacksDown(xIndex, yIndex);
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

    private boolean existPossibleMoves(int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].getPiece().isKinged())
            return (existPossibleMovesDown(xIndex, yIndex) || existPossibleMovesUp(xIndex, yIndex));
        if (boardTable[xIndex][yIndex].getPiece().isBlack())
            return existPossibleMovesDown(xIndex, yIndex);
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

    private void move(int xIndexOld, int yIndexOld, int xIndexNew, int yIndexNew) {
        boardTable[xIndexNew][yIndexNew].setPiece(boardTable[xIndexOld][yIndexOld].getPiece());
        boardTable[xIndexOld][yIndexOld].setPiece(null);
    }

    private int getAttackedPieceXIndex(int xIndexOld, int xIndexNew) {
        return (xIndexOld - xIndexNew) / 2 + xIndexNew;
    }

    private int getAttackedPieceYIndex(int yIndexOld, int yIndexNew) {
        return (yIndexOld - yIndexNew) / 2 + yIndexNew;
    }

    private void attack(int xIndexOld, int yIndexOld, int xIndexNew, int yIndexNew) {
        boardTable[xIndexNew][yIndexNew].setPiece(boardTable[xIndexOld][yIndexOld].getPiece());
        boardTable[xIndexOld][yIndexOld].setPiece(null);
        int attackedPieceXIndex = getAttackedPieceXIndex(xIndexOld, xIndexNew);
        int attackedPieceYIndex = getAttackedPieceYIndex(yIndexOld, yIndexNew);
        if (boardTable[attackedPieceXIndex][attackedPieceYIndex].getPiece().isBlack())
            --blackPieces;
        else
            --redPieces;
        boardTable[attackedPieceXIndex][attackedPieceYIndex].setPiece(null);
    }

    public void executeAttack(int mouseXIndex, int mouseYIndex) {
        attack(clickXIndex, clickYIndex, mouseXIndex, mouseYIndex);
        if ((mouseYIndex == 0 || mouseYIndex == 7) && !boardTable[mouseXIndex][mouseYIndex].getPiece().isKinged())
            makeKing(mouseXIndex, mouseYIndex);
        resetFlags();
        if (existPossibleAttacks(mouseXIndex, mouseYIndex))
            setChainAttack(mouseXIndex, mouseYIndex);
        else {
            changeTurn(firstLabel, secondLabel);
            possibilities();
        }
    }

    private void setChainAttack(int mouseXIndex, int mouseYIndex) {
        clickXIndex = mouseXIndex;
        clickYIndex = mouseYIndex;
        getBoardTable()[mouseXIndex][mouseYIndex].setSelected(true);
        findPossibleAttacks(mouseXIndex, mouseYIndex);
    }

    public void executeMove(int mouseXIndex, int mouseYIndex) {
        move(clickXIndex, clickYIndex, mouseXIndex, mouseYIndex);
        if ((mouseYIndex == 0 || mouseYIndex == 7) && !boardTable[mouseXIndex][mouseYIndex].getPiece().isKinged())
            makeKing(mouseXIndex, mouseYIndex);
        resetFlags();
        changeTurn(firstLabel, secondLabel);
        possibilities();
    }

    public void possibilities() {
        boolean attackFound;
        boolean moveFound = false;
        attackFound = searchPossibleAttackingPieces();
        if (!attackFound)
            moveFound = searchPossibleMovingPieces();
        gameEnded = isGameEnded(attackFound, moveFound);
    }

    private boolean isGameEnded(boolean attackFound, boolean moveFound) {
        if (!moveFound && !attackFound)
            return true;
        if (redPieces == 0)
            return true;
        return blackPieces == 0;
    }

    public void selectPieceToUse(int mouseXIndex, int mouseYIndex) {
        clickXIndex = mouseXIndex;
        clickYIndex = mouseYIndex;
        resetFlags();
        boardTable[clickXIndex][clickYIndex].setSelected(true);
        if (existPossibleAttacks(clickXIndex, clickYIndex))
            findPossibleAttacks(clickXIndex, clickYIndex);
        else
            findPossibleMoves(clickXIndex, clickYIndex);
    }

    private void changeTurn(JLabel firstLabel, JLabel secondLabel) {
        blackTurn = !blackTurn;
        if (blackTurn) {
            firstLabel.setForeground(Color.BLUE);
            secondLabel.setForeground(Color.BLACK);
        } else {
            firstLabel.setForeground(Color.BLACK);
            secondLabel.setForeground(Color.BLUE);
        }
    }

    private boolean searchPossibleAttackingPieces() {
        boolean attackFound;
        attackFound = false;
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if (boardTable[index1][index2].getPiece() != null &&
                        boardTable[index1][index2].getPiece().isBlack() == blackTurn)
                    if (existPossibleAttacks(index1, index2)) {
                        boardTable[index1][index2].setPossibleMove(true);
                        attackFound = true;
                    }
        return attackFound;
    }

    private boolean searchPossibleMovingPieces() {
        boolean moveFound = false;
        for (int index1 = 0; index1 < 8; ++index1)
            for (int index2 = 0; index2 < 8; ++index2)
                if (boardTable[index1][index2].getPiece() != null &&
                        boardTable[index1][index2].getPiece().isBlack() == blackTurn)
                    if (existPossibleMoves(index1, index2)) {
                        boardTable[index1][index2].setPossibleMove(true);
                        moveFound = true;
                    }
        return moveFound;
    }

    private void makeKing(int mouseXIndex, int mouseYIndex) {
        if (boardTable[mouseXIndex][mouseYIndex].getPiece().isBlack()) {
            if (mouseYIndex == 7)
                boardTable[mouseXIndex][mouseYIndex].getPiece().setKinged(true);
        } else if (mouseYIndex == 0)
            boardTable[mouseXIndex][mouseYIndex].getPiece().setKinged(true);
    }

    public int fromXCoordinateToXIndex(int xCoordinate) {
        return (xCoordinate - 70) / 65;
    }

    public int fromYCoordinateToYIndex(int yCoordinate) {
        return (yCoordinate - 70) / 65;
    }

    public void draw(Graphics g) {
        drawBoardFrame(g);
        drawScoreFrame(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        for (int xCoordinate = 70; xCoordinate < 590; xCoordinate += 65)
            for (int yCoordinate = 70; yCoordinate < 590; yCoordinate += 65) {
                int xIndex = fromXCoordinateToXIndex(xCoordinate);
                int yIndex = fromYCoordinateToYIndex(yCoordinate);
                drawBoardTiles(g, xCoordinate, yCoordinate, xIndex, yIndex);
                drawPossibleBoardTiles(g, xCoordinate, yCoordinate, xIndex, yIndex);
                if (boardTable[xIndex][yIndex].getPiece() != null)
                    boardTable[xIndex][yIndex].getPiece().DrawPiece(g, xCoordinate, yCoordinate);
            }
        g.setColor(Color.BLACK);
        g.drawRect(70, 70, 520, 520);
    }

    private void drawPossibleBoardTiles(Graphics g, int xCoordinate, int yCoordinate, int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].isSelected())
            g.drawImage(Choose.selected, xCoordinate, yCoordinate, null);
        else if (boardTable[xIndex][yIndex].isPossibleMove() || boardTable[xIndex][yIndex].isPossibleAttack())
            g.drawImage(Choose.possible, xCoordinate, yCoordinate, null);
    }

    private void drawBoardTiles(Graphics g, int xCoordinate, int yCoordinate, int xIndex, int yIndex) {
        if (boardTable[xIndex][yIndex].isBlack())
            g.setColor(Choose.myBlack);
        else
            g.setColor(Choose.myWhite);
        g.fillRect(xCoordinate, yCoordinate, 65, 65);
    }

    private void drawScoreFrame(Graphics g) {
        g.setColor(Choose.myBrown);
        g.fillRect(700, 130, 185, 185);
        g.setColor(Color.BLACK);
        g.drawRect(700, 130, 185, 185);
        g.setColor(Color.BLACK);

        g.setColor(Choose.myWhite);
        g.fillRect(710, 140, 165, 165);
        g.setColor(Color.BLACK);
        g.drawRect(710, 140, 165, 165);
    }

    private void drawBoardFrame(Graphics g) {
        g.setColor(Choose.myBrown);
        g.fillRect(50, 50, 560, 560);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 560, 560);
    }
}
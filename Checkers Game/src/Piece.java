public class Piece {
    private final boolean black;
    private boolean kinged;
    private int posX;
    private int posY;

    public Piece(boolean black, int posX, int posY) {
        this.black = black;
        this.posX = posX;
        this.posY = posY;
        kinged = false;
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isKinged() {
        return kinged;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setKinged(boolean kinged) {
        this.kinged = kinged;
    }

    public void setPosX(byte posX) {
        this.posX = posX;
    }

    public void setPosY(byte posY) {
        this.posY = posY;
    }

    public void moveUpLeft() {
        --posX;
        --posY;
    }

    public void moveUpRight() {
        ++posX;
        --posY;
    }

    public void moveDowsLeft() {
        --posX;
        ++posY;
    }

    public void moveDownRight() {
        ++posX;
        ++posY;
    }

    public void attackMoveUpLeft() {
        posX -= 2;
        posY -= 2;
    }

    public void attackMoveUpRight() {
        posX += 2;
        posY -= 2;
    }

    public void attackMoveDowsLeft() {
        posX -= 2;
        posY += 2;
    }

    public void attackMoveDownRight() {
        posX += 2;
        posY += 2;
    }
}

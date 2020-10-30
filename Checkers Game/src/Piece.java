public class Piece {
    private final boolean color;//black==true, white==false
    private boolean kinged;
    private byte PosX;
    private byte PosY;

    public Piece(boolean color, byte PosX, byte PosY) {
        this.color = color;
        this.PosX = PosX;
        this.PosY = PosY;
        kinged = false;
    }

    public boolean isColor() {
        return color;
    }

    public boolean isKinged() {
        return kinged;
    }

    public byte getxPos() {
        return PosX;
    }

    public byte getyPos() {
        return PosY;
    }

    public void setKinged(boolean kinged) {
        this.kinged = kinged;
    }

    public void setPosX(byte PosX) {
        this.PosX = PosX;
    }

    public void setPosY(byte PosY) {
        this.PosY = PosY;
    }

    public void moveUpLeft() {
        --PosX;
        --PosY;
    }

    public void moveUpRight() {
        ++PosX;
        --PosY;
    }

    public void moveDowsLeft() {
        --PosX;
        ++PosY;
    }

    public void moveDownRight() {
        ++PosX;
        ++PosY;
    }

    public void attackMoveUpLeft() {
        PosX -= 2;
        PosY -= 2;
    }

    public void attackMoveUpRight() {
        PosX += 2;
        PosY -= 2;
    }

    public void attackMoveDowsLeft() {
        PosX -= 2;
        PosY += 2;
    }

    public void attackMoveDownRight() {
        PosX += 2;
        PosY += 2;
    }
}

public class Pawn {
    private int positionX;
    private int positionY;

    public Pawn(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pawn)) return false;

        Pawn pawn = (Pawn) o;

        if (positionX != pawn.positionX) return false;
        return positionY == pawn.positionY;
    }

    @Override
    public int hashCode() {
        int result = positionX;
        result = 31 * result + positionY;
        return result;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}

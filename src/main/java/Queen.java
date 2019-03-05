public class Queen {
    private int positionX;
    private int positionY;
    private int moves;

    private Queen(int positionX, int positionY, int moves) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.moves = moves;
    }


    public Queen(int positionX, int positionY) {
        this(positionX, positionY, 0);
    }

    public Queen beatPawn(Pawn pawn) {
        int deltaX = Math.abs(this.positionX - pawn.getPositionX());
        int deltaY = Math.abs(this.positionY - pawn.getPositionY());

        if (this.positionX == pawn.getPositionX()
                || this.positionY == pawn.getPositionY()
                || deltaX == deltaY) {
            return new Queen(pawn.getPositionX(), pawn.getPositionY(), 1);
        } else {
            return new Queen(pawn.getPositionX(), pawn.getPositionY(), 2);
        }
    }

    public int getMoves(){
        return this.moves;
    }

    @Override
    public String toString() {
        return "Queen{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                ", moves=" + moves +
                '}';
    }
}

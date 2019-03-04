import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Pawn> pawns = new HashSet<>();
        pawns.add(new Pawn(2, 2));
        pawns.add(new Pawn(4, 2));
        pawns.add(new Pawn(1, 4));
        pawns.add(new Pawn(3, 4));

        ChessBoard chessBoard = new ChessBoard(new Queen(1,1), pawns);
        App app = new App(chessBoard);
    }
}

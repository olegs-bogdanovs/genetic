import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(4, 0));
        pawns.add(new Pawn(7, 2));
        pawns.add(new Pawn(4, 5));
        pawns.add(new Pawn(6, 5));
        pawns.add(new Pawn(4, 7));
        pawns.add(new Pawn(1, 4));
        pawns.add(new Pawn(1, 2));
        pawns.add(new Pawn(0, 1));
        pawns.add(new Pawn(7, 4));
        pawns.add(new Pawn(4, 3));

        ChessBoard chessBoard = new ChessBoard(new Queen(0,0), pawns);
        App app = new App(chessBoard);
    }
}

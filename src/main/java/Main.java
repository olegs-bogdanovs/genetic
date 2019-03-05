import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Pawn(2, 2));
        pawns.add(new Pawn(5, 2));
        pawns.add(new Pawn(0, 7));
        pawns.add(new Pawn(4, 11));
        pawns.add(new Pawn(4, 9));
        pawns.add(new Pawn(7, 9));
        pawns.add(new Pawn(7, 15));
        pawns.add(new Pawn(5, 13));
        pawns.add(new Pawn(0, 13));
        pawns.add(new Pawn(0, 9));
        pawns.add(new Pawn(3, 7));
        pawns.add(new Pawn(3, 15));
        pawns.add(new Pawn(0, 15));
        pawns.add(new Pawn(8, 15));

        ChessBoard chessBoard = new ChessBoard(new Queen(0,0), pawns);

        //while(true) {
            new App(chessBoard);
        //}
    }
}

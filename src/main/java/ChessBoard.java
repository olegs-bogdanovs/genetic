import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessBoard {
    private Queen queen;
    private List<Pawn> pawns;

    public ChessBoard(Queen queen, List<Pawn> pawns) {
        this.queen = queen;
        this.pawns = pawns;
    }

    public Queen getQueen(){
        return this.queen;
    }

    public List<Pawn> getPawns(){
        return new ArrayList<>(pawns);
    }
}

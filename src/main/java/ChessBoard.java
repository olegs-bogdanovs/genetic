import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessBoard {
    private Queen queen;
    private Set<Pawn> pawns = new HashSet<>();

    public ChessBoard(Queen queen, Set<Pawn> pawns) {
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

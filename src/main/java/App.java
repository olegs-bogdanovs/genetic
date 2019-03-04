import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    private ChessBoard chessBoard;
    private final int ENTITY_COUNT = 100;
    private List<List<Pawn>> entities = new ArrayList<>();

    private List<List<Pawn>> generatePopulation(int count) {
        List<List<Pawn>> ents = new ArrayList<>();
        for (int i=0; i < ENTITY_COUNT; i++) {
            List<Pawn> entity = chessBoard.getPawns();
            Collections.shuffle(entity);
            ents.add(entity);
        }
        return ents;
    }


    public App(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.entities = generatePopulation(ENTITY_COUNT);
    }
}

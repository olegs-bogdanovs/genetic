import org.junit.Test;

import static org.junit.Assert.*;

public class QueenTest {
    @Test
    public void beatPawn() throws Exception {
        Queen queen = new Queen(2, 2);
        assertEquals(1, queen.beatPawn(new Pawn(1, 1)).getMoves());
        assertEquals(1, queen.beatPawn(new Pawn(3, 3)).getMoves());
        assertEquals(1, queen.beatPawn(new Pawn(1, 3)).getMoves());
        assertEquals(1, queen.beatPawn(new Pawn(3, 1)).getMoves());
        assertEquals(2, queen.beatPawn(new Pawn(4, 1)).getMoves());
        assertEquals(2, queen.beatPawn(new Pawn(4, 3)).getMoves());
        assertEquals(2, queen.beatPawn(new Pawn(3, 8)).getMoves());
    }

}
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

        Queen queen1 = new Queen(3, 1);
        assertEquals(2, queen1.beatPawn(new Pawn(0, 3)).getMoves());
        assertEquals(1, queen1.beatPawn(new Pawn(1, 3)).getMoves());


        Queen q1 = new Queen(0, 0);
        assertEquals(1, q1.beatPawn(new Pawn(1, 1)).getMoves());
        Queen q2 = new Queen(1, 1);
        assertEquals(1, q2.beatPawn(new Pawn(3, 1)).getMoves());
        Queen q3 = new Queen(3, 1);
        assertEquals(2, q3.beatPawn(new Pawn(0, 3)).getMoves());
        Queen q4 = new Queen(0, 3);
        assertEquals(1, q4.beatPawn(new Pawn(2, 3)).getMoves());

        Queen q5 = new Queen(1, 1);
        assertEquals(2, q5.beatPawn(new Pawn(2, 3)).getMoves());



    }

}
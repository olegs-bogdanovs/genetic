
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class App {
    private int GENERATION = 0;
    private ChessBoard chessBoard;
    private final int ENTITY_COUNT = 100;
    private final double MUTATION_PROP = 0.001;
    private boolean isFounded = false;
    private Map<Pawn, Integer> pawnIdMap = new HashMap<>();
    private Map<Integer, Pawn> idPawnMap = new HashMap<>();
    private List<Entity> entities = new ArrayList<>();
    private Random random = new Random();


    private void generatePopulations() {
        List<Pawn> pawns = this.chessBoard.getPawns();
        for (int i = 0; i < pawns.size(); i++) {
            pawnIdMap.put(pawns.get(i), i);
            idPawnMap.put(i, pawns.get(i));
        }

        for (int i = 0; i < ENTITY_COUNT; i++) {
            List<Integer> chromosome = new ArrayList<>(idPawnMap.keySet());
            Collections.shuffle(chromosome);
            entities.add(new Entity(chromosome));
        }
    }

    private int fitnessFunction(Entity entity) {
        Queen queen = this.chessBoard.getQueen();
        int res = 0;

        for (Integer integer : entity.getChromosome()) {
            queen = queen.beatPawn(idPawnMap.get(integer));
            if (queen.getMoves() == 1) res++;
        }
        if (res == entity.getChromosome().size()) {
            isFounded = true;
            System.out.println(entity + " GEN: " + GENERATION);
        }
        return res;
    }

    private void crossover() {
        Collections.shuffle(this.entities);
        List<Entity> newEntities = new ArrayList<>();

        for (int i = 0; i < this.entities.size(); i = i + 2) {
            List<Integer> firstEntPieceOfChromosome = this.entities.get(i).getRandomPieceOfChromosome();
            List<Integer> secondEntPieceOfChromosome = this.entities.get(i + 1).getRandomPieceOfChromosome();

            Entity firstChild = this.entities.get(i).getCopy();
            Entity secondChild = this.entities.get(i+1).getCopy();

            firstChild.insertPieceOfChromosome(secondEntPieceOfChromosome);
            secondChild.insertPieceOfChromosome(firstEntPieceOfChromosome);

            newEntities.add(firstChild);
            newEntities.add(secondChild);
        }

        this.entities.addAll(newEntities);
    }

    private void mutation() {
        for (Entity entity : this.entities) {
            if (this.random.nextDouble() < MUTATION_PROP) {
                entity.mutate();
            }
        }
    }

    private void selection() {
        List<Pair<Integer, Entity>> entsWithFitnessRes = new ArrayList<>();
        int sum = 0;
        for (Entity entity : this.entities) {
            int fitnessRes = fitnessFunction(entity);
            sum += fitnessRes;
            entsWithFitnessRes.add(Pair.of(fitnessRes, entity));
        }

        List<Pair<Double, Entity>> roulette = new ArrayList<>();

        double prevValue = 0.0;
        for (Pair<Integer, Entity> entity : entsWithFitnessRes) {
            prevValue += entity.getKey() * 1.0 / sum;
            roulette.add(Pair.of(prevValue, entity.getValue()));
        }

        List<Entity> newPopulation = new ArrayList<>();
        for (int i = 0; i < ENTITY_COUNT; i++) {
            double r = this.random.nextDouble();

            for (int j = 0; j < roulette.size(); j++) {
                Pair<Double, Entity> entity = roulette.get(j);
                if (entity.getKey() > r) {
                    newPopulation.add(entity.getValue());
                    break;
                }
            }
        }

        this.entities.clear();
        this.entities.addAll(newPopulation);
    }


    public App(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        generatePopulations();

        for (int i = 0; i < 200; i++) {
            crossover();
           // mutation();
            selection();
            GENERATION++;
        }

        for (Entity entity : this.entities) {
            System.out.println(entity + " " + fitnessFunction(entity));
        }

//        while (!isFounded) {
//            crossover();
//            mutation();
//            selection();
//            GENERATION++;
//        }


    }
}

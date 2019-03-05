
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class App {
    private int GENERATION = 0;
    private ChessBoard chessBoard;
    private final int ENTITY_COUNT = 100;
    private final double MUTATION_PROP = 0.01;
    private Map<Pawn, Integer> pawnIdMap = new HashMap<>();
    private Map<Integer, Pawn> idPawnMap = new HashMap<>();
    private List<List<Integer>> entities = new ArrayList<>();
    private Random random = new Random();


    private void generatePopulations() {
        List<Pawn> pawns = this.chessBoard.getPawns();
        for (int i = 0; i < pawns.size(); i++) {
            pawnIdMap.put(pawns.get(i), i);
            idPawnMap.put(i, pawns.get(i));
        }

        for (int i = 0; i < ENTITY_COUNT; i++) {
            List<Integer> entity = new ArrayList<>(idPawnMap.keySet());
            Collections.shuffle(entity);
            entities.add(entity);
        }
    }

    private int fitnessFunction(List<Integer> entity) {
        Queen queen = this.chessBoard.getQueen();
        int res = 0;

        for (Integer integer : entity) {
            queen = queen.beatPawn(idPawnMap.get(integer));
            if (queen.getMoves() == 1) res++;
        }
        //if (res == entity.size()) System.out.println("GENERATION: " + GENERATION + " ENT->" + entity);
        return res;
    }

    private void crossover(){
        List<List<Integer>> ent = new ArrayList<>();
        ent.addAll(this.entities);
        Collections.shuffle(ent);

        for (int i = 0; i < ent.size(); i = i + 2){
            List<Integer> ent1 = new ArrayList<>(ent.get(i));
            List<Integer> ent2 = new ArrayList<>(ent.get(i+1));

            int genomPosition = this.random.nextInt(ent1.size());
            Integer ent1GenValue = ent1.get(genomPosition);
            Integer ent2GenValue = ent2.get(genomPosition);

            ent1.set(ent1.indexOf(ent2GenValue), ent1GenValue);
            ent2.set(ent2.indexOf(ent1GenValue), ent2GenValue);

            ent1.set(genomPosition, ent2GenValue);
            ent2.set(genomPosition, ent1GenValue);

            this.entities.add(ent1);
            this.entities.add(ent2);
        }
    }

    private void mutation() {
        List<List<Integer>> newPopulation = new ArrayList<>();
        for (List<Integer> entity : this.entities) {
            if (this.random.nextDouble() < MUTATION_PROP) {
                int intervalStart = random.nextInt(entity.size());
                int intervalEnd = random.nextInt(entity.size());
                int temp;

                if (intervalStart > intervalEnd) {
                    temp = intervalEnd;
                    intervalEnd = intervalStart;
                    intervalStart = temp;
                }

                List<Integer> subList = new ArrayList<>(entity.subList(intervalStart, intervalEnd));
                Collections.reverse(subList);

                List<Integer> newEntity = new ArrayList<>();
                for (int i=0; i < entity.size(); i++) {
                    if (i < intervalStart || i >= intervalEnd) {
                        newEntity.add(entity.get(i));
                    } else {
                        newEntity.add(subList.get(i - intervalStart));
                    }
                }
                newPopulation.add(newEntity);
            } else {
                newPopulation.add(entity);
            }
        }
        this.entities.clear();
        this.entities.addAll(newPopulation);
    }

    private void selection() {
        List<Pair<Integer, List<Integer>>> ents = new ArrayList<>();
        int sum = 0;
        for (List<Integer> entity : this.entities) {
            int fitnessRes = fitnessFunction(entity);
            sum += fitnessRes;
            ents.add(Pair.of(fitnessRes, entity));
        }

        List<Pair<Double, List<Integer>>> ents2 = new ArrayList<>();

        double prevValue = 0.0;
        for (Pair<Integer, List<Integer>> entity: ents) {
            prevValue += entity.getKey() * 1.0 / sum;
            ents2.add(Pair.of(prevValue, entity.getValue()));
        }

        List<List<Integer>> newPopulation = new ArrayList<>();
        for (int i = 0; i < ENTITY_COUNT; i++) {
            double r = this.random.nextDouble();

            for (int j = 0; j < ents2.size(); j++) {
                Pair<Double, List<Integer>> entity = ents2.get(j);
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
        for (int i = 0; i < 100; i++) {
            crossover();
            mutation();
            selection();
            GENERATION ++;
        }

        this.entities.forEach(entity -> {
            System.out.println(entity + " -> " + fitnessFunction(entity));
        });
    }
}

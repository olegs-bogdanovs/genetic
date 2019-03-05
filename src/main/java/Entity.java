import java.util.*;

public class Entity {
    private static Random random = new Random();

    private List<Integer> chromosome;
    private int generationSurvived = 0;

    public Entity(List<Integer> chromosome) {
        this.chromosome = chromosome;
    }

    public List<Integer> getChromosome() {
        return new ArrayList<>(this.chromosome);
    }

    public int getGenerationSurvived() {
        return generationSurvived;
    }

    public void increaseGeneration() {
        this.generationSurvived++;
    }

    public List<Integer> getRandomPieceOfChromosome() {
        int left = random.nextInt(this.chromosome.size() - 1);
        int right = random.nextInt(this.chromosome.size() - 1);

        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }

        List<Integer> chromosomePiece = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            chromosomePiece.add(this.chromosome.get(i));
        }
        return chromosomePiece;
    }

    private void normalizeChromosome(List<Integer> originalChromosome){
        List<Integer> unusedEntries = new ArrayList<>(originalChromosome);
        Set<Integer> usedEntries = new HashSet<>(this.chromosome);
        unusedEntries.removeAll(usedEntries);

        for(int i = 0; i < this.chromosome.size(); i++){
            if (usedEntries.contains(this.chromosome.get(i))){
                usedEntries.remove(this.chromosome.get(i));
            } else {
                Integer e = unusedEntries.get(0);
                this.chromosome.set(i, e);
                unusedEntries.remove(e);
            }
        }
    }

    public void insertPieceOfChromosome(List<Integer> pieceOfChromosome) {
        int startOfInsert = random.nextInt(this.chromosome.size() - pieceOfChromosome.size());
        List<Integer> originalChromosome = new ArrayList<>(this.chromosome);

        for (int i = 0; i < pieceOfChromosome.size(); i++) {
            this.chromosome.set(startOfInsert + i, pieceOfChromosome.get(i));
        }
        normalizeChromosome(originalChromosome);
    }

    public void mutate() {
        int intervalStart = random.nextInt(this.chromosome.size());
        int intervalEnd = random.nextInt(this.chromosome.size());
        int temp;

        if (intervalStart > intervalEnd) {
            temp = intervalEnd;
            intervalEnd = intervalStart;
            intervalStart = temp;
        }

        List<Integer> subList = new ArrayList<>(this.chromosome.subList(intervalStart, intervalEnd + 1));
        Collections.reverse(subList);

        for (int i = 0; i < this.chromosome.size(); i++) {
            if (!(i < intervalStart || i > intervalEnd)) {
                this.chromosome.set(i, subList.get(i - intervalStart));
            }
        }
    }

    public Entity getCopy(){
        return new Entity(new ArrayList<>(this.chromosome));
    }

    public static void main(String[] args) {
        Entity firstEntity = new Entity(Arrays.asList(1, 2, 3, 4, 5));
        Entity secondEntity = new Entity(Arrays.asList(5, 4, 3, 2, 1));

        List<Integer> firstPieceOfChromosome = firstEntity.getRandomPieceOfChromosome();
        secondEntity.insertPieceOfChromosome(firstPieceOfChromosome);

        secondEntity.mutate();
    }

    @Override
    public String toString() {
        return "Entity{" +
                "chromosome=" + chromosome +
                ", generationSurvived=" + generationSurvived +
                '}';
    }
}

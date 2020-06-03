import java.util.ArrayList;

public class ObservationPoint {

    private int numberOfPredator;
    private ArrayList<Koala> healthyKoala;
    private ArrayList<Koala> injuredKoala;
    private int[] treesNumber;
    private double weightOfAvailableFood;
    private int numberOfShelterTrees;
    private ArrayList<Tree> trees;
    //private String treeTypes;

    public ObservationPoint() {
        numberOfPredator = 0;
        healthyKoala = new ArrayList<Koala>();
        injuredKoala = new ArrayList<Koala>();
        treesNumber = new int[5];
        weightOfAvailableFood = 0.00;
        numberOfShelterTrees = 0;
        trees = null;
    }

    public ObservationPoint(int newNumberOfPredator, ArrayList<Koala> newHealthyKoala, ArrayList<Koala> newInjuredKoala, int[] newTreesNumber, double newWeightOfAvailableFood, int newNumberOfShelterTrees, ArrayList<Tree> newTrees) {
        numberOfPredator = newNumberOfPredator;
        healthyKoala = newHealthyKoala;
        injuredKoala = newInjuredKoala;
        treesNumber = newTreesNumber;
        weightOfAvailableFood = newWeightOfAvailableFood;
        numberOfShelterTrees = newNumberOfShelterTrees;
        trees = newTrees;
    }

    public int getNumberOfPredator() {
        return numberOfPredator;
    }

    public ArrayList<Koala> getHealthyKoala() {
        return healthyKoala;
    }

    public ArrayList<Koala> getInjuredKoala() {
        return injuredKoala;
    }

    public int[] getTreesNumber() {
        return treesNumber;
    }

    public double getWeightOfAvailableFood() {
        return weightOfAvailableFood;
    }

    public int getNumberOfShelterTrees() {
        return numberOfShelterTrees;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void setNumberOfPredator(int numberOfPredator) {
        this.numberOfPredator = numberOfPredator;
    }

    public void setHealthyKoala(ArrayList<Koala> healthyKoala) {
        this.healthyKoala = healthyKoala;
    }

    public void setInjuredKoala(ArrayList<Koala> injuredKoala) {
        this.injuredKoala = injuredKoala;
    }

    public void setTreesNumber(int[] treesNumber) {
        this.treesNumber = treesNumber;
    }

    public void setWeightOfAvailableFood(double weightOfAvailableFood) {
        this.weightOfAvailableFood = weightOfAvailableFood;
    }

    public void setNumberOfShelterTrees(int numberOfShelterTrees) {
        this.numberOfShelterTrees = numberOfShelterTrees;
    }

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }
}

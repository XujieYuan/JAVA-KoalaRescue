import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Reserve {

    private SafeHaven safeHaven;
    //private ObservationPoint[] obsPoint;
    private ArrayList<ObservationPoint> obsPoint;
    //private int randomNumber;

    public Reserve() {
        safeHaven = new SafeHaven();
        //obsPoint = new ObservationPoint[10];
        obsPoint = new ArrayList<ObservationPoint>();
    }

    public Reserve(SafeHaven newSafeHaven, ArrayList<ObservationPoint> newObsPoint) {
        safeHaven = newSafeHaven;
        obsPoint = newObsPoint;
    }

    public SafeHaven getSafeHaven() {
        return safeHaven;
    }

    public ArrayList<ObservationPoint> getObsPoint() {
        return obsPoint;
    }

    public void setObsPoint(ArrayList<ObservationPoint> obsPoint) {
        this.obsPoint = obsPoint;
    }

    public void setSafeHaven(SafeHaven safeHaven) {
        this.safeHaven = safeHaven;
    }

    public int setObservationPoint() {
        setObservationPointTrees();
        int lostTrees = damageTrees();
        setInjuredKoala();
        setHealthyKoala();
        setFood();
        setShelter();
        setPredator();
        return lostTrees;
    }

    public void setObservationPointTrees() {
        FileIO file = new FileIO("trees.txt");
        ArrayList<String> trees = file.readFileByLines();
        //ArrayList<ObservationPoint> obs = new ArrayList<>();
        ArrayList<Tree> t1 = new ArrayList<>();
        System.out.println("There are " + file.countLines() + " observation points.");
        for (int i = 0; i <= file.countLines() - 1; i++) {
            ObservationPoint ob = new ObservationPoint();
            int[] t = new int[trees.get(i).split(",").length];
            for (int j = 0; j < trees.get(i).split(",").length; j++) {
                t[j] = Integer.parseInt(trees.get(i).split(",")[j]);
            }
            ob.setTreesNumber(t);
            //ob.getTreesNumber();
            //Manna Gum
            Tree tr1 = new Tree();
            tr1.setTreeType("Manna Gum");
            tr1.setUsage("Food");
            tr1.setAmount(t[0]);
            tr1.setLeaves(t[0] * 1.00);
            t1.add(tr1);
            //Swamp Gum
            Tree tr2 = new Tree();
            tr2.setTreeType("Swamp Gum");
            tr2.setUsage("Food");
            tr2.setAmount(t[1]);
            tr2.setLeaves(t[1] * 0.34);
            t1.add(tr2);
            //Blue Gum
            Tree tr3 = new Tree();
            tr3.setTreeType("Blue Gum");
            tr3.setUsage("Food");
            tr3.setAmount(t[2]);
            tr3.setLeaves(t[2] * 0.90);
            t1.add(tr3);
            //River Red Gum
            Tree tr4 = new Tree();
            tr4.setTreeType("River Red Gum");
            tr4.setUsage("Food");
            tr4.setAmount(t[3]);
            tr4.setLeaves(t[3] * 0.40);
            t1.add(tr4);
            //Wattle
            Tree tr5 = new Tree();
            tr1.setTreeType("Wattle");
            tr1.setUsage("Shelter");
            tr1.setAmount(t[0]);
            tr1.setLeaves(0);
            t1.add(tr5);
            ob.setTrees(t1);

            obsPoint.add(ob);
            System.out.println(Arrays.toString(ob.getTreesNumber()));
        }
        //System.out.println(Arrays.toString(obsPoint.get(3).getTreesNumber()));
    }

    public int damageTrees() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("For each type of tree there is 5% chance that one tree of that type has been burnt or has fallen over");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        try {
            BufferedWriter out1 = new BufferedWriter(new FileWriter("trees.txt"));
            out1.write("");
        } catch (IOException e) {
            System.out.println("IO error");
        }
        RandomNumber randomNumber = new RandomNumber();
        FileIO f = new FileIO("trees.txt");
        int damagedTrees = 0;
        //ArrayList<int[]> newTrees = new ArrayList<>();
        int[] newTs = new int[5];
        for (ObservationPoint observationPoint : obsPoint) {
            for (int j = 0; j < observationPoint.getTreesNumber().length; j++) {
                int rn = randomNumber.getRandomNumber(20, 1);
                if (rn == 6 && observationPoint.getTreesNumber()[j] > 0) {
                    observationPoint.getTreesNumber()[j] -= 1;
                    damagedTrees++;
                }
                newTs = observationPoint.getTreesNumber();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < newTs.length; i++) {
                sb.append(newTs[i]);
                if ((i + 1) != newTs.length) {
                    sb.append(",");
                }
            }
            String content = sb.toString();
            try {
                BufferedWriter out2 = new BufferedWriter(new FileWriter("trees.txt", true));
                out2.write(content + "\n");
                out2.close();
            } catch (IOException e) {
                System.out.println("Unable to save");
            }
            //newTrees.add(newTs);
            //obsPoint.add(observationPoint);
            System.out.println(Arrays.toString(observationPoint.getTreesNumber()));
            System.out.println("Number of damaged trees is " + damagedTrees);
        }

        /**
         for (int a = 0; a < newTrees.size(); a++) {
         f.writeToFile(Arrays.toString(newTrees.get(a)));
         System.out.println("write " + Arrays.toString(newTrees.get(a)));
         System.out.println("Update to file successful");
         }
         */

        //System.out.println(Arrays.toString(newTrees.get(0)));
        //return newTrees;
        return damagedTrees;
    }

    public void setFood() {
        System.out.println("=============================================================");
        //ArrayList<int[]> damagedTrees = damageTrees();
        double food = 0;
        for (int i = 0; i < obsPoint.size(); i++) {
            food = 1.00 * obsPoint.get(i).getTreesNumber()[0] + 0.34 * obsPoint.get(i).getTreesNumber()[1] + 0.90 * obsPoint.get(i).getTreesNumber()[2] + 0.40 * obsPoint.get(i).getTreesNumber()[3];
            obsPoint.get(i).setWeightOfAvailableFood(food);
            System.out.println("Total food in observation point " + (i + 1) + " is " + food);
        }
    }

    public void setShelter() {
        System.out.println("=============================================================");
        int shelter = 0;
        for (int i = 0; i < obsPoint.size(); i++) {
            shelter = obsPoint.get(i).getTreesNumber()[4];
            obsPoint.get(i).setNumberOfShelterTrees(shelter);
            System.out.println("Shelter in observation point " + (i + 1) + " is " + shelter);
        }
    }

    public void setHealthyKoala() {
        System.out.println("=============================================================");
        RandomNumber randomNumber = new RandomNumber();
        for (int j = 0; j < obsPoint.size(); j++) {
            int numberOfHealthyKoala = randomNumber.getRandomNumber(9, 0);
            System.out.println("Number of healthy koala in observation point " + (j + 1) + " is " + numberOfHealthyKoala);
            for (int i = 0; i < numberOfHealthyKoala; i++) {
                Koala k = new Koala();
                k.setAge(randomNumber.getRandomNumber(18, 1));
                k.setStatus("Healthy");
                obsPoint.get(j).getHealthyKoala().add(k);
                //System.out.println("Age is " + k.getAge());
            }
        }
    }

    public void setInjuredKoala() {
        System.out.println("=============================================================");
        RandomNumber randomNumber = new RandomNumber();
        for (int i = 0; i < obsPoint.size(); i++) {
            int numberOfInjuredKoala = randomNumber.getRandomNumber(2, 0);
            System.out.println("Number of injured koala in observation point " + (i + 1) + " is " + numberOfInjuredKoala);
            for (int j = 0; j < numberOfInjuredKoala; j++) {
                Koala k = new Koala();
                k.setAge(randomNumber.getRandomNumber(18, 1));
                k.setStatus("Injured");
                obsPoint.get(i).getInjuredKoala().add(k);
                //System.out.println("Age is " + k.getAge());
            }
        }
    }

    public void setPredator() {
        System.out.println("=============================================================");
        RandomNumber randomNumber = new RandomNumber();
        for (int i = 0; i < obsPoint.size(); i++) {
            int numberOfPredators = randomNumber.getRandomNumber(4, 0);
            System.out.println("Number of predator in observation point " + (i + 1) + " is " + numberOfPredators);
            obsPoint.get(i).setNumberOfPredator(numberOfPredators);
        }
    }
}

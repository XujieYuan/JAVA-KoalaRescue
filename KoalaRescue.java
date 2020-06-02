import java.io.IOException;
import java.util.*;

public class KoalaRescue {

    private String playerName;
    private int budget;
    private String action;

    public KoalaRescue() {
        playerName = "";
        budget = 0;
        action = "";
    }

    public KoalaRescue(String newPlayerName, int newBudget, String newAction) {
        playerName = newPlayerName;
        budget = newBudget;
        action = newAction;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBudget() {
        return budget;
    }

    public String getAction() {
        return action;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void start()  {
        displayWelcomeMessage();
        enterBudget();
        Reserve r = new Reserve();
        int lostTrees = r.setObservationPoint();
        System.out.println("=============================================================");
        System.out.println("Total budget for the rescue is " + budget);
        int result = searchObservationPointsInTurn(r);
        System.out.println("NUMBER of trees that have been lost: " + lostTrees);
        if (result == 0) {
            System.out.println("Rescue was successful, with no koala deaths");
        } else {
            System.out.println("Rescue completed with " + result + " koalas deaths");
        }
    }

    public void displayWelcomeMessage() {
        String prompt = "Welcome to Koala Rescue system, please enter your name.";
        Input input = new Input();
        Validation validation = new Validation(16, 1);
        String output = input.acceptStringInput(prompt);
        if (validation.stringWithinRange(output)) {
            for (int i = 0; i < output.length(); i++) {
                while (!((output.charAt(i) >= 'a' && output.charAt(i) <= 'z') || (output.charAt(i) >= 'A' && output.charAt(i) <= 'Z'))) {
                    prompt = "The name cannot be blank but must be less than 16 alphabetic characters, please enter again!";
                    output = input.acceptStringInput(prompt);
                }
            }
        } else {
            prompt = "The name cannot be blank but must be less than 16 alphabetic characters, please enter again!";
            output = input.acceptStringInput(prompt);
        }
        setPlayerName(output);
        System.out.println("welcome, " + playerName + "!");
    }

    public void enterBudget() {
        String prompt = "please enter the budget for the rescue.";
        Input input = new Input();
        Validation validation = new Validation(200, 100);
        try {
            int money = input.acceptIntInput(prompt);
            if (validation.intWithinRange(money)) {
                setBudget(money);
                System.out.println("Budget is successful set to " + budget + ".");
            } else {
                System.out.println("Budget must be an amount from $100 to $200, inclusive, please enter again!");
                enterBudget();
            }
        } catch (Exception e) {
            System.out.println("Budget can only be a number!");
            enterBudget();
        }
    }

    public int searchObservationPointsInTurn(Reserve reserve) {
        int injuredKoalasTakenToTheSafeHaven = 0;
        int numberOfKoalasRelocated = 0;
        int numberOfHealthyKoala = 0;
        int numberOfDeathKoala = 0;
        int originBudget = getBudget();
        Input input = new Input();
        String prompt = "Please select an action from the list";
        SafeHaven sf = reserve.getSafeHaven();
        int i = 0;
        while (i < reserve.getObsPoint().size()) {
            System.out.println("=============================================================");
            System.out.println("You are now in observation point " + (i + 1));
            System.out.println("The number of injured koalas is " + reserve.getObsPoint().get(i).getInjuredKoala().size());
            System.out.println("The number of healthy koalas is " + reserve.getObsPoint().get(i).getHealthyKoala().size());
            System.out.println("The weight of available food is " + reserve.getObsPoint().get(i).getWeightOfAvailableFood());
            System.out.println("The number of shelter trees is " + reserve.getObsPoint().get(i).getNumberOfShelterTrees());
            System.out.println("The number of predators is " + reserve.getObsPoint().get(i).getNumberOfPredator());
            System.out.println("The remaining budget now is " + budget);
            System.out.println();
            System.out.println("Please decide what actions to take to aid the koalas’ survival within the available budget");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+ A. Move an injured koala to the safe haven +");
            System.out.println("+ B. Move a healthy koala to the safe haven  +");
            System.out.println("+ C. Relocate a koala to this location       +");
            System.out.println("+ D. Take no further action                  +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
            String choice = input.acceptStringInput(prompt);
            switch (choice) {
                case "A":
                    if (budget - 20 >= 0) {
                        if (reserve.getObsPoint().get(i).getInjuredKoala().size() > 0) {
                            Koala movedKoala = reserve.getObsPoint().get(i).getInjuredKoala().get(0);
                            System.out.println("Before remove size " + reserve.getObsPoint().get(i).getInjuredKoala().size());
                            reserve.getObsPoint().get(i).getInjuredKoala().remove(movedKoala);
                            sf.getSafeKoala().add(movedKoala);
                            budget -= 20;
                            injuredKoalasTakenToTheSafeHaven++;
                            System.out.println("After remove size " + reserve.getObsPoint().get(i).getInjuredKoala().size());
                            for (int o = 0; o < sf.getSafeKoala().size(); o++) {
                                System.out.println("In safe haven " + sf.getSafeKoala().get(o).getStatus());
                            }
                        } else {
                            System.out.println("There is no injured koala, please select another action");
                        }
                    } else {
                        System.out.println("Budget is not enough, cannot do this action");
                    }
                    break;
                case "B":
                    if (budget - 10 >= 0) {
                        if (reserve.getObsPoint().get(i).getHealthyKoala().size() > 0) {
                            Koala movedKoala = reserve.getObsPoint().get(i).getHealthyKoala().get(0);
                            System.out.println("Before remove size " + reserve.getObsPoint().get(i).getHealthyKoala().size());
                            reserve.getObsPoint().get(i).getHealthyKoala().remove(movedKoala);
                            sf.getSafeKoala().add(movedKoala);
                            budget -= 10;
                            System.out.println("After remove size " + reserve.getObsPoint().get(i).getHealthyKoala().size());
                            for (int p = 0; p < sf.getSafeKoala().size(); p++) {
                                System.out.println("In safe haven " + sf.getSafeKoala().get(p).getStatus());
                            }
                        } else {
                            System.out.println("There is no healthy koala, please select another action");
                        }
                    } else {
                        System.out.println("Budget is not enough, cannot do this action");
                    }
                    break;
                case "C":
                    int oldestAge = 0;
                    int[] ages = new int[sf.getSafeKoala().size()];
                    System.out.println("size " + sf.getSafeKoala().size());
                    for (int q = 0; q < sf.getSafeKoala().size(); q++) {
                        System.out.println("In safe haven " + sf.getSafeKoala().get(q).getAge());
                        ages[q] = sf.getSafeKoala().get(q).getAge();
                    }
                    System.out.println(Arrays.toString(ages));
                    int temp = 0;
                    for (int m = 0; m < ages.length; m++) {
                        for (int j = 0; j < ages.length; j++) {
                            if (ages[m] < ages[j] && m < j) {
                                temp = ages[m];
                                ages[m] = ages[j];
                                ages[j] = temp;
                            }
                        }
                    }
                    System.out.println(Arrays.toString(ages));
                    if (!(ages == null || ages.length == 0)) {
                        oldestAge = ages[0];
                        System.out.println("Oldest koala is " + oldestAge);
                        for (int l = 0; l < sf.getSafeKoala().size(); l++) {
                            if (sf.getSafeKoala().get(l).getAge() == oldestAge) {
                                if (((reserve.getObsPoint().get(i).getHealthyKoala().size() + 1) <= reserve.getObsPoint().get(i).getWeightOfAvailableFood()) && ((reserve.getObsPoint().get(i).getHealthyKoala().size() + 1) <= reserve.getObsPoint().get(i).getNumberOfShelterTrees()) && (reserve.getObsPoint().get(i).getNumberOfPredator() < 3)) {
                                    Koala movedKoala = reserve.getSafeHaven().getSafeKoala().get(l);
                                    reserve.getSafeHaven().getSafeKoala().remove(movedKoala);
                                    reserve.getObsPoint().get(i).getHealthyKoala().add(movedKoala);
                                    //System.out.println(movedKoala.getStatus());
                                    movedKoala.setStatus("Healthy");
                                    //System.out.println(movedKoala.getStatus());
                                    budget += 5;
                                    numberOfKoalasRelocated++;
                                    System.out.println("In observation point " + (i + 1) + " you relocated a koala");
                                } else {
                                    System.out.println("Not satisfy the relocate requirement");
                                }
                            }
                        }
                    } else {
                        System.out.println("There are no koalas in safe haven");
                    }
                    break;
                case "D":
                    //injured koala
                    numberOfDeathKoala += reserve.getObsPoint().get(i).getInjuredKoala().size();
                    //System.out.println("There are " + numberOfDeathKoala + " injured koalas dead");
                    System.out.println(numberOfDeathKoala + " koalas dead(i)");
                    for (int a = 0; a < reserve.getObsPoint().get(i).getInjuredKoala().size(); a++) {
                        Koala movedDeadKoala = reserve.getObsPoint().get(i).getInjuredKoala().get(a);
                        reserve.getObsPoint().get(i).getInjuredKoala().remove(movedDeadKoala);
                    }
                    //shortage of food
                    if (reserve.getObsPoint().get(i).getHealthyKoala().size() > reserve.getObsPoint().get(i).getWeightOfAvailableFood()) {
                        int extraKoalaNumber1 = reserve.getObsPoint().get(i).getHealthyKoala().size() - (int) (reserve.getObsPoint().get(i).getWeightOfAvailableFood());
                        System.out.println("There are " + extraKoalaNumber1 + " koalas have not enough food which may not survive");
                        for (int a1 = 0; a1 < extraKoalaNumber1; a1++) {
                            //for (int b = 0; b < reserve.getObsPoint().get(i).getHealthyKoala().size(); b++) {
                            RandomNumber randomNumber = new RandomNumber();
                            int chance1 = randomNumber.getRandomNumber(5, 1);
                            if (chance1 == 1 || chance1 == 2 || chance1 == 3 || chance1 == 4) {
                                Koala movedDeadKoala = reserve.getObsPoint().get(i).getHealthyKoala().get(a1);
                                reserve.getObsPoint().get(i).getHealthyKoala().remove(movedDeadKoala);
                                numberOfDeathKoala += 1;
                                //System.out.println(numberOfDeathKoala + " koalas dead because of shortage of food");
                                System.out.println(numberOfDeathKoala + " koalas dead(f)");
                            }
                            //}
                        }
                    }
                    //lack of shelter
                    if (reserve.getObsPoint().get(i).getHealthyKoala().size() > reserve.getObsPoint().get(i).getNumberOfShelterTrees()) {
                        int extraKoalaNumber2 = reserve.getObsPoint().get(i).getHealthyKoala().size() - reserve.getObsPoint().get(i).getNumberOfShelterTrees();
                        System.out.println("There are " + extraKoalaNumber2 + " koalas have not enough shelters which may not survive");
                        for (int a2 = 0; a2 < extraKoalaNumber2; a2++) {
                            //for (int b1 = 0; b1 < reserve.getObsPoint().get(i).getHealthyKoala().size(); b1++) {
                            RandomNumber randomNumber = new RandomNumber();
                            int chance2 = randomNumber.getRandomNumber(5, 1);
                            if (chance2 == 1) {
                                Koala movedDeadKoala = reserve.getObsPoint().get(i).getHealthyKoala().get(a2);
                                reserve.getObsPoint().get(i).getHealthyKoala().remove(movedDeadKoala);
                                numberOfDeathKoala += 1;
                                //System.out.println(numberOfDeathKoala + " koalas dead because of lack of shelters");
                                System.out.println(numberOfDeathKoala + " koalas dead(s)");
                            }
                            //}
                        }
                    }
                    //predators
                    if (reserve.getObsPoint().get(i).getNumberOfPredator() > 3) {
                        for (int b2 = 0; b2 < reserve.getObsPoint().get(i).getHealthyKoala().size(); b2++) {
                            RandomNumber randomNumber = new RandomNumber();
                            int chance3 = randomNumber.getRandomNumber(2, 1);
                            if (chance3 == 2) {
                                Koala movedDeadKoala = reserve.getObsPoint().get(i).getHealthyKoala().get(b2);
                                reserve.getObsPoint().get(i).getHealthyKoala().remove(movedDeadKoala);
                                numberOfDeathKoala += 1;
                                //System.out.println(numberOfDeathKoala + " koalas dead because of the predators");
                                System.out.println(numberOfDeathKoala + " koalas dead(p)");
                            }
                        }
                    }
                    numberOfHealthyKoala += reserve.getObsPoint().get(i).getHealthyKoala().size();
                    System.out.println("There are " + numberOfHealthyKoala + " healthy koalas in observation points");
                    i++;
                    break;
                default:
                    System.out.println("Invalid input! Enter again!");
                    break;
            }
        }
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("*******************************************************");
        System.out.println("NUMBER of death koalas: " + numberOfDeathKoala);
        System.out.println("There are " + numberOfHealthyKoala + " healthy koalas in observation points");
        System.out.println("There are " + reserve.getSafeHaven().getSafeKoala().size() + " healthy koalas in safe haven");
        System.out.println("NUMBER of healthy koalas (both in the reserve and safe haven): " + (numberOfHealthyKoala + reserve.getSafeHaven().getSafeKoala().size()));
        System.out.println("NUMBER of injured koalas taken to the safe haven: " + injuredKoalasTakenToTheSafeHaven);
        System.out.println("NUMBER of koalas relocated: " + numberOfKoalasRelocated);
        System.out.println("Amount spent on the rescue: " + (originBudget - budget));
        return numberOfDeathKoala;
    }
}

public class RandomNumber {
    private int maximumValue;
    private int minimumValue;
    /**
     * Constructor for objects of class RNG
     */
    public RandomNumber()
    {
        maximumValue = 1;
        minimumValue = 0;
    }
    public int getMaximumValue()
    {
        return maximumValue;
    }
    public int getMinimumValue()
    {
        return minimumValue;
    }
    public void setMaximumValue(int newMaximumValue)
    {
        maximumValue = newMaximumValue;
    }
    public void setMinimumValue(int newMinimumValue)
    {
        minimumValue = newMinimumValue;
    }
    public int getRandomNumber(int maximumValue, int minimumValue)
    {
        return minimumValue + (int)(Math.random()*(maximumValue-minimumValue+1));
    }

    /**
    public void checkRandomNumber()//check whether the method getRandomNumber work well
    {
        for(int i = 0; i < 20; i++)
        {
            int turn = getRandomNumber(3, 1);
            System.out.println(turn);

        }
    }
     */
}

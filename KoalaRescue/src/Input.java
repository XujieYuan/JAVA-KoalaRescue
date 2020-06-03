import java.util.Scanner;

/**
 * This is a class to accept user input.
 */
public class Input
{
    /**
     * Method to accept user string input
     *
     * @param prompt
     * @return
     */
    public String acceptStringInput(String prompt)
    {
        System.out.println(prompt);
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public int acceptIntInput(String prompt)
    {
        System.out.println(prompt);
        Scanner console = new Scanner(System.in);
        return console.nextInt();
    }
}

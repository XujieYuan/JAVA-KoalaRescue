import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A class to read file content and write content into a file.
 */
public class FileIO {
    private String fileName;

    /**
     * default constructor
     */
    public FileIO() {
        fileName = "trees.txt";
    }

    /**
     * non-default constructor
     */
    public FileIO(String newFileName) {
        fileName = newFileName;
    }

    /**
     * Accessor Method
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Mutator Method
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Method to calculate how many lines are in the file
     *
     * @return
     */
    public int countLines() {
        int linesNumber = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((bufferedReader.readLine()) != null) {
                    linesNumber++;
                }
            } finally {
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found when counting lines");
        } catch (IOException e) {
            System.out.println("Unexpected I/O error occured");
        }
        return linesNumber;
    }

    /**
     * Method to read file content
     *
     * @return
     */
    public ArrayList<String> readFileByLines() {
        ArrayList<String> trees = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(fileName);
            try {
                Scanner parser = new Scanner(fileReader);
                while (parser.hasNextLine()) {
                    trees.add(parser.nextLine());
                }
            } finally {
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found when reading file");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Unexpected I/O error occured");
        }
        //System.out.println(trees);
        return trees;
    }

    /**
     * Method to write the result into a file
     */
    public void writeToFile(String str) {
        //String content = "";
        try {
            FileWriter writer = new FileWriter(fileName, true);
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            writer.write(str + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to save to " + fileName);
        }
    }
}

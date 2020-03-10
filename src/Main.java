import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main{

    private static String inputFile;
    private static String outputFile;

    private static ArrayList<String> fileLines;
    private static ArrayList<String> slideshow;
    private static int score;

    public static void main(String[]args){
        inputFile = args[0];//get file name from args

        try {
            fileLines = readFile();//call the method to read the file

            slideshow = organizeSlideshow();//call to the method that organizes the pictures into a slideshow based on the algorithms

            System.out.println(score); //printing the slideshow score

        } catch (IOException e) {
            System.err.println("IOException");
            e.printStackTrace();
        }

    }



    private static ArrayList<String> readFile() throws IOException {
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(inputFile));
        return lines;
    }

    private static ArrayList<String> organizeSlideshow() {
        ArrayList<String> content = new ArrayList<String>();

        //TODO escolher e implementar um dos algoritmos de optimização que são sugeridos no enunciado.


        return  content;
    }

}
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class SlideShow {

    private static ArrayList<Photo> verticalPhotos = new ArrayList<Photo>();
    private static ArrayList<Photo> horizontalPhotos = new ArrayList<Photo>();

    private static ArrayList<Slide> slides = new ArrayList<Slide>();
    private static ArrayList<Slide> finalSlideShow = new ArrayList<Slide>();

    private static int score;

    public static void main(String[] args) throws Exception {
        // Call the file reader method
        readFromFile(args[0]);

        createSlides();

        ArrayList<Slide> firstSlideShow = GenerateRandomSlideshow(slides);

        System.out.println(
                "Choose your algorithm:\n\t1 - Random SlideShow\n\t2 - Greedy Approach\n\t3 - Hill Climbing\n\t4 - Simulated Annealing\n\t5 - Genetic Algorithm");
        System.out.println("The algorithms will print different numbers to know if the optimization is running");

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        String alg = scanner.next();
        String name = args[0];
        String outputFilename= name.split("\\.")[0];
        scanner.close();
        switch (alg) {
            case "1":
                System.out.println("Random SlideShow was chosen!");
                finalSlideShow = firstSlideShow;
                score = Scoring.getTotalScoring(firstSlideShow);
                outputFilename = outputFilename + "Random_SlideShow.txt";
                break;
            case "2":
                System.out.println("Greedy Approach was chosen!");
                //finalSlideShow = Greedy.greedyApproach(slides); //with original file input
                finalSlideShow = Greedy.greedyApproach(firstSlideShow); //with random slideshow
                score = Scoring.getTotalScoring(finalSlideShow);
                outputFilename = outputFilename + "Greedy_Approach.txt";
                break;
            case "3":
                System.out.println("Hill Climbing was chosen!");
                finalSlideShow = HillClimbing.algorithm(firstSlideShow);
                score = Scoring.getTotalScoring(finalSlideShow);
                outputFilename = outputFilename + "Hill_Climbing.txt";
                break;
            case "4":
                System.out.println("Simulated Annealing was chosen!");
                finalSlideShow = SimulatedAnnealing.algorithm(firstSlideShow);
                score = Scoring.getTotalScoring(finalSlideShow);
                outputFilename = outputFilename + "Simulated_Annealing.txt";
                break;
            case "5":
                System.out.println("Genetic Algorithm was chosen!");
                outputFilename = outputFilename + "Random_SlideShow.txt";
                break;
            default:
                System.out.println("no match");
        }

        writeInFile(finalSlideShow,outputFilename);

        System.out.println("Score is: " + score);
    }

    private static ArrayList<Slide> GenerateRandomSlideshow(ArrayList<Slide> slides) {

        ArrayList<Slide> FirstSlideShow = new ArrayList<Slide>();
        FirstSlideShow = (ArrayList<Slide>) slides.clone();

        Collections.shuffle(FirstSlideShow);

        return FirstSlideShow;
    }

    private static void createSlides() {

        // Horizontal3
        int i = 0;//output variable
        for (Photo horizontalPhoto : horizontalPhotos) {
            slides.add(new Slide(horizontalPhoto));
            i++;
            System.out.println("a new horizontal slide was added: " + i);
        }

        //temporary way of making the vertical slides, by joining the 2
        //closest vertical photos
        /*
        for (int j = 0; j < verticalPhotos.size(); j+=2) {
            Photo p1 = verticalPhotos.get(j);
            Photo p2 = verticalPhotos.get(j+1);
            slides.add(new Slide(p1,p2));
            i++;
            System.out.println("a new vertical slide was added: " + i);
        }*/

        i = 0; //output variable
        ArrayList<Photo> vPhotos = (ArrayList<Photo>) verticalPhotos.clone();
        Random r = new Random();

        //create vertical slides
        while (vPhotos.size()>=1){
            //first photo will always be at index 0
            Photo p1 = vPhotos.get(0);

            //determining the second photo based  on a random number
            int offset = r.nextInt(vPhotos.size());
            if(offset==0){
                offset=offset+1;
            }
            Photo p2 = vPhotos.get(offset);

            //adding the photos to a slide
            slides.add(new Slide(p1,p2));
            vPhotos.remove(offset);
            vPhotos.remove(0);

            //progress output
            i++;
            System.out.println("a new vertical slide was added: " + i + " " + offset);
        }

        //getBestVerticalSlides(vPhotos, 0);

    }

    //initial method to create vertical slides by greedy approach
    private static void getBestVerticalSlides(ArrayList<Photo> vPhotos, int j) {

        // If there is one or less vertical photos in the array the method ends
        if (vPhotos.size() <= 1) {
            return;
        }

        // Get the first photo of each run and remove it from the array
        Photo p1 = vPhotos.get(0);
        vPhotos.remove(0);

        // number of maximum different tags, is -1 because if it was 0 it would create
        // problems for the recursion stop condition
        int maxDifferentTags = -1;

        // index of the best partner for the current P1
        int bestPartnerIndex = 0;

        // tags of the current p1
        ArrayList<String> tagsP1 = p1.getTags();

        for (int i = 0; i < vPhotos.size(); i++) {

            // in each iteration of the loop the p2 will be the i index object in the
            // arraylist
            Photo p2 = vPhotos.get(i);

            // calculate the number of different tags between the current p1 and p2
            int differentTags = p2.getNumberOfDifferentTags(tagsP1);

            // if the current different tags number is higher than the max replace the max
            // and change the best partner index
            if (differentTags > maxDifferentTags) {
                maxDifferentTags = differentTags;
                bestPartnerIndex = i;
            }
        }

        // the best partner is turned into p2 and removed from the arraylist
        Photo p2 = vPhotos.get(bestPartnerIndex);
        vPhotos.remove(bestPartnerIndex);

        // a new slide is created
        slides.add(new Slide(p1, p2));
        j++;
        System.out.println("a new vertical photo was added:" + j);

        // sends the array without this iteration of p1 and p2
        getBestVerticalSlides(vPhotos, j);

    }

    // reads from the input file
    private static void readFromFile(String filename) throws Exception {

        File file = new File("input/" + filename);
        System.out.println(file.getAbsolutePath());

        BufferedReader br = new BufferedReader(new FileReader(file));

        // Reads the first line of the file
        int numberOfPhotos = Integer.parseInt(br.readLine());

        System.out.println("Number of photos = " + numberOfPhotos);

        int i = 0;

        String st;

        // reads line by line until EOF
        while ((st = br.readLine()) != null) {
            Photo p = new Photo(st, i);
            String direc = p.getDirection();
            if (direc.equals("H")) {
                // Add the photo to the array of horizontal photos of the class
                horizontalPhotos.add(p);
            } else {
                //// Add the photo to the array of vertical photos of the class
                verticalPhotos.add(p);
            }
            i++;
            System.out.println("read n of lines: " + i);
        }

        br.close();
    }

    // writes information in output file
    private static void writeInFile(ArrayList<Slide> Slideshow,String filename) throws IOException {

        // creates the output file
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
            // writes the number of slides
            writer.write(String.valueOf(Slideshow.size()) + "\n");

            // writes the id of the photo(s) in each slide
            for (Slide slide : Slideshow) {
                writer.write(slide.getID() + "\n");
            }
        }
    }

}
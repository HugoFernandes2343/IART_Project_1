import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
        scanner.close();
        switch (alg) {
            case "1":
                System.out.println("Random SlideShow was chosen!");
                finalSlideShow = firstSlideShow;
                score = Scoring.getTotalScoring(firstSlideShow);
                break;
            case "2":
                System.out.println("Greedy Approach was chosen!");
                finalSlideShow = Greedy.greedyApproach(slides);
                // finalSlideShow = Greedy.greedyApproach(firstSlideShow);
                score = Scoring.getTotalScoring(finalSlideShow);
                break;
            case "3":
                System.out.println("Hill Climbing was chosen!");
                finalSlideShow = HillClimbing.algorithm(firstSlideShow);
                score = Scoring.getTotalScoring(finalSlideShow);
                break;
            case "4":
                System.out.println("Simulated Annealing was chosen!");
                finalSlideShow = SimulatedAnnealing.algorithm(firstSlideShow);
                score = Scoring.getTotalScoring(finalSlideShow);
                break;
            case "5":
                System.out.println("Genetic Algorithm was chosen!");
                break;
            default:
                System.out.println("no match");
        }

        writeInFile(finalSlideShow);

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
        int i = 0;
        for (Photo horizontalPhoto : horizontalPhotos) {
            slides.add(new Slide(horizontalPhoto));
            i++;
            System.out.println("a new horizontal photo was added: " + i);
        }

        ArrayList<Photo> vPhotos = verticalPhotos;

        // Vertical - done using recursive method to lessen the runtime complexity of
        // the method
        getBestVerticalSlide(vPhotos, 0);

    }

    private static void getBestVerticalSlide(ArrayList<Photo> vPhotos, int j) {

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
        getBestVerticalSlide(vPhotos, j);

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
    private static void writeInFile(ArrayList<Slide> Slideshow) throws IOException {

        // creates the output file
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"))) {
            // writes the number of slides
            writer.write(String.valueOf(Slideshow.size()) + "\n");

            // writes the id of the photo(s) in each slide
            for (Slide slide : Slideshow) {
                writer.write(slide.getID() + "\n");
            }
        }
    }

}
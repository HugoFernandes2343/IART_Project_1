import java.io.*;
import java.util.ArrayList;

public class SlideShow {

    private static ArrayList<Photo> verticalPhotos = new ArrayList<Photo>();
    private static ArrayList<Photo> horizontalPhotos = new ArrayList<Photo>();
    private static ArrayList<Slide> slides = new ArrayList<Slide>();

    public static void main(String[] args) throws Exception
    {
        //Call the file reader method
        readFromFile(args[0]);

        createSlides();

        //ArrayList<Slide> FirstSlideShow = Greedy.greedyAproach(slides);
        ArrayList<Slide> FirstSlideShow = HillClimbing.algorithm(slides);
        int score = Scoring.getTotalScoring(FirstSlideShow);

        writeInFile(FirstSlideShow);

        System.out.println("Score is: " + score);
    }

    private static void createSlides() {

        //Horizontal
        for (Photo horizontalPhoto : horizontalPhotos) {
            slides.add(new Slide(horizontalPhoto));
        }

        ArrayList<Photo> vPhotos = verticalPhotos;

        //Vertical - done using recursive method to lessen the runtime complexity of the method
        getBestVerticalSlide(vPhotos);

    }

    private static void getBestVerticalSlide(ArrayList<Photo> vPhotos) {

        // If there is one or less vertical photos in the array the method ends
        if(vPhotos.size() <= 1){
            return;
        }

        //Get the first photo of each run and remove it from the array
        Photo p1 = vPhotos.get(0);
        vPhotos.remove(0);

        //number of maximum different tags, is -1 because if it was 0 it would create problems for the recursion stop condition
        int maxDifferentTags = -1;

        //index of the best partner for the current P1
        int bestPartnerIndex = 0;

        //tags of the current p1
        ArrayList<String> tagsP1 = p1.getTags();

        for (int i = 0; i < vPhotos.size(); i++) {

            //in each iteration of the loop the p2 will be the i index object in the arraylist
            Photo p2 = vPhotos.get(i);

            //calculate the number of different tags between the current p1 and p2
            int differentTags = p2.getNumberOfDifferentTags(tagsP1);

            // if the current different tags number is hiegher than the max replace the max and change the best partner index
            if (differentTags > maxDifferentTags) {
                maxDifferentTags = differentTags;
                bestPartnerIndex = i;
            }
        }

        //the best partner is turned into p2 and removed from the arraylist
        Photo p2 = vPhotos.get(bestPartnerIndex);
        vPhotos.remove(bestPartnerIndex);

        //a new slide is created
        slides.add(new Slide(p1,p2));

        //sends the array without this iteration of p1 and p2
        getBestVerticalSlide(vPhotos);

    }

    private static void readFromFile(String filename) throws Exception {

        File file = new File("input/" + filename);
        System.out.println(file.getAbsolutePath());

        BufferedReader br = new BufferedReader(new FileReader(file));

        int numberOfPhotos = Integer.parseInt(br.readLine());

        System.out.println("Number of photos = " + numberOfPhotos);

        int i = 0;

        String st;
        while ((st = br.readLine()) != null) {
            Photo p = new Photo(st, i);
            String direc = p.getDirection();
            if (direc.equals("H")) {
                //Add the photo to the array of horizontal photos of the class
                horizontalPhotos.add(p);
            } else {
                ////Add the photo to the array of vertical photos of the class
                verticalPhotos.add(p);
            }
            i++;
        }

        br.close();
    }

    private static void writeInFile(ArrayList<Slide> Slideshow) throws IOException
    {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"),"utf-8"))) 
        {
            writer.write(String.valueOf(Slideshow.size()) + "\n");

            for (Slide slide : Slideshow) {
                writer.write(slide.getID() + "\n");
            }
        }
    }


}
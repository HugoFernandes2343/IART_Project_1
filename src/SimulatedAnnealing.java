import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class SimulatedAnnealing {
    private static double temperature = 100;
    private static double coolingFactor = 0.999995;

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides) {

        ArrayList<Slide> currentSlideshow = slides;
        ArrayList<Slide> bestSlideshow = (ArrayList<Slide>) slides.clone();
        int bestScore = Scoring.getTotalScoring(bestSlideshow);
        int  out =0;
        for (double t = temperature; t > 1; t *= coolingFactor) {

            //neighbour
            ArrayList<Slide> neighbourSlideShow = (ArrayList<Slide>) currentSlideshow.clone();
            Random r = new Random();
            int index1 = (r.nextInt(neighbourSlideShow.size() - 1));
            int index2 = (r.nextInt(neighbourSlideShow.size() - 1));

            Collections.swap(neighbourSlideShow, index1, index2);

            int currentScore = Scoring.getTotalScoring(currentSlideshow);
            int neighbourScore = Scoring.getTotalScoring(neighbourSlideShow);
            out++;
            System.out.println("neighbour score: " + neighbourScore + "current score: " + currentScore + " current iteration: " + out;);

            if (bestScore < neighbourScore){
                bestSlideshow = (ArrayList<Slide>) neighbourSlideShow.clone();
                bestScore = neighbourScore;
            }if (Math.random() > Math.exp((currentScore-neighbourScore))){
                currentSlideshow = (ArrayList<Slide>) neighbourSlideShow.clone();
                currentScore = neighbourScore;
            }
            System.out.println("the temperature is: " + t + " the score is: " + bestScore);
        }
        return bestSlideshow;
    }

}

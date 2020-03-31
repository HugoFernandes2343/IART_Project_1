import java.util.ArrayList;
import java.util.Collections;


public class SimulatedAnnealing {
    private static double temperature = 100;
    private static double coolingFactor = 0.9999995;

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides) {

        ArrayList<Slide> currentSlideshow = slides;
        ArrayList<Slide> bestSlideshow = (ArrayList<Slide>) slides.clone();
        int bestScore = Scoring.getTotalScoring(bestSlideshow);

        for (double t = temperature; t > 1; t *= coolingFactor) {

            //neighbour
            ArrayList<Slide> neighbourSlideShow = (ArrayList<Slide>) currentSlideshow.clone();

            int index1 = (int) ((neighbourSlideShow.size()-1) * Math.random());
            int index2 = (int) ((neighbourSlideShow.size()-1) * Math.random());

            Collections.swap(neighbourSlideShow, index1, index2);

            int currentScore = Scoring.getTotalScoring(currentSlideshow);
            int neighbourScore = Scoring.getTotalScoring(neighbourSlideShow);

            System.out.println("neighbour score: " + neighbourScore + "current score: " + currentScore);

            if (Math.random() > probability(currentScore, neighbourScore, t)){
                currentSlideshow = (ArrayList<Slide>) neighbourSlideShow.clone();
                currentScore = neighbourScore;
            }

            if (bestScore < currentScore){
                bestSlideshow = currentSlideshow;
                bestScore = currentScore;
            }
            System.out.println("the temperature is: " + t + "the score is: " + bestScore);
        }
        return bestSlideshow;
    }

    private static double probability(int currentScore, int neighbourScore, double t) {
        if (neighbourScore < currentScore) return 1;
        return  Math.exp((currentScore-neighbourScore)/t);

    }
}

import java.util.*;

public class HillClimbing {

    public HillClimbing(){}

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides)
    {
        ArrayList<Slide> tempShow = slides;

        int score = Scoring.getTotalScoring(slides);
        int numberOfSlides = slides.size() - 1;
        
        Random r = new Random();
        int slideA, slideB;

        int tries = 0;

        do {
            slideA = r.nextInt(numberOfSlides);
            slideB = r.nextInt(numberOfSlides);
                  
            Collections.swap(tempShow, slideA, slideB);

            int tempScore = Scoring.getTotalScoring(tempShow);
            
            if(tempScore > score)
            {
                score = tempScore;
                tries = 0;
            }
            else
            {
                Collections.swap(tempShow, slideB, slideA);
                tries++;
            }
            System.out.println(score);
        } while (tries < 99999);


        return tempShow;
    }
}
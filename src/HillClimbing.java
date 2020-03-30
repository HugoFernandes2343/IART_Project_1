import java.util.*;

public class HillClimbing {

    public HillClimbing() {
    }

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides) {
        ArrayList<Slide> nextShow = Operations.shuffleSlideshow(slides);
        ArrayList<Slide> nextShow2 = Operations.deshuffleSlideshow(slides);
        
        int score = Scoring.getTotalScoring(slides);
        int nextScore = Scoring.getTotalScoring(nextShow);
        int otherScore = Scoring.getTotalScoring(nextShow2);

        System.out.println(score + "\n" + nextScore + "\n" + otherScore + "\n\n");

        if(score >= otherScore && score < nextScore)
        {
            return algorithm(nextShow);
        }
        else if(score < otherScore && score >= nextScore)
        {
            return algorithm(nextShow2);
        }
        else if(score < otherScore && score < nextScore)
        {
            // vai para a direita ou esquerda?
        }
        else if(score == otherScore && score == nextScore)
        {
            // vai para a direita ou esquerda
        }

        return slides;
    }

    
}
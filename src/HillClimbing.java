import java.util.*;

public class HillClimbing {

    public HillClimbing() {
    }

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides) {
        ArrayList<Slide> nextShow = Operations.shuffleSlideshow(slides);
        ArrayList<Slide> prevShow = Operations.deshuffleSlideshow(slides);

        int score = Scoring.getTotalScoring(slides);
        int nextScore = Scoring.getTotalScoring(nextShow);
        int prevScore = Scoring.getTotalScoring(prevShow);
        boolean nothingRight = false;
        boolean nothingLeft = false;

        System.out.println(score + "\n" + nextScore + "\n" + prevScore + "\n\n");

        if (score >= prevScore && score < nextScore) {
            nothingRight = true;
            return algorithm(nextShow);
        } else if (score < prevScore && score >= nextScore) {
            nothingLeft = true;
            return algorithm(prevShow);
        } else if (score < prevScore && score < nextScore) {
            if (prevScore < nextScore) {
                return algorithm(nextShow);
            } else {
                return algorithm(prevShow);
            }
        } else if (score == prevScore && score == nextScore) {
            if (nothingLeft && nothingRight) {
                return slides;
            } else if (nothingLeft && !nothingRight) {
                return algorithm(nextShow);
            } else if (!nothingLeft && nothingRight) {
                return algorithm(prevShow);
            } else {
                return algorithm(nextShow);
            }
        } else if (score == prevScore && score > nextScore) {
            nothingRight = true;
            if (!nothingLeft) {
                return algorithm(prevShow);
            } else {
                return slides;
            }
        } else if (score > prevScore && score == nextScore) {
            nothingLeft = true;
            if (!nothingRight) {
                return algorithm(nextShow);
            } else {
                return slides;
            }
        }

        return slides;
    }

}
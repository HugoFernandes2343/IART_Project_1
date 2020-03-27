import java.util.*;

public class HillClimbing {

    public HillClimbing() {
    }

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides) {
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

            if (tempScore > score) {
                score = tempScore;
                tries = 0;
            } else {
                Collections.swap(tempShow, slideB, slideA);
                tries++;
            }
            System.out.println(score);
        } while (tries < 99999);

        return tempShow;
    }

    public static ArrayList<Slide> shuffleEvenSlideshow(ArrayList<Slide> slides) {
        ArrayList<Slide> newSlideshow = new ArrayList<Slide>();
        int size = slides.size();
        int middle = size / 2;
        int firstHalf = middle / 4;
        int secondHalf = middle - firstHalf;
        for (int i = firstHalf - 1; i < secondHalf - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        for (int i = middle + secondHalf - 1; i < size - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        for (int i = 0; i < firstHalf - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        for (int i = middle - 1; i < middle + secondHalf - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        return newSlideshow;
    }

    public static ArrayList<Slide> deshuffleEvenSlideshow(ArrayList<Slide> slides) {
        ArrayList<Slide> newSlideshow = new ArrayList<Slide>();
        int size = slides.size();
        int middle = size / 2;
        int firstHalf = middle / 4;
        int secondHalf = middle - firstHalf;
        for (int i = middle - 1; i < middle + firstHalf - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        for (int i = 0; i < secondHalf - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        for (int i = middle + firstHalf - 1; i < size - 1; i++) {
            newSlideshow.add(slides.get(i));
        }
        for (int i = secondHalf - 1; i < middle - 1; i++) {
            newSlideshow.add(slides.get(i));
        }

        return newSlideshow;
    }

}
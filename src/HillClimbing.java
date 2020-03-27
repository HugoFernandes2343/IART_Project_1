import java.util.*;

public class HillClimbing {

    public HillClimbing() {
    }

    public static ArrayList<Slide> algorithm(ArrayList<Slide> slides) {
        ArrayList<Slide> nextShow = shuffleSlideshow(slides);
        int score = Scoring.getTotalScoring(slides);
        int nextScore = Scoring.getTotalScoring(nextShow);

        System.out.println("score atual - " + score + " proximo score - " + nextScore);
        if (nextScore > score) {
            return algorithm(nextShow);
        } else {
            nextShow = deshuffleSlideshow(slides);
            nextScore = Scoring.getTotalScoring(nextShow);
            System.out.println("score atual - " + score + " score anterior - " + nextScore);
            if (nextScore > score) {
                return algorithm(nextShow);
            } else {
                return slides;
            }
        }
    }

    /*
     * public static ArrayList<Slide> shuffleSlideshow(ArrayList<Slide> slides) {
     * ArrayList<Slide> newSlideshow = new ArrayList<Slide>(); int size =
     * slides.size(); int middle = size / 2; int firstHalf = middle / 4; int
     * secondHalf = middle - firstHalf;
     * newSlideshow.addAll(slides.subList(firstHalf, middle));
     * newSlideshow.addAll(slides.subList(middle + secondHalf, size));
     * newSlideshow.addAll(slides.subList(0, firstHalf));
     * newSlideshow.addAll(slides.subList(middle, middle + secondHalf));
     * 
     * return newSlideshow; }
     */
    public static ArrayList<Slide> shuffleSlideshow(ArrayList<Slide> slides) {
        ArrayList<Slide> newSlideshow = new ArrayList<Slide>();
        int size = slides.size();
        int middle = size / 2;
        List<Slide> firstHalf = slides.subList(0, middle);
        List<Slide> secondHalf = slides.subList(middle, size);
        for (int i = 0; i < middle; i++) {
            newSlideshow.add(secondHalf.get(i));
            newSlideshow.add(firstHalf.get(i));
        }
        if (size % 2 != 0) {
            newSlideshow.add(secondHalf.get(middle));
        }
        return newSlideshow;
    }

    public static ArrayList<Slide> deshuffleSlideshow(ArrayList<Slide> slides) {
        ArrayList<Slide> newSlideshow = new ArrayList<Slide>();
        int size = slides.size();
        for (int i = 1; i < size; i++) {
            newSlideshow.add(slides.get(i));
            i++;
        }
        for (int i = 0; i < size; i++) {
            newSlideshow.add(slides.get(i));
            i++;
        }
        return newSlideshow;
    }
    /*
     * public static ArrayList<Slide> deshuffleSlideshow(ArrayList<Slide> slides) {
     * ArrayList<Slide> newSlideshow = new ArrayList<Slide>(); int size =
     * slides.size(); int middle = size / 2; int middle2 = size - middle; int
     * firstHalf = middle / 4; int secondHalf = middle - firstHalf;
     * newSlideshow.addAll(slides.subList(middle2, middle2 + firstHalf));
     * newSlideshow.addAll(slides.subList(0, secondHalf));
     * newSlideshow.addAll(slides.subList(middle2 + firstHalf, size));
     * newSlideshow.addAll(slides.subList(secondHalf, middle2)); return
     * newSlideshow; }
     */
}
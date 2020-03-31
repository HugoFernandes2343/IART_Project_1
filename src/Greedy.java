import java.util.ArrayList;

public class Greedy
{

    public Greedy()
    {
    }

	public static ArrayList<Slide> greedyApproach(ArrayList<Slide> slides) {

        
        ArrayList<Slide> slideshow = new ArrayList<Slide>();    // Slideshow to be returned
        
        
        slideshow.add(slides.get(0));   //Always start with the first slide
        slides.remove(0);
        int j = 0;

        slideshow = recursiveGreedy(slideshow,slides,j);

		return slideshow;
	}

    private static ArrayList<Slide> recursiveGreedy(ArrayList<Slide> slideshow, ArrayList<Slide> slides, int j) {

            if(slides.size()==0) {
                return slideshow;
            }
            int tempScore = 0;
            int nextSlide = 0;
            for (int i = 0; i < slides.size(); i++) {
                int temp = Scoring.calculatePoints(slideshow.get(j), slides.get(i));
                if (temp >= tempScore) {
                    tempScore = temp;
                    nextSlide = i;
                }
            }

            slideshow.add(slides.get(nextSlide));
            System.out.println(slideshow.size());
            slides.remove(nextSlide);
            j++;
            return recursiveGreedy(slideshow,slides,j);
    }


}
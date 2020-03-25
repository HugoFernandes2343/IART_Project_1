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

        for (int j = 0; j < slideshow.size(); j++) {
            int tempScore = 0;
            int nextSlide = 0;
            if(slides.size()==0)
                break;
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

        }

		return slideshow;
	}




}
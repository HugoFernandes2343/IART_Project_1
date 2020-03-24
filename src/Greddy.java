import java.util.ArrayList;

public class Greddy 
{

    public Greddy()
    {
    }

	public static ArrayList<Slide> greddyAproach(ArrayList<Slide> slides) {

        
        ArrayList<Slide> Slideshow = new ArrayList<Slide>();    // Slideshow to be returned
        
        
        Slideshow.add(slides.get(0));   //Always start with the first slide
        slides.remove(0);

        for (int j = 0; j < Slideshow.size(); j++) {
            int tempScore = 0;
            int nextSlide = 0;
            System.out.println("a = " + slides.size());
            if(slides.size()==0)
                break;
            for (int i = 0; i < slides.size(); i++) {
                int temp = Scoring.calculatePoints(Slideshow.get(j), slides.get(i));
                if (temp >= tempScore) {
                    tempScore = temp;
                    nextSlide = i;
                }
            }

            Slideshow.add(slides.get(nextSlide));
            slides.remove(nextSlide);

            System.out.println("b = " + Slideshow.size());
        }

		return Slideshow;
	}




}
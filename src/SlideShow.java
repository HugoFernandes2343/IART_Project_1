import java.io.*;
import java.util.ArrayList;

public class SlideShow {

    public static void main(String[] args) throws Exception
    {
        
        File file = new File("input\\" + args[0]);

        BufferedReader br = new BufferedReader(new FileReader(file));

        Integer numberOfPhotos = Integer.parseInt(br.readLine());

        ArrayList<Photo> vertical = new ArrayList<Photo>();
        ArrayList<Photo> horizontal = new ArrayList<Photo>();

        int i = 0;

        String st;
        while ((st = br.readLine()) != null) {
            Photo p = new Photo(st, i);
            String direc = p.getDirection();
            if (direc.equals("H")) {
                horizontal.add(p);
            } else {
                vertical.add(p);
            }
            i++;
        }
        
        br.close();

        ArrayList<Slide> slides = new ArrayList<Slide>();
        
        
        for (int j = 0; j < horizontal.size(); j++) {
            slides.add(new Slide(horizontal.get(j)));
        }

       
        

        Greddy g = new Greddy();

        ArrayList<Slide> FirstSlideShow = g.greddyAproach(slides);

    }
}
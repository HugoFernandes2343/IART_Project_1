import java.io.*;
import java.util.ArrayList;

public class SlideShow {

    public static void main(String[] args) throws Exception
    {
        
        File file = new File("C:\\Users\\joaom\\Desktop\\Curso\\IART\\" + args[0]);

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
        
        System.out.println(numberOfPhotos);

        br.close();
    }
}
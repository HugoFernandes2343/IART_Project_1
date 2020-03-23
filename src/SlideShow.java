import java.io.*;

public class SlideShow {

    public static void main(String[] args) throws Exception
    {
        System.out.println(args[0]);

        File file = new File("C:\\Users\\joaom\\Desktop\\Curso\\IART\\" + args[0]);

        BufferedReader br = new BufferedReader(new FileReader(file));

        Integer numberOfPhotos = Integer.parseInt(br.readLine());

        Photo[] photoArray = new Photo[numberOfPhotos];

        int i = 0;

        String st;
        while ((st = br.readLine()) != null) {
            photoArray[i] = new Photo(st);
            i++;
        }
 
        System.out.println(numberOfPhotos);

        br.close();
    }
}
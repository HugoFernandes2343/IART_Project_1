import java.util.ArrayList;

public class Slide
{
    Photo p1;
    Photo p2;
    ArrayList<String> tags = new ArrayList<String>();
    
    public Slide(Photo p)
    {
        this.p1 = p;
        this.p2 = null;

        for (String tag : p1.getTags()) {
            tags.add(tag);
        }

    }

    public Slide(Photo p1, Photo p2)
    {
        this.p1 = p1;
        this.p2 = p2;

        tags = p1.getTags();
        tags.addAll(p2.getTags());
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }

    public String getID()
    {
        String a = ""+p1.getIndex();
        if(p2 != null)
            a = a+" "+p2.getIndex();
        return a;
    }
}
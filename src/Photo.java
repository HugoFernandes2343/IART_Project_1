import java.util.ArrayList;

public class Photo {

    private String direction;
    private Integer nTags;
    private Integer index;
    private ArrayList<String> tags;

    public Photo(String data, Integer index)
    {
        this.index = index;
        String[] dt = data.split(" ");

        direction = dt[0];
        nTags = Integer.parseInt(dt[1]);
    
        tags = new ArrayList<String>();

        for (int i = 2; i < dt.length; i++) {
            tags.add(dt[i]);
        }
    }

    public String getDirection()
    {
        return direction;
    }

    public Integer getNTags()
    {
        return nTags;
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }

    public Integer getIndex()
    {
        return index;
    }

    public Integer getNumberOfDifferentTags(ArrayList<String> foreingTags){
        int difference = 0;

        for (int i = 0; i<foreingTags.size();i++){
            if(!tags.contains(foreingTags.get(i))){
                difference++;
            }
        }

        return difference;
    }
}
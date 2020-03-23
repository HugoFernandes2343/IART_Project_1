public class Photo {

    private String direction;
    private Integer nTags;
    private String[] tags;

    public Photo(String data)
    {
        String[] dt = data.split(" ");

        direction = dt[0];
        nTags = Integer.parseInt(dt[1]);
    
        tags = new String[nTags];

        for (int i = 2; i < dt.length; i++) {
            tags[i-2] = dt[i];
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

    public String[] getTags()
    {
        return tags;
    }

}
import java.util.ArrayList;

public class Scoring
{
    public Scoring(){}

    public static int calculatePoints(Slide a, Slide b)
    {
        ArrayList<String> tagsA = a.getTags();

        ArrayList<String> tagsB = b.getTags();

        int common = getCommonTags(tagsA, tagsB);

        int uncommon1 = getUncommonTags(tagsA, tagsB);

        int uncommon2 = getUncommonTags(tagsB, tagsA);

        int score = getMinimumValue(common,uncommon1,uncommon2);

        return score;
    }

    private static int getMinimumValue(int common, int uncommon1, int uncommon2) {
        if(common <= uncommon1 && common <= uncommon2)
            return common;
        else if(uncommon1 <= common && uncommon1 <= uncommon2)
            return uncommon1;
        else if(uncommon2 <= uncommon1 && uncommon2 <= common)
            return uncommon2;
        return 0;
    }

    private static int getCommonTags(ArrayList<String> a, ArrayList<String> b)
    {
        int common = 0;

        for (String tag : a) {
            if(b.contains(tag))
                common++;
        }

        return common;
    }

    private static int getUncommonTags(ArrayList<String> a, ArrayList<String> b)
    {
        int uncommon = 0;

        for (String tag : a) {
            if(!b.contains(tag))
                uncommon++;
        }

        return uncommon;
    }

    public static int getTotalScoring(ArrayList<Slide> slideshow)
    {
        int temp = 0;

        for (int i = 0; i < slideshow.size() - 1; i++) {
            temp = temp + calculatePoints(slideshow.get(i), slideshow.get(i+1));
        }

        return temp;
    }

}
import java.util.ArrayList;

public class Scoring {
    public Scoring() {
    }

    public static int calculatePoints(Slide a, Slide b) {
        ArrayList<String> tagsA = a.getTags();

        ArrayList<String> tagsB = b.getTags();

        ArrayList<Integer> tags = getNTags(tagsA, tagsB);
        ArrayList<Integer> tags1 = getNTags(tagsA, tagsB);

        int common = tags.get(0);
        int uncommon1 = tags.get(1);
        int uncommon2 = tags1.get(2);

        int score = getMinimumValue(common, uncommon1, uncommon2);

        return score;
    }

    private static int getMinimumValue(int common, int uncommon1, int uncommon2) {
        if (common <= uncommon1 && common <= uncommon2)
            return common;
        else if (uncommon1 <= common && uncommon1 <= uncommon2)
            return uncommon1;
        else if (uncommon2 <= uncommon1 && uncommon2 <= common)
            return uncommon2;
        return 0;
    }

    public static ArrayList<Integer> getNTags(final ArrayList<String> a, final ArrayList<String> b) {
        ArrayList<String> a1 = (ArrayList) a.clone();
        int common = 0;
        int uncommon = 0;
        if (a1.size() > 1) {
            if (b.contains(a1.get(0))) {
                common++;
            } else {
                uncommon++;
            }
            a1.remove(0);
            ArrayList<Integer> aux = getNTags(a1, b);
            ArrayList<Integer> ret = new ArrayList<Integer>();
            ret.add(aux.get(0) + common);
            ret.add(aux.get(1) + uncommon);
            return ret;
        } else {
            if (b.contains(a1.get(0))) {
                common++;
            } else {
                uncommon++;
            }
            ArrayList<Integer> ret = new ArrayList<Integer>();
            ret.add(common);
            ret.add(uncommon);
            return ret;
        }
    }

    public static int getTotalScoring(final ArrayList<Slide> slideshow) {
        final int temp = 0;
        return getTotalScoringRecursive(slideshow, temp);
    }

    public static int getTotalScoringRecursive(final ArrayList<Slide> slideshow, final int aux) {
        ArrayList<Slide> slideshow2 = (ArrayList) slideshow.clone();
        int temp = 0;
        temp = aux + calculatePoints(slideshow2.get(0), slideshow2.get(1));
        if (slideshow2.size() > 2) {
            slideshow2.remove(0);
            return getTotalScoringRecursive(slideshow2, temp);
        } else {
            return temp;
        }
    }

}
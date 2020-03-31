import java.util.*;

public class Genetic {

    static Random rand = new Random();
    static int cont = 0;

    static HashMap<Integer, ArrayList<Slide>> map = new HashMap<Integer, ArrayList<Slide>>();

    static ArrayList<Slide> fatherSlide = new ArrayList<Slide>();
    static ArrayList<Slide> motherSlide = new ArrayList<Slide>();

    /*static ArrayList<Slide> child1 = new ArrayList<Slide>();
    static ArrayList<Slide> child2 = new ArrayList<Slide>();
    static ArrayList<Slide> child3 = new ArrayList<Slide>();
    static ArrayList<Slide> child4 = new ArrayList<Slide>();
    static ArrayList<Slide> child5 = new ArrayList<Slide>();
    static ArrayList<Slide> child6 = new ArrayList<Slide>();
    static ArrayList<Slide> child7 = new ArrayList<Slide>();
    static ArrayList<Slide> child8 = new ArrayList<Slide>();
    static ArrayList<Slide> child9 = new ArrayList<Slide>();
    static ArrayList<Slide> child10 = new ArrayList<Slide>();
    static ArrayList<Slide> child11 = new ArrayList<Slide>();
    static ArrayList<Slide> child12 = new ArrayList<Slide>();*/

    public Genetic(){}

    public static ArrayList<Slide> algorithm(ArrayList<Slide> a, ArrayList<Slide> b, ArrayList<Slide> c, ArrayList<Slide> d, ArrayList<Slide> e, ArrayList<Slide> f, ArrayList<Slide> g, ArrayList<Slide> h, ArrayList<Slide> i, ArrayList<Slide> j, ArrayList<Slide> k, ArrayList<Slide> l)
    {
        

        map = new HashMap<Integer, ArrayList<Slide>>();

        int scoreA = Scoring.getTotalScoring(a);
        map.put(scoreA, a);
        int scoreB = Scoring.getTotalScoring(b);
        map.put(scoreB, b);
        int scoreC = Scoring.getTotalScoring(c);
        map.put(scoreC, c);
        int scoreD = Scoring.getTotalScoring(d);
        map.put(scoreD, d);
        int scoreE = Scoring.getTotalScoring(e);
        map.put(scoreE, e);
        int scoreF = Scoring.getTotalScoring(f);
        map.put(scoreF, f);
        int scoreG = Scoring.getTotalScoring(g);
        map.put(scoreG, g);
        int scoreH = Scoring.getTotalScoring(h);
        map.put(scoreH, h);
        int scoreI = Scoring.getTotalScoring(i);
        map.put(scoreI, i);
        int scoreJ = Scoring.getTotalScoring(j);
        map.put(scoreJ, j);
        int scoreK = Scoring.getTotalScoring(k);
        map.put(scoreK, k);
        int scoreL = Scoring.getTotalScoring(l);
        map.put(scoreL, l);

        System.out.println(scoreA+" "+ scoreB+" "+ scoreC+" "+ scoreD+" "+ scoreE+" "+ scoreF+" "+ scoreG+" "+ scoreH+" "+ scoreI+" "+ scoreJ+" "+ scoreK+" "+ scoreL);

        int[] scores = { scoreA, scoreB, scoreC, scoreD, scoreE, scoreF, scoreG, scoreH, scoreI, scoreJ, scoreK, scoreL };
        
        int[] parents = getParents(scores);

        int fatherScore = parents[0];
        int motherScore = parents[1];

        fatherSlide = map.get(fatherScore);
        motherSlide = map.get(motherScore);

        if(cont == 10000)
        {
            return fatherSlide;
        }

    
        a = makeBaby(fatherSlide, motherSlide);
        b = makeBaby(fatherSlide, motherSlide);
        c = makeBaby(fatherSlide, motherSlide);
        d = makeBaby(fatherSlide, motherSlide);
        e = makeBaby(fatherSlide, motherSlide);
        f = makeBaby(fatherSlide, motherSlide);
        g = makeBaby(fatherSlide, motherSlide);
        h = makeBaby(fatherSlide, motherSlide);
        i = makeBaby(fatherSlide, motherSlide);
        j = makeBaby(fatherSlide, motherSlide);
        k = makeBaby(fatherSlide, motherSlide);
        l = makeBaby(fatherSlide, motherSlide);
        
        System.out.println(cont);
        cont++;
        return algorithm(a,b,c,d,e,f,g,h,i,j,k,l);
    }

    public static ArrayList<Slide> makeBaby(ArrayList<Slide> father, ArrayList<Slide> mother)
    {

        ArrayList<Slide> child = new ArrayList<Slide>();

        // Start of Mating    
        int startingIndex = rand.nextInt(father.size());
        int segmentLength = rand.nextInt(father.size()-startingIndex);
        System.out.println("Starting Index: " + startingIndex + " length: " + segmentLength);
        
        List<Slide> temp = father.subList(startingIndex, startingIndex+segmentLength);

        for (int i = 0; i < father.size(); i++) {
            if(i == startingIndex)
            {
                child.addAll(i, temp);
                i += segmentLength;
            }
            child.add(null);
        }
        

        for (int i = 0; i < mother.size(); i++) {
            if(child.get(i) == null)
            {
                child.remove(i);
                child.add(i, mother.get(i));
            }
        }
        // End of Mating

        // Start of mutation
        int percentage = rand.nextInt(100)+1;
        if(percentage <= 10)
        {
            int h = rand.nextInt(child.size());
            int j = rand.nextInt(child.size());
            Collections.swap(child, h, j);
        }
        // End of mutation

        return child;
    }

    public static int getFather(int[] filhos, int cont, int pai) {
        if (cont == filhos.length) {
            return filhos[pai];
        } else {
            if (filhos[cont] > filhos[pai]) {
                pai = cont;
                cont++;
                return getFather(filhos, cont, pai);
            } else {
                cont++;
                return getFather(filhos, cont, pai);
            }
        }
    }

    public static int getMother(int[] filhos, int cont, int mae, int pai) {
        if (cont == filhos.length) {
            return filhos[mae];
        } else {
            if (cont != pai) {
                if (filhos[cont] > filhos[mae]) {
                    mae = cont;
                    cont++;
                    return getMother(filhos, cont, mae, pai);
                } else {
                    cont++;
                    return getMother(filhos, cont, mae, pai);
                }
            } else {
                cont++;
                return getMother(filhos, cont, mae, pai);
            }
        }
    }

    public static int[] getParents(int[] filhos) {
        int pai = getFather(filhos, 0, 0);
        int mae = getMother(filhos, 0, 0, pai);
        int[] ret = new int[] { pai, mae };
        return ret;
    }

    
}
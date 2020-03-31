import java.util.*;

public class Genetic {

    static Random rand = new Random();

    static HashMap<Integer, ArrayList<Slide>> map = new HashMap<Integer, ArrayList<Slide>>();

    static ArrayList<Slide> fatherSlide = new ArrayList<Slide>();
    static ArrayList<Slide> motherSlide = new ArrayList<Slide>();


    public Genetic(){}

    public static ArrayList<Slide> algorithm(ArrayList<Slide> a, ArrayList<Slide> b, ArrayList<Slide> c,
    ArrayList<Slide> d, ArrayList<Slide> e, ArrayList<Slide> f, ArrayList<Slide> g, ArrayList<Slide> h,
    ArrayList<Slide> i, ArrayList<Slide> j, ArrayList<Slide> k, ArrayList<Slide> l, ArrayList<Slide> m,
    ArrayList<Slide> n, ArrayList<Slide> o, ArrayList<Slide> p, ArrayList<Slide> q, ArrayList<Slide> r,
    ArrayList<Slide> s, ArrayList<Slide> t) {
        
        // generates 40000 generations
        for(int count = 0; count < 40000; count++) 
        {
            
            // map stores the score of each slideshow and respective slideshow
            map = new HashMap<Integer, ArrayList<Slide>>();

            // calculates scores of the population
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
            int scoreM = Scoring.getTotalScoring(m);
            map.put(scoreM, m);
            int scoreN = Scoring.getTotalScoring(n);
            map.put(scoreN, n);
            int scoreO = Scoring.getTotalScoring(o);
            map.put(scoreO, o);
            int scoreP = Scoring.getTotalScoring(p);
            map.put(scoreP, p);
            int scoreQ = Scoring.getTotalScoring(q);
            map.put(scoreQ, q);
            int scoreR = Scoring.getTotalScoring(r);
            map.put(scoreR, r);
            int scoreS = Scoring.getTotalScoring(s);
            map.put(scoreS, s);
            int scoreT = Scoring.getTotalScoring(t);
            map.put(scoreT, t);

            System.out.println(scoreA + " " + scoreB + " " + scoreC + " " + scoreD + " " + scoreE + " " + scoreF + " "
            + scoreG + " " + scoreH + " " + scoreI + " " + scoreJ + " " + scoreK + " " + scoreL + " " + scoreM + " "
            + scoreN + " " + scoreO + " " + scoreP + " " + scoreQ + " " + scoreR + " " + scoreS + " " + scoreT);

            // stores scores in an array
            int[] scores = { scoreA, scoreB, scoreC, scoreD, scoreE, scoreF, scoreG, scoreH, scoreI, scoreJ, scoreK, scoreL,
                scoreM, scoreN, scoreO, scoreP, scoreQ, scoreR, scoreS, scoreT };
            
            // get the 2 highest values of the array
            int[] parents = getParents(scores);

            int fatherScore = parents[0];
            int motherScore = parents[1];

            fatherSlide = map.get(fatherScore);
            motherSlide = map.get(motherScore);

            // make the next generation
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
            m = makeBaby(fatherSlide, motherSlide);
            n = makeBaby(fatherSlide, motherSlide);
            o = makeBaby(fatherSlide, motherSlide);
            p = makeBaby(fatherSlide, motherSlide);
            q = makeBaby(fatherSlide, motherSlide);
            r = makeBaby(fatherSlide, motherSlide);
            s = makeBaby(fatherSlide, motherSlide);
            t = makeBaby(fatherSlide, motherSlide);
            
            System.out.println(count);
        }
        return fatherSlide;
    }

    public static ArrayList<Slide> makeBaby(ArrayList<Slide> father, ArrayList<Slide> mother)
    {

        ArrayList<Slide> child = new ArrayList<Slide>();

        // Start of Mating    
        int startingIndex = rand.nextInt(father.size());
        int segmentLength = rand.nextInt(father.size()-startingIndex);
        
        List<Slide> temp = father.subList(startingIndex, startingIndex+segmentLength);
        // add segment from father
        for (int i = 0; i < father.size(); i++) {
            if(i == startingIndex)
            {
                child.addAll(i, temp);
                i += segmentLength;
            }
            child.add(null);
        }
        
        // fill the rest with elements from the mother
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
        if(percentage <= 50)
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
            if (filhos[cont] > filhos[pai])
                pai = cont;

            cont++;
            return getFather(filhos, cont, pai);
        }
    }

    public static int getMother(int[] filhos, int cont, int mae, int pai) {
        if (cont == filhos.length) {
            return filhos[mae];
        } else {
            if (cont != pai && filhos[cont] > filhos[mae])
                    mae = cont;
                    
            cont++;
            return getMother(filhos, cont, mae, pai);
            
        }
    }

    public static int[] getParents(int[] filhos) {
        int pai = getFather(filhos, 0, 0);
        int mae = getMother(filhos, 0, 0, pai);
        int[] ret = new int[] { pai, mae };
        return ret;
    }

    
}
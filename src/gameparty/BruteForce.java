package gameparty;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by feli- on 01/11/2016.
 */
public class BruteForce {
    public ArrayList<character> answerSet = new ArrayList<character>();
    public ArrayList<character> allChar = new ArrayList<character>();
    public int totalStat;
    public int maxCost;
    public int maxPerson;
    public int[] answerIdxSet;

    public BruteForce(ArrayList<character> chars, int cost, int person){
        allChar.addAll(chars);
        maxCost = cost;
        maxPerson = person;
        answerIdxSet = new int[maxPerson];
        totalStat = 0;
    }

    public ArrayList<int[]> getCombi(){
        int[] input = new int[allChar.size()];    // input array
        for (int i = 0; i < allChar.size(); i++){
            input[i] = i;
        }
        int k = maxPerson;                             // sequence length

        ArrayList<int[]> subsets = new ArrayList<int[]>();

        int[] s = new int[k];                  // here we'll keep indices
        // pointing to elements in input array

        if (k <= input.length) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (s[i] = i) < k - 1; i++);
            subsets.add(getSubset(input, s));
            for(;;) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--);
                if (i < 0) {
                    break;
                } else {
                    s[i]++;                    // increment this item
                    for (++i; i < k; i++) {    // fill up remaining items
                        s[i] = s[i - 1] + 1;
                    }
                    subsets.add(getSubset(input, s));
                }
            }

            // limit total cost + get max
            for (int[] tempRes : subsets) {
                int tempCost = 0;
                int tempStats = 0;
                for (int resX : tempRes) {
                    tempCost += allChar.get(resX).cost;
                    tempStats += allChar.get(resX).totalStat();
                }
                if (tempCost <= maxCost) {
                    if (tempStats > totalStat) {
                        totalStat = tempStats;
                        answerIdxSet = tempRes;
                    }
                }
            }
        }
        if (subsets.size() == 0){
            System.out.println("Semua masukan masuk dalam kombinasi akhir!");
            return null;
        } else {
            //print subsets for checking

            for (int[] oneSub : subsets) {
                int maxcount = 0;
                for (int i : oneSub) {
                    System.out.print(i + ", ");
                    maxcount += allChar.get(i).totalStat();
                }
                System.out.println(maxcount);
            }

            //print result
            System.out.println("====== RESULT ======");
            System.out.println("- Party Member: ");
            int globalCost = 0;
            for (int ansIdx : answerIdxSet) {
                System.out.print("o) ");
                System.out.println(allChar.get(ansIdx).toString());
                globalCost += allChar.get(ansIdx).cost;
            }
            System.out.println("- Max Stat: " + totalStat);
            System.out.println("- Total Cost: " + globalCost);
            return subsets;
        }
    }
    int[] getSubset(int[] input, int[] subset) {
        int[] result = new int[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }
}

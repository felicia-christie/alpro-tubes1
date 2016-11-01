package gameparty;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by feli- on 01/11/2016.
 */
public class BackTrack {
    // Heuristik: Menyerupai kasus knapsack problem, sehingga perhitungan Value (keuntungan) dilakukan dengan mengambil
    // status yang paling tinggi.
    public ArrayList<character> allChar = new ArrayList<character>();
    public int totalStat;
    public int maxCost;
    public int maxPerson;
    public int[] answerIdxSet;

    public BackTrack(ArrayList<character> chars, int cost, int person){
        allChar.addAll(chars);
        maxCost = cost;
        maxPerson = person;
        answerIdxSet = new int[maxPerson];
        totalStat = 0;
    }

    public void backtrackProcessInit(){
        // define root
        int curMinCost = 999;
        int curMaxVal = 0;
        // dfs over nodes by looping
        for (int level = 0; level < allChar.size(); level++){
            if (countCosts(new int[]{level}) <= maxCost) {
                for (int level2 = level + 1; level2 < allChar.size(); level2++) {
                    if (countCosts(new int[]{level, level2}) <= maxCost) {
                        for (int level3 = level2 + 1; level3 < allChar.size(); level3++) {
                            if (countCosts(new int[]{level, level2, level3}) <= maxCost) {
                                for (int level4 = level3 + 1; level4 < allChar.size(); level4++) {
                                    if (countCosts(new int[]{level, level2, level3, level4}) <= maxCost ) {
                                        for (int level5 = level4 + 1; level5 < allChar.size(); level5++) {
                                            // count cost
                                            if (countCosts(new int[]{level, level2, level3, level4, level5}) <= maxCost
                                                    && countStat(new int[]{level, level2, level3, level4, level5}) > curMaxVal) {
                                                curMaxVal = countStat(new int[]{level, level2, level3, level4, level5});
                                                answerIdxSet = new int[]{level, level2, level3, level4, level5};
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i : answerIdxSet){
            System.out.println(allChar.get(i).toString() + "\n");
        }
        System.out.println("Total Cost: " + countCosts(answerIdxSet));
        System.out.println("Total Stat: " + countStat(answerIdxSet));
    }

    public int countCosts (int[] idx){
        int temp = 0;
        for (int i : idx){
            temp += allChar.get(i).cost;
        }
        return temp;
    }

    public int countStat (int[] idx){
        int temp = 0;
        for (int i : idx){
            temp += allChar.get(i).totalStat();
        }
        return temp;
    }


}

package gameparty;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by feli- on 01/11/2016.
 */
public class BranchBound {
    public ArrayList<character> answerSet = new ArrayList<character>();
    public ArrayList<character> allChar = new ArrayList<character>();
    public int totalStat;
    public int maxCost;
    public int maxPerson;
    public int[] answerIdxSet;
    public ArrayList<bnbNode> passedNodes;

    public BranchBound(ArrayList<character> chars, int cost, int person){
        allChar.addAll(chars);
        maxCost = cost;
        maxPerson = person;
        answerIdxSet = new int[maxPerson];
        totalStat = 0;
    }

    public ArrayList<character> getPossibleCharNodes(bnbNode startNode){
        ArrayList<character> possibleChars = new ArrayList<character>();
        possibleChars.addAll(allChar);
        for (bnbNode parentNode : startNode.parentNodes){
            possibleChars.remove(parentNode.nodeChar);
        }
        possibleChars.remove(startNode.nodeChar);

        return possibleChars;
    }

    public void processBB(){
        // root
        ArrayList<character> possibleChars = new ArrayList<character>();
        // bfs, heuristik by skillpoint
        possibleChars.addAll(allChar);
        boolean accept = false;
        // find max skillPoint
        Collections.sort(possibleChars);
        passedNodes.add(new bnbNode(new ArrayList<bnbNode>(), new ArrayList<bnbNode>(), possibleChars.get(0)));
        possibleChars.remove(0);

        while (!accept){
            Collections.sort(passedNodes);
            possibleChars.clear();
            possibleChars.addAll(getPossibleCharNodes(passedNodes.get(0)));
            Collections.sort(possibleChars);
            ArrayList<bnbNode> temp = new ArrayList<bnbNode>();
            temp.add(passedNodes.get(0));
            temp.addAll(passedNodes.get(0).parentNodes);
            bnbNode tempNode = new bnbNode(temp , new ArrayList<bnbNode>(), possibleChars.get(0));


        }

    }


}

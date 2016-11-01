package gameparty;

import java.util.ArrayList;

/**
 * Created by feli- on 01/11/2016.
 */
public class bnbNode implements Comparable {
    ArrayList<bnbNode> parentNodes = new ArrayList<bnbNode>();
    ArrayList<bnbNode> childNodes = new ArrayList<bnbNode>();
    character nodeChar;

    public bnbNode(ArrayList<bnbNode> parents, ArrayList<bnbNode> children, character inputChar){
        parentNodes = new ArrayList<bnbNode>();
        parentNodes.addAll(parents);

        childNodes = new ArrayList<bnbNode>();
        childNodes.addAll(children);
        nodeChar = inputChar;
    }

    public int countCost(){
        int temp = nodeChar.cost;
        for(bnbNode parent : parentNodes){
            temp += parent.nodeChar.cost;
        }
        return temp;
    }
    public int countStat(){
        int temp = nodeChar.totalStat();
        for(bnbNode parent : parentNodes){
            temp += parent.nodeChar.totalStat();
        }
        return temp;
    }

    @Override
    public int compareTo(Object o) {
        return this.countStat() - ((bnbNode) o).countStat();
    }

    public ArrayList<bnbNode> getAllParents(){
        ArrayList<bnbNode> temp = new ArrayList<bnbNode>();
        temp.addAll(parentNodes);
        ArrayList<bnbNode> curPar = new ArrayList<bnbNode>();
        curPar.addAll(parentNodes);
        boolean hasParent = true;
        while (hasParent){
            for (bnbNode par : curPar){

            }
        }
        return temp;
    }

    @Override

    public String toString(){
        String temp = "";
        for (bnbNode parentNode : parentNodes){
            temp += parentNode.nodeChar.name + " -> ";
        }
        temp += nodeChar.name;

        return temp;
    }
}

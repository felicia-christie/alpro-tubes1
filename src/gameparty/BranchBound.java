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
    public ArrayList<bnbNode> passedNodes = new ArrayList<bnbNode>();

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
        ArrayList<bnbNode> possibleNodes = new ArrayList<bnbNode>();
        ArrayList<bnbNode> boundedNodes = new ArrayList<bnbNode>();
        // bfs, heuristik by skillpoint
        possibleChars.addAll(allChar);
        boolean accept = false;
        // find max skillPoint
        Collections.sort(possibleChars);
        passedNodes.add(new bnbNode(new ArrayList<bnbNode>(), new ArrayList<bnbNode>(), possibleChars.get(possibleChars.size() - 1))); // right after root node

        // open all possible char nodes
        for (character chars : possibleChars){
            possibleNodes.add(new bnbNode(new ArrayList<bnbNode>(), new ArrayList<bnbNode>(), chars)); // right after root node
        }

        possibleChars.remove(possibleChars.size() - 1);

        while (!accept){
            Collections.sort(passedNodes);
            possibleChars.clear();
            possibleChars.addAll(getPossibleCharNodes(passedNodes.get(passedNodes.size() - 1)));
            // add all possible nodes of passed nodes if not in
            for (bnbNode passed : passedNodes){
                for (character posNod : getPossibleCharNodes(passed)){
                    ArrayList<bnbNode> temp = new ArrayList<bnbNode>();
                    temp.add(passed);
                    possibleNodes.add(new bnbNode(temp, new ArrayList<bnbNode>(), posNod)); // right after root node
                }
            }
            Collections.sort(possibleNodes);
            Collections.sort(possibleChars);
            ArrayList<bnbNode> temp = new ArrayList<bnbNode>();
            temp.add(passedNodes.get(passedNodes.size() - 1));
            temp.addAll(passedNodes.get(passedNodes.size() - 1).parentNodes);

            bnbNode tempNode = new bnbNode(temp , new ArrayList<bnbNode>(), possibleChars.get(possibleChars.size() -1));
//            bnbNode tempNode = possibleNodes.get(possibleNodes.size() - 1);
            if (tempNode.countCost() < maxCost){
                passedNodes.add(tempNode);
                System.out.println("Passed: " + tempNode.toString());
                if (temp.size() >= maxPerson)
                    accept = true;
            } else {
                if (boundedNodes.contains(tempNode)) {
                    System.out.println("ANSWER ERROR");
                    break;
                }
                boundedNodes.add(tempNode);
                tempNode.nodeChar.hp -= 1000;
                tempNode.nodeChar.str -= 1000;

                System.out.println("bounded node: " + tempNode.toString());

            }
        }

        System.out.println(passedNodes.get(passedNodes.size() - 1).nodeChar.toString());
        ArrayList<bnbNode> parentNodes = passedNodes.get(passedNodes.size() - 1).parentNodes;
        for (bnbNode parentNode : parentNodes){
            System.out.println(parentNode.nodeChar.toString());
        }
        System.out.println("Total cost: " + passedNodes.get(passedNodes.size() - 1).countCost());
        System.out.println("Total stat: " + passedNodes.get(passedNodes.size() - 1).countStat());
    }

    public void branchingbounding(){

    }
}

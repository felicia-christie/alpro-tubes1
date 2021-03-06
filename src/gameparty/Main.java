package gameparty;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        String algo = "BB";
        try {

            // 1. Read Input
//            Object obj = parser.parse(new FileReader(
//                    "party-example.json"));
            Object obj = parser.parse(new FileReader(
                    "generated-50.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray chara = (JSONArray) jsonObject.get("characters");

            ArrayList<character> charStats = new ArrayList<character>();

            for (int i = 0; i < chara.size(); i++){
                JSONObject tempChar = (JSONObject) chara.get(i);
                charStats.add(new character( ((Long) tempChar.get("hp")).intValue() , ((Long) tempChar.get("str")).intValue(),
                        0, ((Long) tempChar.get("cost")).intValue(), (String) tempChar.get("name"))
                    );
            }

            // 2. Read Constraint
            int maxcost = ((Long) jsonObject.get("max-cost")).intValue();
            int maxppl = ((Long) jsonObject.get("max-people")).intValue();

            // 3. Choose Algorithm

            //BF
            if (algo.contentEquals("BF")) {
                BruteForce BF = new BruteForce(charStats, maxcost, maxppl);
                long timeMil = System.currentTimeMillis();
                BF.getCombi();
                long dist = System.currentTimeMillis() - timeMil;
                System.out.println("===== Total time: " + dist + "ms =====");
            } else if (algo.contentEquals("BT")){
                BackTrack BT = new BackTrack(charStats, maxcost, maxppl);
                long timeMil = System.currentTimeMillis();
                BT.backtrackProcessInit();
                long dist = System.currentTimeMillis() - timeMil;
                System.out.println("===== Total time: " + dist + "ms =====");
            } else if (algo.contentEquals("BB")){
                BranchBound BB = new BranchBound(charStats, maxcost, maxppl);
                long timeMil = System.currentTimeMillis();
                BB.processBB();
                long dist = System.currentTimeMillis() - timeMil;
                System.out.println("===== Total time: " + dist + "ms =====");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

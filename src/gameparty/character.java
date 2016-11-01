package gameparty;

/**
 * Created by feli- on 01/11/2016.
 */
public class character implements Comparable{
    public int hp;
    public int str;
    public int def;
    public int cost;
    public String name;
    public int profitHeuristic;

    public character(){
        hp = 0; str = 0; cost = 0;
        def = 0; name = "unknown";
        profitHeuristic = 0;
    }

    public character(int new_hp, int new_str, int new_def, int new_cost, String new_name){
        hp = new_hp; str = new_str; cost = new_cost;
        def = new_def; name = new_name;
        profitHeuristic = 0;
    }

    public int totalStat(){
        if (profitHeuristic == 0) {
            if (cost == 0)
                return 99;
            return (hp + def + str)/cost;
        } else if (profitHeuristic == 1){
            return hp;
        } else if (profitHeuristic == 2){
            return str;
        }

        return hp + def + str;
    }

    @Override

    public String toString(){
        return ("Name: " + name +
                "\nHP: " + hp +
                "\nSTR: " + str +
                "\nCost: " + cost +
                "\nTotal Stat: " + (hp + def + str) + "\n"
        );
    }

    @Override
    public int compareTo(Object o) {
        return totalStat() - ((character) o).totalStat() ;
    }
}

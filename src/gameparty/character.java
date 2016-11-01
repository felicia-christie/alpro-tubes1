package gameparty;

/**
 * Created by feli- on 01/11/2016.
 */
public class character {
    public int hp;
    public int str;
    public int def;
    public int cost;
    public double profit;
    public String name;

    public character(){
        hp = 0; str = 0; cost = 0;
        def = 0; name = "unknown";
    }

    public character(int new_hp, int new_str, int new_def, int new_cost, String new_name){
        hp = new_hp; str = new_str; cost = new_cost;
        def = new_def; name = new_name;
        profit = (hp + def + str) / cost;
    }

    public int totalStat(){
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
}

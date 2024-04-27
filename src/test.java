import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class test {

    public static void main(String[] args) throws ParseException {


        Date data = new Date(1613323132012L);
        System.out.println(data.toString() + " " + data.getTime() + " " + System.currentTimeMillis() + " ");
        String std = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(data);
        System.out.println(std);

        long time = (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2021-18-15 01:18:52")).getTime();
        System.out.println("TI:" + time);
        List<Integer> re = new ArrayList<>();
        re.add(1);
        re.add(2);
        re.add(3);
        re.add(4);

        Map<String, List<String>> mymap = new HashMap<>();
        mymap.put("123", new ArrayList<>());
        for(Map.Entry<String, List<String>> current : mymap.entrySet()){

        }


        Road cc = new Road("C", -1);
        Road bb = new Road("B",0, cc);
        Road aa = new Road("A", 0, bb);


        System.out.println(aa.id + " " + aa.nextRoads.light + " " + aa.nextRoads.nextRoads.light);

        re.stream().filter(a -> a%2 == 0).forEach(System.out::println);

        PriorityQueue<Integer> buyOrders = new PriorityQueue<Integer>((a, b) -> b - a);
        PriorityQueue<Integer> sellOrders = new PriorityQueue<Integer>((a, b) -> a - b);
        for(int i = 1; i < 4; i++){
            buyOrders.add(i);
            sellOrders.add(i);
        }

        System.out.println(buyOrders.toString());
        System.out.println(sellOrders.toString());

        List<Intervals> res = new ArrayList<>();
        res.add(new Intervals(3, 4));
        res.add(new Intervals(2, 6));
        res.add(new Intervals(1, 5));
        res.add(new Intervals(1, 2));



        res.sort((a, b) -> a.start - b.start);

        res.stream().forEach(a -> System.out.println(a.start + "  " + a.end));
    }


}

class Solution{
    public int ahh(int[] A) {
        return 0;
    }

}

class Intervals {
    public int start;
    public int end;

    public Intervals(int start, int end){
        this.start = start;
        this.end = end;
    }
}

class Road{
    String id;
    Road nextRoads;
    // -1: no light 0:green 1:red
    int light;

    public Road(String id,  int light){
        this.id = id;
        this.light = light;
    }
    public Road(String id, int light, Road nextRoads){
        this.id = id;
        this.light = light;
        this.nextRoads = nextRoads;
    }
}








import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.*;
public class test {

    public static void main(String[] args){
        List<Integer> re = new ArrayList<>();
        re.add(1);
        re.add(2);
        re.add(3);
        re.add(4);



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







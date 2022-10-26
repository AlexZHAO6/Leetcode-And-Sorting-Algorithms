import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args){
        int a = -20;
        int b = Math.abs(a);

        char c = 'c';
        char d = 'd';

        //JAVA stream API, practice GroupBy!!
        int[] test = {1,1,2,2,3,4,7,8,9};
        Map<Integer,List<Integer>> map =  Arrays.stream(test).boxed().collect(Collectors.groupingBy(Integer::intValue));

        for(int inn : map.keySet()){
            System.out.println(map.get(inn).size());
        }

        StringBuilder res = new StringBuilder("abba");
        StringBuilder res2 = new StringBuilder(res.toString());

        res = res.reverse();
        System.out.println(res);
        System.out.println(res2);

        //this is how to get current time in java
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));

    }
}

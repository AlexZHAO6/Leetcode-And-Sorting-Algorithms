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

        Map<String, Integer> mymap = new HashMap<>();
        mymap.put("a" , 1);
        mymap.put("b" , 2);
        mymap.put("c" , 3);
        mymap.put("d" , 4);

        List<Map.Entry<String, Integer>> mylist = new ArrayList<>(mymap.entrySet());

        Collections.sort(mylist, (a, b) -> a.getValue() - b.getValue());



        mylist.stream().forEach(System.out::println);

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

        int[][] loads = new int[4][3];
        loads[0] = new int[]{1, 7, 3};
        loads[1] = new int[]{2, 8, 3};
        loads[2] = new int[]{3, 9, 3};
        loads[3] = new int[]{5, 10, 6};
        int[] res_new = getMaxStorageInterval(loads);
        System.out.println(res_new[0] + "   " + res_new[1] + "   " + res_new[2]);
    }


    public static int[] getMaxStorageInterval(int[][]loads){
        int[][] inArray = loads;
        int[][] outArray = new int[loads.length][3];

        System.arraycopy(inArray, 0, outArray, 0, loads.length);
        Arrays.sort(inArray,(a, b) -> a[0] - b[0]);
        Arrays.sort(outArray,(a, b) -> a[1] - b[1]);

        int start = 0, end = 0, sum=0, max=0, indexIn=0, indexOut=0;
        boolean startFlag = false;

        while(indexIn < loads.length){
            int[] in = inArray[indexIn];
            int[] out = outArray[indexOut];

            if(in[0] < out[1]){
                sum += in[2];
                indexIn++;
                if(sum > max){
                    max = sum;
                    start = in[0];
                    startFlag = true;
                }
            }
            else {
                sum -= out[2];
                indexOut++;
                if(startFlag){
                    end = out[1];
                    startFlag = false;
                }
            }
        }

        if(end < start){
            end = outArray[indexOut][1];
        }

        return new int[]{start, end, max};
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







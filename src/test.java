import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args){
        char[] test = new char[5];
        System.out.println(test[0] == '\0');
        int a = -20;
        int b = Math.abs(a);
        System.out.println(a);
        System.out.println(b);

        String atest = "/a/b/";
        String[] ah = atest.split("/");
        System.out.println(ah.length);
        for(String x : ah){
            System.out.println(x);
        }
        int c = 2147483600;
        int right = (int)Math.sqrt(c);
        long right2 = (long)Math.sqrt(c);
        int test3 = 46342 * 46340;
        long test4 = (long)46342 * 46340;
        double float5 = 4/333;
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(float5);
        System.out.println(String.valueOf(float5));



    }
}

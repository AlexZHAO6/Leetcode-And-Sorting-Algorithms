import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.*;
public class test {

    public static void main(String[] args){
        int INITIAL_CAPACITY = 2;
        Solution solution = new Solution();


    }


}

class Solution{
    public int solution(int[] A) {
        int len = A.length;
        if(len == 1) return A[0] == 1 ? 2 : 1;
        Arrays.sort(A);

        for(int i = 0; i < len - 1; i++){
            if(i == 0 && A[i] > 1) return 1;
            if(A[i] <= 0 && A[i+1] > 1) return 1;
            if(A[i] <= 0) continue;

            if(A[i+1] - A[i] <= 1) continue;
            else return A[i] + 1;
        }

        return A[len - 1] <= 0 ? 1 : A[len - 1] + 1;
        // Implement your solution here
    }


}







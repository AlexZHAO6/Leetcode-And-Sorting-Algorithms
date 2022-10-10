import java.util.List;

public class SortAlgorithms {
    public static void main(String[] args){
        System.out.println("test");
        int[] test = {2,1,3};
        SortAlgorithms_real testa = new SortAlgorithms_real();
        testa.quickSortHelp(test);

        for(int i = 0; i < test.length; i++){
            System.out.println(test[i]);
        }

    }
}

class SortAlgorithms_real{
    public void bubbleSort(int[] arrs){
        int len = arrs.length;

        for(int i = 0; i < len - 1; i ++){
            for(int j = 0; j < len - 1 - i; j++){
                if(arrs[j] > arrs[j+1]){
                    int tmp = arrs[j];
                    arrs[j] = arrs[j+1];
                    arrs[j+1] = tmp;
                }
            }
        }
    }

    public void selectionSort(int[] arrs){
        int len = arrs.length;

        for(int i = 0; i < len - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < len; j++){
                if(arrs[j] < arrs[minIndex]){
                    minIndex = j;
                }
            }

            int tmp = arrs[i];
            arrs[i] = arrs[minIndex];
            arrs[minIndex] = tmp;
        }
    }

    public void insertSort(int[] arrs){
        int len = arrs.length;

        for(int i = 1; i < len; i++){
            int j = i;
            while(j >= 1 && arrs[j] < arrs[j-1]){
                int tmp = arrs[j];
                arrs[j] = arrs[j-1];
                arrs[j-1] = tmp;

                j--;
            }
        }
    }

    public void quickSortHelp(int[] arrs){
        quickSort(arrs,0,arrs.length-1);
    }
    public void quickSort(int[] arrs, int start, int end){
        if(start >= end) return;
        int mid = partition(arrs,start,end);

        quickSort(arrs,start,mid-1);
        quickSort(arrs, mid+1, end);
    }

    public int partition(int[] arrs, int start, int end){
        int pointer = arrs[start];
        int left = start + 1;
        int right = end;

        while(true){
            while(left <= right && arrs[left] <= pointer) left++;
            while(left <= right && arrs[right] > pointer) right--;

            if(left >= right) break;

            swap(arrs,left,right);
            left++;
            right--;
        }

        swap(arrs,start,right);
        return right;
    }

    public void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
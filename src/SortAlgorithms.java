import java.util.List;

public class SortAlgorithms {
    public static void main(String[] args){
        System.out.println("test");
        int[] test = {3,2,1,5};
        SortAlgorithms_real testa = new SortAlgorithms_real();
        testa.quickSortHelp(test);
        testa.insertSort(test);

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

    //快速排序理想情况的时间复杂度是 O(NlogN)，空间复杂度 O(logN)，极端情况下的最坏时间复杂度是 O(N^2)，空间复杂度是 O(N)
    //如果每次 partition 切分的结果都极不均匀 导致树的高度从logN变成了N 也就退化成了选择排序！
    //还有一点需要注意的是，快速排序是「不稳定排序」，与之相对的，前文讲的 归并排序 是「稳定排序」。
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






    public void mergeSort(int[] arr) {
        //refer to Class merge
    }



    //计数排序
    public void countingSort9(int[] arr) {
        // 建立长度为 9 的数组，下标 0~8 对应数字 1~9, 最大数字是多少就要简历多大的数组
        int[] counting = new int[9];
        // 遍历 arr 中的每个元素
        for (int element : arr) {
            // 将每个整数出现的次数统计到计数数组中对应下标的位置
            counting[element - 1]++;
        }
        // 记录前面比自己小的数字的总数
        int preCounts = 0;
        for (int i = 0; i < counting.length; i++) {
            int temp = counting[i];
            // 将 counting 计算成当前数字在结果中的起始下标位置。位置 = 前面比自己小的数字的总数。
            counting[i] = preCounts;
            // 当前的数字比下一个数字小，累计到 preCounts 中
            preCounts += temp;
        }
        int[] result = new int[arr.length];
        for (int element : arr) {
            // counting[element - 1] 表示此元素在结果数组中的下标
            int index = counting[element - 1];
            result[index] = element;
            // 更新 counting[element - 1]，指向此元素的下一个下标
            counting[element - 1]++;
        }
        // 将结果赋值回 arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }


}

//merge sort!!
//O(NlogN)
class Merge {

    // 用于辅助合并有序数组
    private static int[] temp;

    public static void sort(int[] nums) {
        // 先给辅助数组开辟内存空间
        temp = new int[nums.length];
        // 排序整个数组（原地修改）
        sort(nums, 0, nums.length - 1);
    }

    // 定义：将子数组 nums[lo..hi] 进行排序
    private static void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            // 单个元素不用排序
            return;
        }
        // 这样写是为了防止溢出，效果等同于 (hi + lo) / 2
        int mid = lo + (hi - lo) / 2;
        // 先对左半部分数组 nums[lo..mid] 排序
        sort(nums, lo, mid);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        sort(nums, mid + 1, hi);
        // 将两部分有序数组合并成一个有序数组
        merge(nums, lo, mid, hi);
    }

    // 将 nums[lo..mid] 和 nums[mid+1..hi] 这两个有序数组合并成一个有序数组
    private static void merge(int[] nums, int lo, int mid, int hi) {
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存入 nums
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                // 右半边数组已全部被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
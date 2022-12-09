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




    public void mergeSort(int[] arr) {
        if (arr.length == 0) return;
        int[] result = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, result);
    }

    // 对 arr 的 [start, end] 区间归并排序
    private void mergeSort(int[] arr, int start, int end, int[] result) {
        // 只剩下一个数字，停止拆分
        if (start == end) return;
        int middle = (start + end) / 2;
        // 拆分左边区域，并将归并排序的结果保存到 result 的 [start, middle] 区间
        mergeSort(arr, start, middle, result);
        // 拆分右边区域，并将归并排序的结果保存到 result 的 [middle + 1, end] 区间
        mergeSort(arr, middle + 1, end, result);
        // 合并左右区域到 result 的 [start, end] 区间
        merge(arr, start, end, result);
    }

    // 将 result 的 [start, middle] 和 [middle + 1, end] 区间合并
    private void merge(int[] arr, int start, int end, int[] result) {
        int end1 = (start + end) / 2;
        int start2 = end1 + 1;
        // 用来遍历数组的指针
        int index1 = start;
        int index2 = start2;
        while (index1 <= end1 && index2 <= end) {
            if (arr[index1] <= arr[index2]) {
                //resultIndex = start + (index1 - start1) + (index2 - start2) , start1 = start;
                result[index1 + index2 - start2] = arr[index1++];
            } else {
                //resultIndex = start + (index1 - start1) + (index2 - start2)
                result[index1 + index2 - start2] = arr[index2++];
            }
        }
        // 将剩余数字补到结果数组之后
        while (index1 <= end1) {
            result[index1 + index2 - start2] = arr[index1++];
        }
        while (index2 <= end) {
            result[index1 + index2 - start2] = arr[index2++];
        }
        // 将 result 操作区间的数字拷贝到 arr 数组中，以便下次比较
        while (start <= end) {
            arr[start] = result[start++];
        }
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
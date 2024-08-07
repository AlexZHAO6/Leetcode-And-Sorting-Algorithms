import java.util.*;

public class LeetCode_ChapterFour_TF {
    public static void main(String[] args) {

    }
}

class popularAlgorithms{
    public int removeCoveredIntervals(int[][] intervals) {
        // 按照起点升序排列，起点相同时降序排列
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 记录合并区间的起点和终点
        int left = intervals[0][0];
        int right = intervals[0][1];

        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }

        return intervals.length - res;
    }

    // intervals 形如 [[1,3],[2,6]...]
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return new int[][]{};
        // 按区间的 start 升序排列
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++){
            int[] curr = intervals[i];
            // res 中最后一个元素的引用
            int[] last = res.get(res.size()-1);
            if(curr[0] <= last[1]){
                // 找到最大的 end
                last[1] = Math.max(last[1], curr[1]);
            } else{
                // 处理下一个待合并区间
                res.add(curr);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        // 双指针
        int i = 0, j = 0;
        List<int[]> res = new ArrayList<>();
        while (i < A.length && j < B.length) {
            int a1 = A[i][0], a2 = A[i][1];
            int b1 = B[j][0], b2 = B[j][1];
            // 两个区间存在交集
            if (b2 >= a1 && a2 >= b1) {
                // 计算出交集，加入 res
                res.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
            }
            // 指针前进
            if (b2 < a2) {
                j++;
            } else {
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        // 数组充当备忘录
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        // 初始化 base case
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        // 从左向右计算 l_max
        for (int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        // 计算答案
        for (int i = 1; i < n - 1; i++)
            res += Math.min(l_max[i], r_max[i]) - height[i];

        return res;
    }

    //双指针即可
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            // [left, right] 之间的矩形面积
            int cur_area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, cur_area);
            // 双指针技巧，移动较低的一边
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    //use stack to solve!!
    public boolean isValid(String str) {
        Stack<Character> left = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                left.push(c);
            } else { // 字符 c 是右括号
                if (!left.isEmpty() && leftOf(c) == left.peek()) {
                    left.pop();
                } else {
                    // 和最近的左括号不匹配
                    return false;
                }
            }
        }
        // 是否所有的左括号都被匹配了
        return left.isEmpty();
    }
    char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }

    public int minAddToMakeValid(String s) {
        int need = 0;
        int res = 0;
        int len = s.length();

        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '('){
                // 对右括号的需求 + 1
                need++;
            }
            else {
                // 对右括号的需求 - 1
                need--;
                if(need == -1){
                    need = 0;
                    //要插入一个左括号
                    res++;
                }
            }
        }

        return res + need;
    }

    //一个( 匹配两个 )
    public int minInsertions(String s) {
        int res = 0, need = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                need += 2;
                //当需要的右括号变成了奇数 要插入一个右括号 然后need--
                if (need % 2 == 1) {
                    res++;
                    need--;
                }
            }

            if (s.charAt(i) == ')') {
                need--;
                if (need == -1) {
                    res++;
                    need = 1;
                }
            }
        }
        return res + need;
    }

}

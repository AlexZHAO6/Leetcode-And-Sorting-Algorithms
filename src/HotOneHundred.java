import java.util.*;

public class HotOneHundred {
    public static void main(String[] args){

    }
}

class Codes{
    Map<Character, String> mymap = new HashMap<>();
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode dummy = res;

        int carry = 0;
        while(l1 != null || l2 != null){
            int tmp = 0;
            tmp += carry;
            tmp += l1 == null ? 0 : l1.val;
            tmp += l2 == null ? 0 : l2.val;

            carry = tmp / 10;
            tmp = tmp % 10;

            res.next = new ListNode(tmp);
            res = res.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carry > 0) res.next = new ListNode(carry);

        return dummy.next;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        if(len < 2) return len;

        int res = 0;
        int tmp = 0;
        int start = 0, end = 0;
        while(end < len){
            if(map.isEmpty() || !map.containsKey(s.charAt(end))){
                map.put(s.charAt(end),end);
                tmp++;
                end++;
            }
            else {
                res = Math.max(res,tmp);
                int index = map.get(s.charAt(end));
                while(start <= index){
                    map.remove(s.charAt(start));
                    start++;
                }
                tmp = end - start;
            }
        }

        return Math.max(res,tmp);
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2) return s;

        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        int begin = 0;
        for(int i = 0; i < len; i++){
            dp[i][i] = true;
        }

        for(int L = 2; L <= len; L++){
            for(int i = 0; i < len; i++){
                int j = i + L - 1;
                if(j >= len) break;
                if(s.charAt(i) != s.charAt(j)) dp[i][j] = false;
                else {
                    if(j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i+1][j-1];
                }

                if(dp[i][j] && (j - i + 1) > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    //双指针寻找中位数，复杂度 O(m + n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1 + len2) / 2;

        //left 记录上一次结果 right记录本次结果;
        int left = -1, right = -1;
        int index1 = 0, index2 = 0;

        for(int i = 0; i <= mid; i++){
            left = right;
            if(index1 < len1 && (index2 >= len2 || nums1[index1] < nums2[index2])){
                right = nums1[index1];
                index1++;
            }
            else {
                right = nums2[index2];
                index2++;
            }
        }

        if((len1 + len2) % 2 == 0){
            return (left + right) / 2.0;
        }
        else return right;
    }

    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int max = (len - 1) * (Math.min(height[left], height[right]));

        while(left < right){
            if(height[left] >= height[right]){
                right--;
                int tmp = (right - left) * (Math.min(height[left], height[right]));
                max = Math.max(max, tmp);
            }
            else {
                left++;
                int tmp = (right - left) * (Math.min(height[left], height[right]));
                max = Math.max(max, tmp);

            }
        }

        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);

        for(int i = 0; i < len - 2; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int tmp = nums[i];
            int front = i + 1;
            int end = len - 1;

            int front_flag = front;
            while(front < end){
                if (front > front_flag && nums[front] == nums[front - 1]) {
                    front++;
                    continue;
                }

                if(nums[front] + nums[end] > -tmp){
                    end--;
                }
                else if(nums[front] + nums[end] < -tmp){
                    front++;
                }
                else {
                    List<Integer> tmpList = new ArrayList<>();
                    tmpList.add(nums[i]);
                    tmpList.add(nums[front]);
                    tmpList.add(nums[end]);
                    res.add(tmpList);
                    front++;
                    end--;
                }
            }
        }

        return res;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    public List<String> letterCombinations(String digits) {
        mymap.put('2', "abc");
        mymap.put('3', "def");
        mymap.put('4', "ghi");
        mymap.put('5', "jkl");
        mymap.put('6', "mno");
        mymap.put('7', "pqrs");
        mymap.put('8', "tuv");
        mymap.put('9', "wxyz");

        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        backtrack_letterCombinations(res, mymap, new StringBuilder(), digits, 0);

        return res;
    }
    public void backtrack_letterCombinations(List<String> res, Map<Character, String> map, StringBuilder tmp, String digits, int index){
        if(index == digits.length()){
            res.add(tmp.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = map.get(digit);
        for(int i = 0; i < letters.length(); i++){
            tmp.append(letters.charAt(i));
            backtrack_letterCombinations(res, map, tmp, digits, index+1);
            tmp.deleteCharAt(tmp.length()-1);
        }
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '('||s.charAt(i) == '{' || s.charAt(i) == '['){
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i) == ')'){
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else return false;
            }
            else if(s.charAt(i) == '}'){
                if(!stack.isEmpty() && stack.peek() == '{') stack.pop();
                else return false;
            }
            else if(s.charAt(i) == ']'){
                if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode dummy = res;

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                res.next = new ListNode(list1.val);
                res = res.next;
                list1 = list1.next;
            }
            else {
                res.next = new ListNode(list2.val);
                res = res.next;
                list2 = list2.next;
            }
        }

        while(list1 != null){
            res.next = new ListNode(list1.val);
            res = res.next;
            list1 = list1.next;
        }

        while(list2 != null){
            res.next = new ListNode(list2.val);
            res = res.next;
            list2 = list2.next;
        }

        return dummy.next;
    }

    //回溯法，当‘(’ < n  可以加入之; 当’）‘数量<'（'数量时，可以加入之;
    //从而避免暴力构造再判断
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        generateParenthesis_help(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    public void generateParenthesis_help(List<String> ans, StringBuilder tmp, int open, int close, int n){
        if(tmp.length() == 2 * n){
            ans.add(tmp.toString());
            return;
        }

        if(open < n){
            tmp.append('(');
            generateParenthesis_help(ans, tmp, open+1, close,n);
            tmp.deleteCharAt(tmp.length()-1);
        }

        if(close < open){
            tmp.append(')');
            generateParenthesis_help(ans, tmp, open, close+1,n);
            tmp.deleteCharAt(tmp.length()-1);
        }
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    public List<String> restoreIpAddresses(String s) {
        int SEG_COUNT = 4;
        List<String> ans = new ArrayList<String>();
        int[] segments = new int[SEG_COUNT];

        restoreIpAddresses_dfs(s, 0, 0, SEG_COUNT, segments, ans);
        return ans;
    }
    public void restoreIpAddresses_dfs(String s, int segId, int segStart, int SEG_COUNT, int[] segments, List<String> ans) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }
        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }
        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            restoreIpAddresses_dfs(s, segId + 1, segStart + 1, SEG_COUNT, segments, ans);
        }
        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 255) {
                segments[segId] = addr;
                restoreIpAddresses_dfs(s, segId + 1, segEnd + 1, SEG_COUNT, segments, ans);
            } else {
                break;
            }
        }
    }


    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        //纯数学！ 向量叉积符号出现变化则为非凸边形
        long pre = 0;
        long cur = 0;
        for(int i=0;i<n;i++){
            //基于i+1与i得到向量1,基于i+2与i得到向量2,判断两向量叉乘是否<0即可
            int x1 = points.get((i+1)%n).get(0)-points.get(i).get(0);
            int y1 = points.get((i+1)%n).get(1)-points.get(i).get(1);
            int x2 = points.get((i+2)%n).get(0)-points.get(i).get(0);
            int y2 = points.get((i+2)%n).get(1)-points.get(i).get(1);

            //叉乘为0表示平行
            cur = x1*y2-x2*y1;
            if(cur != 0){
                if (cur * pre < 0) {
                    return false;
                } else {
                    pre = cur;
                }
            }
        }
        return true;
    }

    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if(len == 0) return new int[0][2];
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < len; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];

            if(res.size() == 0 || left > res.get(res.size() - 1)[1]){
                res.add(new int[]{left,right});
            }
            else {
                int tmp = Math.max(right,res.get(res.size() - 1)[1]);
                res.get(res.size() - 1)[1] = tmp;
            }

        }

        return res.toArray(new int[res.size()][]);
    }

}
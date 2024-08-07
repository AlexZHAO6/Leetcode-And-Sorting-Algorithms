import java.util.*;

public class LeetCode_ChapterTwo_TF {
    public static void main(String[] args) {

    }
}

class DP_BASIC{
    // 1、确定「状态」，也就是原问题和子问题中会变化的变量
    // 2、确定「选择」，也就是导致「状态」产生变化的行为
    // 3、明确 dp 函数/数组的定义
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 数组大小为 amount + 1，初始值也为 amount + 1
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for(int i  = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin < 0) continue;

                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }

        return (dp[amount] == amount + 1) ? -1 : dp[amount];

    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = -1;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, (int[] a, int[] b) -> {
            return a[0] == b[0] ?
                    b[1] - a[1] : a[0] - b[0];
        });
        // 对高度数组寻找 LIS
        int[] height = new int[n];
        for (int i = 0; i < n; i++)
            height[i] = envelopes[i][1];

        return lengthOfLIS(height);
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int mn = dp[i - 1][j];
                if (j > 0) {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                }
                dp[i][j] = mn + matrix[i][j];
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i] 和 s2[0..j] 的最小编辑距离是 dp[i+1][j+1]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,

                            dp[i][j - 1] + 1,

                            dp[i - 1][j - 1] + 1

                    );
                }
            }
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] dp = new int[len];

        dp[0] = nums[0];
        int res = dp[0];
        for(int i = 1; i < len; i++){
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 现在 i 和 j 从 1 开始，所以要减一
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // s1[i-1] 和 s2[j-1] 必然在 lcs 中
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // s1[i-1] 和 s2[j-1] 至少有一个不在 lcs 中
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }

    public int minDistance_2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // 复用前文计算 lcs 长度的函数
        int lcs = longestCommonSubsequence(word1, word2);
        return m - lcs + n - lcs;
    }

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.codePointAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            int code1 = s1.codePointAt(i - 1);
            for (int j = 1; j <= n; j++) {
                int code2 = s2.codePointAt(j - 1);
                if (code1 == code2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + code1, dp[i][j - 1] + code2);
                }
            }
        }
        return dp[m][n];
    }

    public int longestPalindromeSubseq(String s) {
        // dp 数组的定义是：在子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j]
        int n = s.length();
        // dp 数组全部初始化为 0
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个 s 的最长回文子串长度
        return dp[0][n - 1];
    }

    public int minInsertions(String s) {
        //dp定义: 对字符串 s[i..j]，最少需要进行 dp[i][j] 次插入才能变成回文串。
        int n = s.length();
        // dp[i][j] 表示把字符串 s[i..j] 变成回文串的最少插入次数
        // dp 数组全部初始化为 0
        int[][] dp = new int[n][n];

        // 反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    // 把 s[i+1..j] 和 s[i..j-1] 变成回文串，选插入次数较少的
                    // 然后还要再插入一个 s[i] 或 s[j]，使 s[i..j] 配成回文串
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        // 整个 s 的最少插入次数
        return dp[0][n - 1];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        //dp[i][j] = x 表示，对于前 i 个物品（i 从 1 开始计数），当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为 false，则说明不能恰好将背包装满。
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装入或不装入背包
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        //dp[i][j] 的定义如下：
        //若只使用前 i 个物品（可以重复使用），当背包容量为 j 时，有 dp[i][j] 种方法可以装满背包。
        int[][] dp = new int[n+1][amount+1];
        for(int i = 1; i <= n; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= amount; j++){
                if (j - coins[i - 1] < 0) {
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }

            }
        }

        return dp[n][amount];
    }


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // **** base case ****
        //dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];

        for (int j = 1; j < n; j++)
            dp[0][j] = dp[0][j - 1] + grid[0][j];

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(
                        dp[i - 1][j],
                        dp[i][j - 1]
                ) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        //我们用 f[t][i] 表示通过恰好 t 次航班，从出发城市 src 到达城市 i 需要的最小花费
        //由于我们最多只能中转 k 次，也就是最多搭乘 k+1 次航班
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; ++i) {
            Arrays.fill(f[i], INF);
        }
        f[0][src] = 0;

        for(int t = 1; t < k +2; t++){
            for(int[] flight : flights){
                int tmpdes = flight[1], tmpsrc = flight[0], cost = flight[2];
                f[t][tmpdes] = Math.min(f[t][tmpdes], f[t-1][tmpsrc] + cost);
            }
        }

        int res = INF;
        for(int i = 0; i <= k + 1; i++){
            res = Math.min(res, f[i][dst]);
        }

        return res == INF ? -1 : res;
    }

    ////-------- 打家劫舍 ----------------
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        if(nums.length == 1) return nums[0];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for(int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[n-1];
    }

    public int rob_2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int n = end - start + 1;
        int[] dp = new int[n];
        if(nums.length == 1) return nums[start];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start],nums[start+1]);

        for(int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i+start]);
        }

        return dp[n-1];
    }


    //-------- 股票买卖 ----------------
    //dp[i][k][0 or 1]
    //0 <= i <= n - 1, 1 <= k <= K
    //n 为天数， k 为交易数的上限，0 和 1 代表是否持有股票。
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //              max( 今天选择 rest,        今天选择 sell       )
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    //              max( 今天选择 rest,         今天选择 buy         ) -- k-1是因为交易的开始是从buy开始！
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit_2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            //如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }


    public int maxProfit_3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        if(n < 2) return 0;
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);
        for (int i = 2; i < n; i++) {
            //每次 sell 之后要等一天才能继续交易，只要把这个特点融入上一题的状态转移方程即可
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            //i-2! not i-1
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit_4(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            //买股票要加手续费
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }

    //允许两次交易 dp数组引入K
    public int maxProfit_5(int[] prices) {
        int max_k = 2, n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];

        for(int i = 1; i <= max_k; i++){
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }

    public int maxProfit_final(int k, int[] prices) {
        int max_k = k, n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];

        for(int i = 1; i <= max_k; i++){
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int k_this = max_k; k_this >= 1; k_this--) {
                dp[i][k_this][0] = Math.max(dp[i-1][k_this][0], dp[i-1][k_this][1] + prices[i]);
                dp[i][k_this][1] = Math.max(dp[i-1][k_this][1], dp[i-1][k_this-1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }


    public int minMeetingRooms(int[][] meetings) {
        int n = meetings.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);

        // 扫描过程中的计数器
        int count = 0;
        // 双指针技巧
        int res = 0, i = 0, j = 0;
        while (i < n && j < n) {

            if (begin[i] < end[j]) {
                // 扫描到一个红点
                count++;
                i++;
            } else {
                // 扫描到一个绿点
                count--;
                j++;
            }
            // 记录扫描过程中的最大值
            res = Math.max(res, count);
        }

        return res;
    }

    //贪心
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            // 不断计算能跳到的最远距离
            farthest = Math.max(farthest, i + nums[i]);
            // 可能碰到了 0，卡住跳不动了
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= n - 1;
    }

    //贪心
    public int jump(int[] nums) {
        int n = nums.length;
        int jumps = 0;
        int forcast = 0, end = 0;
        //i 和 end 标记了可以选择的跳跃步数，farthest 标记了所有选择 [i..end] 中能够跳到的最远距离，jumps 记录了跳跃次数。
        for(int i = 0; i < n - 1 ; i++){
            forcast = Math.max(forcast, nums[i] + i);
            if(i == end){
                jumps++;
                end = forcast;
            }
        }

        return jumps;
    }

}








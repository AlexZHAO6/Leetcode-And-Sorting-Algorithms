General Algorithms && Leetcode

Record the process of practicing algorithms

--- General algorithms

    TODO: sort algorithms


--- Leetcode
    
    TODO: Chapter 0 4节第2部分

    How to caucalate the Big O?
    Big O : 
        Big O 记号表示复杂度的「上界」。比较简单的处理方式就是按最坏情况做近似处理
        非递归算法复杂度: 一般看循环的次数就行了，多个循环嵌套就相乘；但也要注意算法具体做了什么
        比如nSum问题/滑动窗口问题，看上去多个循环嵌套，实际复杂度是O(N) 因为指针一直向前走 并没有回退！！

        递归算法复杂度分析：递归算法本质就是树的遍历！
        时间复杂度：递归次数 * 函数本身的时间复杂度 = 递归树的节点个数 * 每个节点的时间复杂度！！！！
        空间复杂度：递归堆栈深度 + 算法申请的存储空间 = 递归树的高度 + 算法申请的存储空间！！

        tips:   C(N, 0) + C(N, 1) + C(N, 2) + ... + C(N, N) = 2^N
                N 个元素的所有子集（幂集）数量为 2^N
                p(n，m)=n(n-1)(n-2)……(n-m+1); p(n, n) = n!;
                P(N, 0) + P(N, 1) + P(N, 2) + ... + P(N, N), 估算上界 = P(N,N) * N = N * N!
                
        
        
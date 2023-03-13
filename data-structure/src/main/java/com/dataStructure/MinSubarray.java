package com.dataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个正整数数组nums，请你移除 最短子数组（可以为 空），使得剩余元素的 和能被 p整除。 不允许将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1。
 * 子数组定义为原数组中连续的一组元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-sum-divisible-by-p
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Desc
 */
public class MinSubarray {
    public static int minSubarray(int[] nums, int p) {
        /*int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println(sum);
        System.out.println(sum % 8 == 0);
        System.out.println(sum % 8);
        return 0;*/
        int n = nums.length, ans = n;
        int [] s = new int[n + 1];
        for (int i = 0; i < n; ++i)
            s[i + 1] = (s[i] + nums[i]) % p;
        int x = s[n];
        if (x == 0) return 0; // 移除空子数组（这行可以不要）

        Map last = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; ++i) {
            last.put(s[i], i);
            // 如果不存在，-n 可以保证 i-j >= n
            Integer j = new Integer(last.getOrDefault((s[i] - x + p) % p, -n).toString());
            ans = Math.min(ans, i - j);
        }
        return ans < n ? ans : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 4, 9, 7};
        System.out.println(minSubarray(nums, 8));
        System.out.println(2 % 4);
    }
}

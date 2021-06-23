package weekone;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 */
public class HomeWorkLC560 {
    public static int subarraySum(int[] nums, int k) {
        // 前缀合
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 1; i < sums.length; i++) {
            sums[i] = nums[i - 1] + sums[i-1];
            // 寻找sum[i]-sum[j] = k的次数，i>j
            // 统计 sum[i] - k = sum[j]的个数
            if (map.containsKey(sums[i]-k)) {
                ans += map.get(sums[i] - k);
            }
            map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 1, 0};
        int[] nums = {1, 2, 5, 7, 1, 8, 3, 5, 6};
        subarraySum(nums1, 0);
    }
}

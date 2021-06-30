package week2;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1：
 *
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 *
 *
 */
public class LeetCode697 {
    // 使用hash表，按数组元素，统计每个数出现的次数和出现的位置
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> indxeMap = new HashMap<>();
        int maxCount = 1;
        for (int i = 0; i < nums.length; i++) {
            if (indxeMap.containsKey(nums[i])) {
                int[] info = indxeMap.get(nums[i]);
                // 统计次数
                info[0]++;
                maxCount = Math.max(info[0], maxCount);
                // 出现的最小索引
                info[1] = Math.min(info[1], i);
                // 出现的最大索引
                info[2] = Math.max(info[2], i);
                indxeMap.put(nums[i], info);
            } else {
                indxeMap.put(nums[i], new int[]{1, i, i});
            }
        }
        int minLength = Integer.MAX_VALUE;
        for (int num : indxeMap.keySet()) {
            int[] info = indxeMap.get(num);
            if (info[0] == maxCount) {
                minLength = Math.min(minLength, info[2] - info[1] + 1);
            }
        }
        return minLength;
    }
}

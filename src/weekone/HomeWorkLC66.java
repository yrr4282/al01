package weekone;

/**
 * 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 */
public class HomeWorkLC66 {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1]++;

        // 末尾小于等于9,可直接返回
        if (digits[digits.length - 1] <= 9) {
            return digits;
        }

        // [1,2,3,10]
        int curIndex = digits.length - 1;
        // 末尾等于9,需要向上一位加1，当前位置0
        while (curIndex > 0 && digits[curIndex] == 10) {
            digits[curIndex] = 0;
            digits[curIndex - 1]++;
            curIndex--;
        }

        //　如果首位等于10,数组长度+1
        if (digits[curIndex] == 10) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            return ans;
        }
        return digits;
    }
}

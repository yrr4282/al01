package week5;

import java.util.HashMap;
import java.util.Map;

/**
 * 在选举中，第 i 张票是在时间为times[i]时投给persons[i]的。
 *
 * 现在，我们想要实现下面的查询函数： TopVotedCandidate.q(int t) 将返回在t 时刻主导选举的候选人的编号。
 *
 * 在t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 *
 * 示例：
 *
 * 输入：["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
 * 输出：[null,0,1,1,0,0,1]
 * 解释：
 * 时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。
 * 时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。
 * 时间为 25，票数分布情况是 [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。
 * 在时间 15、24 和 8 处继续执行 3 个查询。
 *
 */
public class LeetCode911 {
    int [] persons;
    int [] times;
    int[] maxPersons;
    // [0,1,3,1,1,3,0]
    // 转换为记录当前时刻的得票最多的人 [0,1,3,1,1,1,1]
    public LeetCode911(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        Map<Integer, Integer> countMap = new HashMap<>();
        maxPersons = new int[persons.length];
        int max = 0;
        int maxPerson = persons[0];
        for (int i = 0; i < persons.length; i++) {
            if (countMap.containsKey(persons[i])) {
                countMap.put(persons[i], countMap.get(persons[i]) + 1);
            } else {
                countMap.put(persons[i], 1);
            }
            if (countMap.get(persons[i]) >= max) {
                max = countMap.get(persons[i]);
                maxPerson = persons[i];
            }
            maxPersons[i] = maxPerson;
        }
    }

    public int q(int t) {
        int left = 0;
        int right = times.length-1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            System.out.println("left "+left+" right "+ right +" mid "+ mid);
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return maxPersons[right];
    }

}

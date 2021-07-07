package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 现在你总共有 n 门课需要选，记为 0 到n-1。
 *
 * 在选修某些课程之前需要一些先修课程。例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例1:
 *
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释:总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 */
public class LeetCode210 {
    List<List<Integer>> edges = new ArrayList<>();
    // 定义边的度，入度为0，则可以开始学习
    int[] inDegree;
    int learn;
    boolean []visited;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        inDegree = new int[numCourses];
        visited = new boolean[numCourses];
        learn = numCourses;
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
        }
        for (int[] prerequisite : prerequisites) {
            // bi->ai
            int ai = prerequisite[0];
            int bi = prerequisite[1];
            edges.get(bi).add(ai);
            inDegree[ai]++;
        }
        // 开始遍历
        return topSort();
    }

    private int[] topSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < learn; i++) {
            if (inDegree[i] == 0) {
                // 入度为0，则可以开始学习
                q.add(i);
            }
        }
        // 已学习数组
        int[] learned = new int[learn];
        int count = 0;
        while (!q.isEmpty()) {
            int learning = q.poll();
            visited[learning] = true;
            learned[count++] = learning;
            for (int nextLearn : edges.get(learning)) {
                inDegree[nextLearn]--;
                if (inDegree[nextLearn] == 0 && !visited[nextLearn]) {
                    q.add(nextLearn);
                }
            }
        }
        int[] result = count == learn ? learned : new int[0];
        return result;
    }
}

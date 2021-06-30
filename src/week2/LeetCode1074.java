package week2;

/**
 * 给出矩阵matrix和目标值target，返回元素总和等于目标值的非空子矩阵的数量。
 *
 * 子矩阵x1, y1, x2, y2是满足 x1 <= x <= x2且y1 <= y <= y2的所有单元matrix[x][y]的集合。
 *
 * 如果(x1, y1, x2, y2) 和(x1', y1', x2', y2')两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵
 */
public class LeetCode1074 {
    // s[i][j] = s[i-1][j]+s[i][j-1] - s[i-1][j-1] + matrix[i][j]
    // s(p,q,i,j) = s[i][j] - s[p-1][j] - s[i][q-1] + s[p-1][q-1]
    // 前缀和暴力破解
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int p = 1; p <= i; p++) {
                    for (int q = 1; q <= j; q++) {
                        if (s[i][j] - s[p - 1][j] - s[i][q - 1] + s[p - 1][q - 1] == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

}

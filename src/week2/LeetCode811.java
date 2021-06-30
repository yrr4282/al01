package week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。
 * 作为顶级域名，常用的有"com"，
 * 下一级则有"leetcode.com"，
 * 最低的一级为"discuss.leetcode.com"。
 * <p>
 * 当我们访问域名"discuss.leetcode.com"时，也同时访问了其父域名"leetcode.com"以及顶级域名"com"。
 * <p>
 * 给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.com"。
 * <p>
 * 接下来会给出一组访问次数和域名组合的列表cpdomains。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。
 * <p>
 * 示例 1:
 * 输入:
 * ["9001 discuss.leetcode.com"]
 * 输出:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * 说明:
 * 例子中仅包含一个网站域名："discuss.leetcode.com"。按照前文假设，子域名"leetcode.com"和"com"都会被访问，所以它们都被访问了9001次。
 * <p>
 * 注意事项：
 * <p>
 * cpdomains的长度小于100。
 * 每个域名的长度小于100。
 * 每个域名地址包含一个或两个"."符号。
 * 输入中任意一个域名的访问次数都小于10000。
 */
public class LeetCode811 {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> countMap = new HashMap<>();
        // ["9001 discuss.leetcode.com"]，处理字符串
        for (String cpdomain : cpdomains) {
            int num = Integer.parseInt(cpdomain.split(" ")[0]);
            String domain1 = cpdomain.split(" ")[1];
            saveDomain(domain1, num, countMap);
            // 找到第一个 “.”出现位置，即下一级域名
            int indexFirstDot = domain1.indexOf(".");
            String domain2 = domain1.substring(indexFirstDot+1);
            saveDomain(domain2, num, countMap);
            // 找到第一个 “.”出现位置，即顶级域名
            int indexSecondDot = domain2.indexOf(".");
            String domain3 = domain2.substring(indexSecondDot+1);
            // 每个域名地址包含一个或两个"."符号。
            if (domain2.equals(domain3)) {
                continue;
            }
            saveDomain(domain3, num, countMap);
        }
        List<String> ans = new ArrayList<>();
        //　转换存储到List
        for (String domainName : countMap.keySet()) {
            int num = countMap.get(domainName);
            ans.add(num + " " + domainName);
        }
        return ans;
    }

    public void saveDomain(String domainName, int num, Map<String, Integer> countMap) {
        if (countMap.containsKey(domainName)) {
            int curNum = countMap.get(domainName);
            countMap.put(domainName, curNum + num);
        } else {
            countMap.put(domainName, num);
        }
    }
}

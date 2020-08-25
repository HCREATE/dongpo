package com.lhc.dongpo.mid;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: 最长不重复子串
 * User: jt.hao
 * Date: 2020-08-18
 * Time: 19:38
 */
public class LongestSubString {
    public int lengthOfLongestSubstring(String s) {
        if (null == s) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int res = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();

        while (start < n && end < n) {
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            } else {
                set.add(s.charAt(end));
                end++;
                res = Math.max(res, end - start);
            }
        }
        return res;
    }

    public int lengthOfLongestSubstringPro(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}

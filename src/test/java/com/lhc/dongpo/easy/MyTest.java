package com.lhc.dongpo.easy;

import com.lhc.dongpo.hard.Median;
import com.lhc.dongpo.mid.LongestSubString;
import com.lhc.dongpo.mid.TwoAdd;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * User: jt.hao
 * Date: 2020-08-14
 * Time: 10:16
 */
public class MyTest {

    @Test
    public void twoAdd() {
        TwoAdd twoAdd = new TwoAdd();
        TwoAdd.ListNode l1 = twoAdd.new ListNode(1);
        l1.next = twoAdd.new ListNode(1);

        TwoAdd.ListNode l2 = twoAdd.new ListNode(9);
        l2.next = twoAdd.new ListNode(9);
        TwoAdd.ListNode res = twoAdd.addTwoNumbers(l1, l2);
        System.out.println(res);
    }

    @Test
    public void longestSubString() {
        String s = "pwwkew";
        LongestSubString longestSubString = new LongestSubString();
        longestSubString.lengthOfLongestSubstring(s);
    }


    @Test
    public void median(){
        int[] nums1 = {1,2,5,7,9};
        int[] nums2 = {2,3,7,9,11};
        Median median = new Median();
        System.out.println(median.findMedianSortedArraysEasy(nums1, nums2));
    }


    @Test
    public void duplicateKey(){
        List<String> list = new LinkedList<>();
        list.add("apple");
        list.add("banana");
        list.add("banana");
        list.add("pear");
        Map<String, String> map = list.stream().
                collect(Collectors.
                        toMap(item -> item, item -> item, (item1, item2) -> item1));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.toString());
        }
    }

    @Test
    public void test1(){
        //false be593660f7f411ea2217099c646f0d07 be593660f7f411ea2217099c646f0d07
        System.out.println("be593660f7f411ea2217099c646f0d07".equals("be593660f7f411ea2217099c646f0d07"));
    }
}

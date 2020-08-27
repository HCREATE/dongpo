package com.lhc.dongpo.easy;

import com.lhc.dongpo.hard.Median;
import com.lhc.dongpo.mid.LongestSubString;
import com.lhc.dongpo.mid.TwoAdd;
import org.junit.jupiter.api.Test;

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
}

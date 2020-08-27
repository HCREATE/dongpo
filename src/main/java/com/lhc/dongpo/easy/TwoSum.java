package com.lhc.dongpo.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 1
 * User: jt.hao
 * Date: 2020-08-13
 * Time: 11:02
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if(map.containsKey(res) && map.get(res) != i){
                return new int[]{i, map.get(res)};
            }
        }
        throw new IllegalArgumentException("no such nums");
    }

    public int[] twoSumPro(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if(map.containsKey(res)){
                return new int[]{i, map.get(res)};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("no such nums");
    }
}

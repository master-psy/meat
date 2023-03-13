package com.dataStructure;

import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法
 *
 * @author Administrator
 * @Desc
 */
public class Permute {
    /**
     * 启动器
     *
     * @param args
     */
    public static void main(String[] args) {
        Permute backtrack = new Permute();
        System.out.println(backtrack.permute(new int[]{1, 2, 3}));
    }

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, track);
        return res;
    }

    /**
     * 路径：记录在 track 中
     * 选择列表：nums 中不存在于 track 的那些元素
     * 结束条件：nums 中的元素全都在 track 中出现
     *
     * @param nums
     * @param track
     */
    void backTrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            //
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //选择过的,跳过
            if (track.contains(nums[i])) {
                continue;
            }
            //做选择
            track.add(nums[i]);
            //进入下一节点
            backTrack(nums, track);
            //选择回退
            track.removeLast();
        }

    }

}

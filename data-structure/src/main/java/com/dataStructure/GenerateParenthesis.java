package com.dataStructure;

import java.util.List;
import java.util.Stack;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        String[] m = new String[]{"(",")"};
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m.length; j++) {

            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}


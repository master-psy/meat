package com.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Desc
 */
public class SolveNQueens {

    List<List<String>> res = new ArrayList<>();

    /**
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * <p>
     * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
     * <p>
     * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位
     *
     * @return
     */
    public List<List<String>> solveQueens(int n) {
        List<String> board = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            board.add(sb.toString());
        }
        backTrack(board, 0);
        return res;
    }

    void backTrack(List<String> board, int row) {
        if (board.size() == row) {
            res.add(new ArrayList<>(board));
            return;
        }
        int n = board.get(row)
                     .length();
        for (int col = 0; col < n; col++) {
            //排除不合法
            if (!isValid(board, row, col)) {
                continue;
            }
            //做选择
            StringBuilder sb = new StringBuilder(board.get(row));
            sb.setCharAt(col, 'Q');
            board.set(row, sb.toString());

            //下一步决策树
            backTrack(board, row + 1);

            //回退
            sb.setCharAt(col, '.');
            board.set(row, sb.toString());
        }
    }

    boolean isValid(List<String> board, int row, int col) {
        //由于是一行一行往下摆
        //下方不用考虑,当前行也不用考虑(左,右),所以值用考虑坐上方,上方(同列),右上方
        //当前总共行数
        int n = board.size();
        //判断列是否有皇后互相冲突
        for (int i = n - 1; i >= 0; i--) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        //或
        /*for (int i = 0; i < n; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }*/
        //或
       /* for (int i = row; i >= 0; i--) {
            if (board.get(i).charAt(col)=='Q') {
                return false;
            }
        }*/
        //检查左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        //检查右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> solveQueens = solveNQueens.solveQueens(8);
        solveQueens.forEach(e -> {
            e.forEach(a -> {
                System.out.println(a);
            });
            System.out.println("<========>");
        });
        //solveQueens.forEach(System.out::println);
        System.out.println(solveQueens.size());


    }

}

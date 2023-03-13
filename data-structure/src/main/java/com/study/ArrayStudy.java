package com.study;

import java.util.Arrays;

/**
 * @Desc
 */
public class ArrayStudy {
    public static void main(String[] args) {
        int x = 9;
        int y = 9;
        int[][] arr = new int[9][9];
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                arr[i - 1][j - 1] = j * i;
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                isValid(arr, i, j);
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    static void isValid(int[][] arr, int x, int y) {
        for (int i = arr[0].length - 1, j = 0; i >= 0 && j < arr[i].length; i--, j++) {
            System.out.println(arr[x][y] + "***" + arr[i][j]);
            if (new Integer(arr[x][y]).equals(arr[i][j])) {
                arr[x][y] = -1;
            }
        }


        //for (int i = x - 1; i >= 0; i--) {
        //    for (int j = 0; j < y; j++) {
        //        System.out.println(arr[x][y] + "***" + arr[i][j]);
        //        if (new Integer(arr[x][y]).equals(arr[i][j])) {
        //            arr[i][j] = -1;
        //        }
        //    }
        //}
    }
}

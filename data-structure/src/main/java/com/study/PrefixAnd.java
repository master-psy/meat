package com.study;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 前缀和
 *
 * @Desc
 */
public class PrefixAnd {


    public static void main(String[] args) {
        int n = 10, m = 11;

        int[] a = new int[n];
        int[] s = new int[m];
        Scanner sc = new Scanner(System.in);
        for (int i =1; i <= n; i++) {
            a[i]= Integer.parseInt(sc.nextLine());
        }
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + a[i];
        }
        System.out.println(Arrays.toString(s));

    }
}

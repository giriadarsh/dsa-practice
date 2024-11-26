package com.adarsh.dsa.dp.solutions;

public class LongestPalindromicSubsequence {

    public static int findLongestPalindromicSubsequence(String str, int[][] dp, int start, int end) {
        // Base case: Single character is a palindrome
        if (start == end) {
            return 1;
        }

        // Base case: Empty substring (start > end)
        if (start > end) {
            return 0;
        }

        // If already computed, return the stored value
        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        // If characters at 'start' and 'end' match, include them
        if (str.charAt(start) == str.charAt(end)) {
            dp[start][end] = 2 + findLongestPalindromicSubsequence(str, dp, start + 1, end - 1);
        } else {
            // Else, find the max by excluding either the start or end character
            int size1 = findLongestPalindromicSubsequence(str, dp, start, end - 1);
            int size2 = findLongestPalindromicSubsequence(str, dp, start + 1, end);
            dp[start][end] = Math.max(size1, size2);
        }

        return dp[start][end];
    }

    public static void main(String[] args) {
        String str = "malayalam";
        int n = str.length();
        int[][] dp = new int[n][n];

        // Initialize the dp array to -1 (indicating uncomputed states)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // Get the result: longest palindromic subsequence length
        int res = findLongestPalindromicSubsequence(str, dp, 0, n - 1);

        // Print the result
        System.out.println("Length of longest palindromic subsequence: " + res);
    }
}

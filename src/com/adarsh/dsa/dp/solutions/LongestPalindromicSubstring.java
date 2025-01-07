package com.adarsh.dsa.dp.solutions;

public class LongestPalindromicSubstring {

    private int palindromeStart = 0;
    private int palindromeEnd = 0;
    private int maxLength = 1; // Initialize maxLength to 1

    // Helper function to calculate the longest palindromic substring
    private int calculateLongestPalindrome(int start, int end, String input, int[][] dp) {

        if (start == end) { // Single character is always a palindrome
            dp[start][end] = 1;
            return 1;
        }

        if (start < end) {

            if (dp[start][end] > -1) { // If already calculated, return the value
                return dp[start][end];
            }

            int result;
            if (input.charAt(start) == input.charAt(end)) {
                result = calculateLongestPalindrome(start + 1, end - 1, input, dp);

                // Check if the substring between start+1 and end-1 is a palindrome
                if (result == (end - 1 - (start + 1) + 1)) {
                    dp[start][end] = result + 2;

                    // Update the maximum length and boundaries if a longer palindrome is found
                    if (dp[start][end] > maxLength) {
                        palindromeStart = start;
                        palindromeEnd = end;
                        maxLength = dp[start][end];
                    }

                    return dp[start][end];
                }
            }

            // Explore other possibilities
            int excludeStart = calculateLongestPalindrome(start + 1, end, input, dp);
            int excludeEnd = calculateLongestPalindrome(start, end - 1, input, dp);

            dp[start][end] = Math.max(excludeStart, excludeEnd);
            return dp[start][end];
        }

        return 0;
    }

    // Function to find the longest palindromic substring
    public String findLongestPalindrome(String input) {
        int length = input.length();
        int[][] dp = new int[length][length];

        // Initialize the dp array with -1
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                dp[i][j] = -1;
            }
        }

        calculateLongestPalindrome(0, length - 1, input, dp);
        return input.substring(palindromeStart, palindromeEnd + 1);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        String input = "malayalam";
        String result = solution.findLongestPalindrome(input);
        System.out.println("Longest Palindromic Substring: " + result);
    }
}

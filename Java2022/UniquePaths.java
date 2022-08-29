import java.util.*;
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePathsDP(3, 2));
        System.out.println(uniquePathsOP(3, 2));
    }
 /*
    Brute_force Approach :
       we need to find the all the possible vaild paths and returns its count
       so the best possible way to find the all valid pahts is using recrusion
       for moving the robot in the matrix we use i and j 2 pointers i denote the no of rows and j denotes the number of col
       base case : when we reach the end point (i== n-1 & j == n-1) and when i and j any of them goes out of bound
       Time = there are m*n element in the matrix for every element we have 2 choice either down or right so O(2^(m*n))
       space = Auxiliary stack space use by the recursive calls O(m*n)
 */
    public static int uniquePaths(int m, int n) {
        return path(0, 0, m, n);
    }
    public static int path(int i, int j, int m, int n) {
        if(i == m - 1 && j == n - 1) {
            return 1;
        }
        if(i >= m || j >= n) {
            return 0;
        }

        return path(i + 1, j, m, n) + path(i, j + 1, m, n);
    }
//    Using an 2d array to store the over lapping subproblem in the recursive tree
//    Time = we know that there can be total n *m possible states so apart from n * m states there no further recursion call even if there is out dp table have the answer
//    space = recursive stack space + sice of the 2d array == O(n*m) + O(n*m)
    public static int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[]a : dp) {
            Arrays.fill(a, -1);
        }
        return solve(0, 0, m, n, dp);
    }
    public static int solve(int i, int j, int m, int n, int[][] dp) {
        if(i>=m || j>=n){
            return 0;
        }
        if(i==m-1 && j==n-1){
            return 1;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        return dp[i][j] = solve(i + 1, j, m, n, dp) + solve(i, j + 1, m, n, dp);
    }
//    More optimized version
    /*
       1. every time we take 3 steps
       from obs2 we summarize that the total number of direction we take m - 1 + n - 1
       2. since we moving from start to end we need to take certain numbers of down directions and certain number of
       right directions
       so the possible right paths = m - 1  and possible down paths = n - 1
        I have a total of m+n-2 directions to choose among that if i am abel to put n - 1 right paths or m - 1 bottom paths
        any of that is gives it our answer (3c2 nad 3c1 == 3)

        to find the nCr we use pascal triangle = 10C3 = 8*9*10/3*2*1 last 3 elements from backwards for upper that depends on lower

     */
//    Time = O(m-1) or O(n - 1) space = O(1)
    public static int uniquePathsOP(int m, int n) {
        int N = m + n - 2;
        int r = m - 1;
        double ans = 1;
        for(int i = 1; i <=r; i++) {
            ans = ans * (N - r + i)/i;
        }
        return (int) ans;
    }
}

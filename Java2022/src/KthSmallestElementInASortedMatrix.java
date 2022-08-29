import java.util.*;
public class KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        int[][]matrix = {
                {1,5,9},
                {10,11,13},
                {12,14,15}
        };
        int k = 8;
        System.out.println(kthSmallest(matrix, k));
        System.out.println(kthSmallestOP(matrix, k));
    }
//    Brute force approach we make a max heap of size k and put the element in the heap when the size of heap> k we pop the element and return heap top
//    Time = O(nlongk) n = number of element here the number of element in the matrix == n ^ 2 so time -> O(n^2 log K)
//    space = O(k) size heap we make
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b)->(b - a));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max.add(matrix[i][j]);

                if(max.size() > k) {
                    max.poll();
                }
            }
        }
        return max.peek();
    }
/*
    Here the matrix is sorted, so we use binary search and upper bound(we use binary search on range not on the index)
        the low = m[0][0] hi = m[n-1][n-1]
    now mid = low + hi /2;
    now we check if the mid-element we get if it is kth element or nor
    for check that we check how many smaller element are there in the matrix means calculate the upper bound if it < k means it is not the answer
    low = mid + 1
    else hi = mid - 1;

 */
    public static int kthSmallestOP(int[][] matrix, int k) {
        int n = matrix.length;
//        binary search on range not the index
        int low = matrix[0][0];
        int hi = matrix[n-1][n-1];
        int ans = 0;
        while (low < hi) {

            int mid  = low + (hi - low) / 2;
            // to store the upper bound
            int count = upperBound(matrix, mid, n);

            if(count < k) {
                low = mid + 1;
            }
            else{

                hi = mid;
//              or we can do
//              hi = mid then the loop will run low < hi not <=
            }
        }
        return low;

    }
// The upperBound method helps to find the position the current mid
    private static int upperBound(int[][] matrix, int mid, int n) {
        int col = n - 1;
        int row = 0;
        int count = 0;
        while (row < n && col >= 0) {
            if(matrix[row][col] > mid) {
                col--;
            }
            else {
                count += col + 1; // position of the mid
                row++; // check the next row
            }
        }
        return count;
    }
}

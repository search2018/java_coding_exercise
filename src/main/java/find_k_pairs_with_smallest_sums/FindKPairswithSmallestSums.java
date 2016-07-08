package find_k_pairs_with_smallest_sums;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class FindKPairswithSmallestSums {
  /*
      Find K Pairs with Smallest Sums - Priority Queue
      Leetcode #373
      https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
      Difficulty: Medium
   */
  public class Solution {
    final int[][] neighbors = {{0, 1}, {1, 0}};

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<int[]> sPairs = new ArrayList<>();
      if (nums1 == null || nums1.length == 0 || nums2 == null
              || nums2.length == 0 || k == 0) return sPairs;

      int m = nums1.length, n = nums2.length;
      int p = k;
      boolean[][] visited = new boolean[m][n];
      Queue<Pair> pq = new PriorityQueue<>();
      pq.offer(new Pair(0, 0, nums1[0] + nums2[0]));
      visited[0][0] = true;
      while (p > 0 && !pq.isEmpty()) {
        Pair minSoFar = pq.poll();
        sPairs.add(new int[] {nums1[minSoFar.row], nums2[minSoFar.col]});
        p--;
        for (int[] neighbor : neighbors) {
          int row1 = minSoFar.row + neighbor[0];
          int col1 = minSoFar.col + neighbor[1];
          if (row1 < 0 || row1 == m || col1 < 0 || col1 == n || visited[row1][col1]) {
            continue;
          }
          visited[row1][col1] = true;
          pq.offer(new Pair(row1, col1, nums1[row1] + nums2[col1]));
        }
      }
      return sPairs;
    }

    private class Pair implements Comparable<Pair> {
      int row;
      int col;
      int value;

      Pair(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
      }

      public int compareTo(Pair other) {
        return value - other.value;
      }
    }
  }

  /*
      Find K Pairs with Smallest Sums - dijkstra
      Leetcode #373
      https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
      Difficulty: Medium
   */
  public class Solution_2 {
    private void search(PriorityQueue<int[]> pq, boolean[][] visited, int[] nums1, int[] nums2, int n1, int n2){
      if(n1 < nums1.length && n2 < nums2.length && !visited[n1][n2]){
        pq.add(new int[]{n1, n2});
        visited[n1][n2] = true;
      }
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<int[]> sPairs = new ArrayList<>();
      if (nums1 == null || nums1.length == 0 || nums2 == null
              || nums2.length == 0 || k == 0) return sPairs;

      PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
        public int compare(int[] pair1, int[] pair2){
          return (nums1[pair1[0]] + nums2[pair1[1]]) - (nums1[pair2[0]] + nums2[pair2[1]]);
        }
      });

      int length1 = nums1.length;
      int length2 = nums2.length;
      boolean[][] visited = new boolean[length1][length2];

      search(pq, visited, nums1, nums2, 0, 0);
      while(pq.size() > 0 && sPairs.size() < k){
        int[] next = pq.poll();
        sPairs.add(new int[]{nums1[next[0]], nums2[next[1]]});
        search(pq, visited, nums1, nums2, next[0] + 1, next[1]);
        search(pq, visited, nums1, nums2, next[0], next[1] + 1);
      }

      return sPairs;
    }
  }

  /*
      Find K Pairs with Smallest Sums - Two Points
      Leetcode #373
      https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
      Difficulty: Medium
   */
  public class Solution_3 {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<int[]> sPairs = new ArrayList<>();
      if (nums1 == null || nums1.length == 0 || nums2 == null
              || nums2.length == 0 || k == 0) return sPairs;

      int m = nums1.length, n = nums2.length;
      int p = k;

      int[] idx = new int[m]; // map to last used element in nums2
      while (p > 0) {
        int minSoFar = Integer.MAX_VALUE;
        int nums1pos = -1;
        // find smallest pair
        for (int i = 0; i < m; i++) {
          if (idx[i] >= n) {
            continue;
          }
          if (nums1[i] + nums2[idx[i]] < minSoFar) {
            minSoFar = nums1[i] + nums2[idx[i]];
            nums1pos = i;
          }
        }

        if (nums1pos == -1) {
          break;
        }

        int[] minPairVal = {nums1[nums1pos], nums2[idx[nums1pos]]};
        sPairs.add(minPairVal);
        idx[nums1pos]++;
        p--;
      }
      return sPairs;
    }
  }

  public static class UnitTest {
    @Test
    public void test1() {
      Solution sol = new FindKPairswithSmallestSums().new Solution();
      int[] test1 = {1, 7, 11};
      int[] test2 = {2, 4, 6};
      List<int[]> result = sol.kSmallestPairs(test1, test2, 3);
      assertEquals(3, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(2, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(1, result.get(1)[0]);
      assertEquals(4, result.get(1)[1]);
      assertEquals(2, result.get(2).length);
      assertEquals(1, result.get(2)[0]);
      assertEquals(6, result.get(2)[1]);

      test1 = new int[]{1, 1, 2};
      test2 = new int[]{1, 2, 3};
      result = sol.kSmallestPairs(test1, test2, 2);
      assertEquals(2, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(1, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(1, result.get(1)[0]);
      assertEquals(1, result.get(1)[1]);

      test1 = new int[]{1, 2};
      test2 = new int[]{3};
      result = sol.kSmallestPairs(test1, test2, 3);
      assertEquals(2, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(3, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(2, result.get(1)[0]);
      assertEquals(3, result.get(1)[1]);
    }

    @Test
    public void test2() {
      Solution_2 sol = new FindKPairswithSmallestSums().new Solution_2();
      int[] test1 = {1, 7, 11};
      int[] test2 = {2, 4, 6};
      List<int[]> result = sol.kSmallestPairs(test1, test2, 3);
      assertEquals(3, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(2, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(1, result.get(1)[0]);
      assertEquals(4, result.get(1)[1]);
      assertEquals(2, result.get(2).length);
      assertEquals(1, result.get(2)[0]);
      assertEquals(6, result.get(2)[1]);

      test1 = new int[]{1, 1, 2};
      test2 = new int[]{1, 2, 3};
      result = sol.kSmallestPairs(test1, test2, 2);
      assertEquals(2, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(1, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(1, result.get(1)[0]);
      assertEquals(1, result.get(1)[1]);

      test1 = new int[]{1, 2};
      test2 = new int[]{3};
      result = sol.kSmallestPairs(test1, test2, 3);
      assertEquals(2, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(3, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(2, result.get(1)[0]);
      assertEquals(3, result.get(1)[1]);
    }

    @Test
    public void test3() {
      Solution_3 sol = new FindKPairswithSmallestSums().new Solution_3();
      int[] test1 = {1, 7, 11};
      int[] test2 = {2, 4, 6};
      List<int[]> result = sol.kSmallestPairs(test1, test2, 3);
      assertEquals(3, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(2, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(1, result.get(1)[0]);
      assertEquals(4, result.get(1)[1]);
      assertEquals(2, result.get(2).length);
      assertEquals(1, result.get(2)[0]);
      assertEquals(6, result.get(2)[1]);

      test1 = new int[]{1, 1, 2};
      test2 = new int[]{1, 2, 3};
      result = sol.kSmallestPairs(test1, test2, 2);
      assertEquals(2, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(1, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(1, result.get(1)[0]);
      assertEquals(1, result.get(1)[1]);

      test1 = new int[]{1, 2};
      test2 = new int[]{3};
      result = sol.kSmallestPairs(test1, test2, 3);
      assertEquals(2, result.size());
      assertEquals(2, result.get(0).length);
      assertEquals(1, result.get(0)[0]);
      assertEquals(3, result.get(0)[1]);
      assertEquals(2, result.get(1).length);
      assertEquals(2, result.get(1)[0]);
      assertEquals(3, result.get(1)[1]);
    }
  }
}


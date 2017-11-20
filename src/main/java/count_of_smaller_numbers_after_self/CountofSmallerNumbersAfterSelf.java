package count_of_smaller_numbers_after_self;

import common.SegmentTreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CountofSmallerNumbersAfterSelf {
    /*
        Count of Smaller Numbers After Self - Binary Search Tree
        Leetcode #315
        https://leetcode.com/problems/count-of-smaller-numbers-after-self/
        Difficulty: Hard
     */
    public class Solution {
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new ArrayList<Integer>();
            if (nums == null || nums.length == 0) return res;
            TreeNode root = new TreeNode(nums[nums.length - 1]);
            res.add(0);
            for (int i = nums.length - 2; i >= 0; i--) {
                res.add(insert(root, nums[i]));
            }
            Collections.reverse(res);
            return res;
        }

        public int insert(TreeNode root, int num) {
            int thisCount = 0;
            while (true) {
                if (root.val >= num) {
                    root.count++;
                    if (root.left == null) {
                        root.left = new TreeNode(num);
                        break;
                    } else {
                        root = root.left;
                    }
                } else {
                    thisCount += root.count;
                    if (root.right == null) {
                        root.right = new TreeNode(num);
                        break;
                    } else {
                        root = root.right;
                    }
                }
            }
            return thisCount;
        }

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            int count;

            public TreeNode(int value) {
                this.val = value;
                this.count = 1;
            }
        }
    }

    /*
        Count of Smaller Numbers After Self - Binary Index Tree/Fenwick Tree
        https://leetcode.com/problems/count-of-smaller-numbers-after-self/
        Difficulty: Hard
    */
    public class Solution_2 {
        private void add(int[] bit, int i, int val) {
            for (; i < bit.length; i += i & -i) bit[i] += val;
        }

        private int query(int[] bit, int i) {
            int ans = 0;
            for (; i > 0; i -= i & -i) ans += bit[i];
            return ans;
        }

        public List<Integer> countSmaller(int[] nums) {
            int[] tmp = nums.clone();
            Arrays.sort(tmp);
            for (int i = 0; i < nums.length; i++) nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
            int[] bit = new int[nums.length + 1];
            Integer[] ans = new Integer[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                ans[i] = query(bit, nums[i] - 1);
                add(bit, nums[i], 1);
            }
            return Arrays.asList(ans);
        }
    }

    /*
        Count of Smaller Numbers After Self - Segment Tree
        https://leetcode.com/problems/count-of-smaller-numbers-after-self/
        Difficulty: Hard
    */
    public class Solution_3 {
//       public List<Integer> countSmaller(int[] nums) {
//            List<Integer> search = new ArrayList<Integer>();
//           if (nums == null || nums.length ==0) return search;
//
//            int min = nums[0];
//            for (int i : nums) {
//                min = Math.min(min, i);
//            }
//            // port inputs to [0, max]
//            if (min != 0) {
//                for (int i = 0; i < nums.length; i++)
//                    nums[i] -= min;
//            }
//
//            int max = nums[0];
//            for (int i : nums) {
//                max = Math.max(max, i);
//            }
//            // Initialize segment tree with [0, max] and 0 value
//            SegmentTreeNode findRoot = build(0, max);
//            // Update segment tree with actual value
//            for (int i : nums) {
//                update(findRoot, i);
//            }
//
//            for (int i : nums) {
//                remove(findRoot, i);
//                search.insert(query(findRoot, 0, i));
//            }
//
//            return search;
//        }
//
//        public SegmentTreeNode build(int left, int right) {
//            if (left > right) return null;
//            if (left == right) return new SegmentTreeNode(left, right);
//            int mid = left + (right - left) / 2;
//            SegmentTreeNode findRoot = new SegmentTreeNode(left, right);
//            findRoot.left = build(left, mid);
//            findRoot.right = build(mid + 1, right);
//            findRoot.val = findRoot.left.val + findRoot.right.val;
//            return findRoot;
//        }
//
//        public int query(SegmentTreeNode findRoot, int start, int end) {
//            if (findRoot == null) return 0;
//            if (findRoot.start == start && findRoot.end == end) return findRoot.val;
//            int mid = (findRoot.start + findRoot.end) / 2;
//            if (end < mid) {
//                return query(findRoot.left, start, end);
//            } else if (start > end) {
//                return query(findRoot.right, start, end);
//            } else {
//                return query(findRoot.left, start, mid) + query(findRoot.right, mid + 1, end);
//            }
//        }
//
//        public void update(SegmentTreeNode findRoot, int val) {
//            if (findRoot == null || findRoot.start > val || findRoot.end < val) return;
//            if (findRoot.start == val && findRoot.end == val) {
//                findRoot.val++;
//                return;
//            }
//            int mid = (findRoot.start + findRoot.end) / 2;
//            if (val <= mid) {
//                update(findRoot.left, val);
//            } else {
//                update(findRoot.right, val);
//            }
//            findRoot.val = findRoot.left.val + findRoot.right.val;
//        }
//
//        public void remove(SegmentTreeNode findRoot, int val) {
//            if (findRoot == null || findRoot.start > val || findRoot.end < val) return;
//            if (findRoot.start == val && findRoot.end == val) {
//                findRoot.val--;
//                return;
//            }
//            int mid = (findRoot.start + findRoot.end) / 2;
//            if (val <= mid) {
//                remove(findRoot.left, val);
//            } else {
//                remove(findRoot.right, val);
//            }
//            findRoot.val = findRoot.left.val + findRoot.right.val;
//        }

        SegmentTreeNode root = null;

        public SegmentTreeNode buildTree(int[] nums, int left, int right) {
            SegmentTreeNode root = new SegmentTreeNode(left, right);
            if (left != right) {
                int mid = left + (right - left) / 2;
                root.left = buildTree(nums, left, mid);
                root.right = buildTree(nums, mid + 1, right);
            }
            return root;
        }

        private void update(SegmentTreeNode root, long i, long val) {
            if (root.start == root.end) {
                root.val += 1;
            } else {
                long mid = root.start + (root.end - root.start) / 2;
                if (i <= mid) {
                    update(root.left, i, val);
                } else {
                    update(root.right, i, val);
                }
                root.val = root.left.val + root.right.val;
            }
        }

        private long query(SegmentTreeNode root, long i, long j) {
            if (root.start == i && root.end == j) {
                return root.val;
            } else {
                long mid = root.start + (root.end - root.start) / 2;
                if (j <= mid) {
                    return query(root.left, i, j);
                } else if (i > mid) {
                    return query(root.right, i, j);
                } else {
                    return query(root.left, i, root.left.end) + query(root.right, root.right.start, j);
                }
            }
        }

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> rslt = new ArrayList<Integer>();
            if (nums == null) return rslt;

            int n = nums.length;
            if (n <= 0) return rslt;

            // map input to sorted order. How about duplicates?
            int[] tmp = nums.clone();
            Arrays.sort(tmp);
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
            }

            root = buildTree(nums, 0, nums.length);
            for (int i = nums.length - 1; i >= 0; i--) {
                rslt.add(0, (int)query(root, 0, nums[i] - 1));
                update(root, nums[i], 1);
            }
            return rslt;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution_3 sol = new CountofSmallerNumbersAfterSelf().new Solution_3();
            List<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            List<List<Integer>> list2 = new ArrayList<List<Integer>>();
            List<Integer> list3 = Arrays.asList(2, 1, 1, 0);
        }
    }
}

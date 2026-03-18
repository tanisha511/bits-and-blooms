package arrays;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
@RunWith(Parameterized.class)
public class TwoSum {

    // ================== SOLUTION ==================

    /*
     * Idea:
     * Use a map to remember numbers we've seen.
     * For each number x → check if (target - x) exists already.
     * If yes → return indices.
     *
     * This avoids O(n^2) brute force → runs in O(n).
     */
    public int[] twoSum(int[] nums, int target) {

        // Map: value → index of previously seen elements (O(1) lookup)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];
            int needed = target - current; // value required to form target

            // If we've already seen the needed value → pair found
            if (map.containsKey(needed)) {
                return new int[]{map.get(needed), i};
            }

            // Store current AFTER check to avoid using same element twice
            map.put(current, i);
        }

        // No valid pair (usually not expected per problem constraints)
        return null;
    }


    // ================== PARAMETERIZED TEST SETUP ==================

    private final int[] nums;
    private final int target;
    private final boolean hasSolution;

    public TwoSum(int[] nums, int target, boolean hasSolution) {
        this.nums = nums;
        this.target = target;
        this.hasSolution = hasSolution;
    }

    @Parameterized.Parameters(name = "{index}: target={1}, hasSolution={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{2, 7, 11, 15}, 9, true},
                {new int[]{3, 2, 4}, 6, true},
                {new int[]{3, 3}, 6, true},
                {new int[]{1, 2, 3}, 10, false},
                {new int[]{5}, 5, false}
        });
    }

    // ================== TEST ==================

    @Test
    public void testTwoSum() {

        int[] result = twoSum(nums, target);

        if (hasSolution) {
            Assert.assertNotNull(result);
            Assert.assertEquals(2, result.length);

            int i = result[0];
            int j = result[1];

            // indices must be valid
            Assert.assertTrue(i >= 0 && i < nums.length);
            Assert.assertTrue(j >= 0 && j < nums.length);

            // indices must be different
            Assert.assertNotEquals(i, j);

            // actual correctness check (not index-order dependent)
            Assert.assertEquals(target, nums[i] + nums[j]);

        } else {
            Assert.assertNull(result);
        }
    }
}


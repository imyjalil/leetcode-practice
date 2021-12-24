package longestIncreasingSubsequence;

//problem link: https://leetcode.com/problems/longest-increasing-subsequence/
//Author: imyjalil
public class solution {
	public int lengthOfLIS(int[] nums) {
        int ans = 0, n = nums.length;
        // for(int i=0;i<n;i++)
        // {
        //     ans = Math.max(ans, 1 + solveRecursive(nums, i));
        // }
        // int[] dp=new int[n];
        // Arrays.fill(dp,-1);
        // for(int i=0;i<n;i++)
        // {
        //     ans = Math.max(ans, 1 + solveMemoize(nums, i, dp));
        // }
        // return ans;
        return solveIterative(nums);
    }
    public int solveRecursive(int nums[], int i)
    {
        int ans=0;
        for(int j=0;j<i;j++)
        {
            if(nums[i] > nums[j])
                ans = Math.max(ans, 1+solveRecursive(nums, j));
        }
        return ans;
    }
    public int solveMemoize(int nums[], int i, int []dp)
    {
        if(dp[i]!=-1) return dp[i];
        int ans=0;
        for(int j=0;j<i;j++)
        {
            if(nums[i] > nums[j])
                ans = Math.max(ans, 1+solveMemoize(nums, j, dp));
        }
        dp[i]=ans;
        return ans;
    }
    public int solveIterative(int nums[])
    {
        int n = nums.length, ans = 1;
        int dp[] = new int[n];
        Arrays.fill(dp,1);
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(nums[i]>nums[j])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

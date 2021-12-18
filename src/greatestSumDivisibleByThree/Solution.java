package greatestSumDivisibleByThree;

public class Solution {
	public int maxSumDivThree(int[] nums) {
        //find a subset with largest sum such that sum is divisible by 3
//         return solveRecursive(nums, 0, 0);
        
//         int[][] dp=new int[3][nums.length];
//         for(int i=0;i<3;i++)
//             Arrays.fill(dp[i],-1);
//         int ans = solveMemoize(nums, 0, 0, dp);
//         return ans == Integer.MIN_VALUE ? 0 : ans;
        return solveIterative(nums);
    }
    public int solveRecursive(int[]nums, int ind, int currentSum)
    {
        int n = nums.length;
        if(ind>=n) return currentSum%3==0?currentSum:0;
        int ans=0;
        ans = Math.max(solveRecursive(nums, ind+1, currentSum),//leave current index
                        solveRecursive(nums, ind+1, currentSum+nums[ind]));//add current index
        return ans;
    }
    public int solveMemoize(int[]nums, int ind, int currentSum, int[][] dp)
    {
        int n = nums.length;
        if(ind>=n) return currentSum==0?currentSum:Integer.MIN_VALUE;
        if(dp[currentSum][ind]!=-1) return dp[currentSum][ind];
        int ans=0;
        int sumBySkippingCurrIndex=solveMemoize(nums, ind+1, currentSum, dp);
        int sumByTakingCurrIndex=solveMemoize(nums, ind+1, (currentSum+nums[ind])%3, dp);
        //why modulo 3? - there are too many values for currentsum-could go upto 4*10^4*10^4 in the worst case
        //hence storing only modulo 3 ensures that we have the cache array of size only nums.length*3(3 possible values after modulus)
        if(sumByTakingCurrIndex != Integer.MIN_VALUE)//if there is no possible subset by considering current element, let it remain min value
            sumByTakingCurrIndex += nums[ind];
        ans = Math.max(sumBySkippingCurrIndex, sumByTakingCurrIndex);
        dp[currentSum][ind]=ans;
        return ans;
    }
    public int solveIterative(int[] nums)
    {
        int n = nums.length;
        int[][] dp= new int[3][n+1];
        int currentSum=nums[nums.length-1];
        dp[1][n]=Integer.MIN_VALUE;dp[2][n]=Integer.MIN_VALUE;//taken from if(ind>=n) return currentSum==0?currentSum:Integer.MIN_VALUE;
        for(int i=n-1;i>=0;i--)
        {
            for(int j=0;j<3;j++)
            {
                dp[j][i]=Math.max(dp[j][i+1],//skip
                                  nums[i]+dp[(j+nums[i])%3][i+1]);//pick
            }
        }
        return dp[0][0];
    }
}

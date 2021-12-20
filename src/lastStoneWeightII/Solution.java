package lastStoneWeightII;

//problem link: https://leetcode.com/problems/last-stone-weight-ii/
//Author: imyjalil
public class Solution {
	int total=0;
    public int lastStoneWeightII(int[] stones) {
        for(int stone:stones) total += stone;
        //return solveRecursive(stones,stones.length-1,0);
        //int[][] dp=new int[stones.length][3000];
        //for(int i=0;i<stones.length;i++) Arrays.fill(dp[i],-1);
        //return solveMemoize(stones, stones.length-1, 0, dp);
        //return solveIterative(stones);
        return solveIterativeMinimized(stones);
    }
    public int solveRecursive(int[] stones, int ind, int sum)
    {
        if(ind<0)
            return Math.abs(total-2*sum);
        //divide problem into sub problem of finding a subset
        //such that total - 2*(sum of subset is minimised)
        //s1, s2 be two mutually exclusive subsets such that s1+s2=total
        //s2=total-s1
        //s1-s2=s1-(total-s1)=2*s1-total
        return Math.min(solveRecursive(stones, ind-1, sum+stones[ind]),
                        solveRecursive(stones, ind-1, sum));
    }
    public int solveMemoize(int[] stones, int ind, int sum, int[][] dp)
    {
        if(ind<0)
            return Math.abs(total-2*sum);
        if(dp[ind][sum]!=-1) return dp[ind][sum];
        dp[ind][sum] = Math.min(solveMemoize(stones, ind-1, sum+stones[ind], dp),
                        solveMemoize(stones, ind-1, sum, dp));
        return dp[ind][sum];
    }
    public int solveIterative(int[] stones)
    {
        int n = stones.length;
        int[][] dp=new int[n][3000];
        for(int sum=3000-1;sum>=0;sum--)
            dp[0][sum]=Math.abs(total-2*sum);
        for(int ind=1;ind<n;ind++)
        {
            for(int sum=3000-1;sum>=0;sum--)
            {
                dp[ind][sum]=dp[ind-1][sum];
                if(sum+stones[ind]<3000)
                    dp[ind][sum]=Math.min(dp[ind-1][sum+stones[ind]],dp[ind][sum]);
            }
        }
        return dp[n-1][0];
    }
    public int solveIterativeMinimized(int[] stones)
    {
        int n = stones.length;
        int[][] dp=new int[n][total+1];
        for(int sum=total;sum>=0;sum--)
            dp[0][sum]=Math.abs(total-2*sum);
        for(int ind=1;ind<n;ind++)
        {
            for(int sum=total;sum>=0;sum--)
            {
                dp[ind][sum]=dp[ind-1][sum];
                if(sum+stones[ind]<=total)
                    dp[ind][sum]=Math.min(dp[ind-1][sum+stones[ind]],dp[ind][sum]);
            }
        }
        return dp[n-1][0];
    } 
}

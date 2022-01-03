package numberOfDiceRollsWithTargetSum;

//problem link: https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
//Author: imyjalil
public class Solution {
	public int numRollsToTarget(int n, int k, int target) {
        //return numRollsToTargetRecursive(n, k,target,dp);
        // int dp[][]=new int[n+1][target+1];
        // for(int i=0;i<=n;i++)
        //     Arrays.fill(dp[i],-1);
        // return numRollsToTargetMemoize(n, k,target,dp);
        return numRollsToTargetDP(n,k,target);
    }
    public int numRollsToTargetDP(int n, int k, int target)
    {
        int[][] dp=new int[n+1][target+1];
        for(int i=0;i<dp[0].length;i++)
            dp[0][i]=0;
        dp[0][0]=1;
        for(int i=1;i<=n;i++)
        {
            for(int j=0;j<=target;j++)
            {
                for(int l=1;l<=k;l++)
                {
                    if(j-l>=0)
                        dp[i][j]=(dp[i][j]+dp[i-1][j-l])%1000000007;
                }
            }
        }
        return dp[n][target];
    }
    public int numRollsToTargetMemoize(int n, int k, int target,int[][] dp){
        if(target<0 || n==0)
            return target==0?1:0;
        if(dp[n][target]!=-1) return dp[n][target];
        int ans = 0;
        for(int i=1;i<=k;i++)
            ans = (ans+numRollsToTargetMemoize(n-1,k,target-i,dp))%1000000007;
        return dp[n][target]=ans;
    }
    public int numRollsToTargetRecursive(int n, int k, int target){
        if(target<0 || n==0)
            return target==0?1:0;
        
        int ans = 0;
        for(int i=1;i<=k;i++)
            ans = (ans+numRollsToTargetRecursive(n-1,k,target-i))%mod;
        return ans;
    }
}

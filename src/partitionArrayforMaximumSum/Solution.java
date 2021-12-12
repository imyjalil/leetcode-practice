package partitionArrayforMaximumSum;

//problem link: https://leetcode.com/problems/partition-array-for-maximum-sum/
//Author: imyjalil

public class Solution {
	
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp,-1);
        return solveIterative(arr,k);
    }
	
	public int solveMemoize(int[] arr, int k, int ind, int[] dp)
	{
        if(ind>=arr.length) return 0;
        if(dp[ind]!=-1) return dp[ind];
		int curr_max=0,ans=0;
		for(int i=ind;i<Math.min(ind+k, arr.length);i++)//currentpartition will be until index i
		{//hence there will be two sums - one until i and the next partition from i+1
			curr_max = Math.max(curr_max, arr[i]);
			int ansUptoCurrIndex=curr_max*(i+1-ind);
			int ansAfterCurrIndex=solveMemoize(arr,k,i+1,dp);
            //System.out.println("ansUptoCurrIndex for "+ind+","+i+"->"+ansUptoCurrIndex);
			//System.out.println("ansAfterCurrIndex for "+(i+1)+",->"+ansAfterCurrIndex);
            ans = Math.max(ans, ansUptoCurrIndex + ansAfterCurrIndex);
		}
        dp[ind]=ans;
		return ans;
	}
    
    public int solveRecursive(int[] arr, int k, int ind)
	{
        if(ind>=arr.length) return 0;
		int curr_max=0,ans=0;
		for(int i=ind;i<Math.min(ind+k, arr.length);i++)
		{
			curr_max = Math.max(curr_max, arr[i]);
			int ansUptoCurrIndex=curr_max*(i+1-ind);
			int ansAfterCurrIndex=solveRecursive(arr,k,i+1);
            //System.out.println("ansUptoCurrIndex for "+ind+","+i+"->"+ansUptoCurrIndex);
			//System.out.println("ansAfterCurrIndex for "+(i+1)+",->"+ansAfterCurrIndex);
            ans = Math.max(ans, ansUptoCurrIndex + ansAfterCurrIndex);
		}
		return ans;
	}
    
    public int solveIterative(int[] arr, int k)
    {
        int n = arr.length;
        int dp[]=new int[n+1];
        dp[0]=0;
        for(int i=1;i<=n;i++)
        {
            int curr_max=0;
            for(int j=i-1;j>=Math.max(0,i-k);j--)//current partition will be at j
            {//and hence two sums - one until j and the other from j+1 to i
                curr_max=Math.max(curr_max,arr[j]);
                dp[i]=Math.max(dp[i],(i-j)*curr_max+dp[j]);//for the second sum, find the max in the range [i,j] and multiply it with size of partition i.e (i-j)
            }
        }
        return dp[n];
    }

}

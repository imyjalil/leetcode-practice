package bestTimeToBuyAndSellStockWithCooldown;

//problem link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
//Author: imyjalil
public class Solution {
	public int solveMemoize(int[] prices, int day, int buy, int dp[][])
    {
        int ans=0, n = prices.length;
        if(day>=n)
            return 0;
        if(dp[buy][day]!=-1) return dp[buy][day];
        int ans2=0,ans1=solveMemoize(prices, day+1, buy, dp);//no transaction on current day
        if(buy==1){
            ans2=-prices[day]+solveMemoize(prices, day+1, 0, dp);
        }
        else{
            ans2=prices[day]+solveMemoize(prices,day+2,1,dp);
        }
        ans=Math.max(ans1, ans2);
        dp[buy][day]=ans;
        return ans;
    }
    public int solveIterative(int[] prices){
        int dp[][]=new int[prices.length+2][2];
        for(int day=prices.length-1;day>=0;day--)
        {
            // for(int buy=0;buy<=1;buy++)
            // {
            //     int ans2=0,ans1=dp[day+1][buy];
            //     if(buy==1)
            //         ans2=-prices[day]+dp[day+1][0];
            //     else
            //         ans2=prices[day]+dp[day+2][1];
            //     dp[day][buy]=Math.max(ans1,ans2);
            // }
            dp[day][1]=Math.max(dp[day+1][1],-prices[day]+dp[day+1][0]);
            dp[day][0]=Math.max(dp[day+1][0],prices[day]+dp[day+2][1]);
        }
        return dp[0][1];
    }
    public int maxProfit(int[] prices) {
        //int dp[][]=new int[2][prices.length];
        //Arrays.fill(dp[0],-1);Arrays.fill(dp[1],-1);
        //return solveMemoize(prices,0,1,dp);
        return solveIterative(prices);
    }
}

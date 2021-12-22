package minimumSidewayJumps;

//problem link: https://leetcode.com/problems/minimum-sideway-jumps/
//Author: imyjalil
public class Solution {
	//Lane 1 - 0
    //Lane 2 - 1
    //Lane 3 - 2
    public int minSideJumps(int[] obstacles) {
        //return solveRecursive(obstacles, 0, 1);
        //int[][] dp=new int[3][obstacles.length];
        //for(int i=0;i<3;i++) Arrays.fill(dp[i],-1);
        //return solveMemoize(obstacles, 0, 1, dp);
        return solveIterative(obstacles);
    }
    public int solveRecursive(int[] obstacles, int point, int lane)
    {
        if(point==obstacles.length-1) return 0;
        int ans=0;
        if(obstacles[point+1]==lane+1)//there's an obstacle on this lane
        {//jump to a different lane
            int loc1=(lane+1)%3, loc2=(lane+2)%3;
            int ans1 = Integer.MAX_VALUE, ans2 = Integer.MAX_VALUE;
            if(obstacles[point]!=loc1+1)
            {
                //no obstacle on loc1, can move there
                ans1 = solveRecursive(obstacles, point+1, loc1);
            }
            if(obstacles[point]!=loc2+1)
            {
                //no obstacle on loc2, can move there
                ans2 = solveRecursive(obstacles, point+1, loc2);
            }
            ans = 1 + Math.min(ans1, ans2);
        }
        else//no obstacle
        {
            ans = solveRecursive(obstacles, point+1, lane);//stay in the same lane
        }
        return ans;
    }
    public int solveMemoize(int[] obstacles, int point, int lane, int[][] dp)
    {
        if(point==obstacles.length-1) return 0;
        if(dp[lane][point]!=-1) return dp[lane][point];
        int ans=0;
        if(obstacles[point+1]==lane+1)
        {
            int loc1=(lane+1)%3, loc2=(lane+2)%3;
            int ans1 = Integer.MAX_VALUE, ans2 = Integer.MAX_VALUE;
            if(obstacles[point]!=loc1+1)
            {
                ans1 = solveMemoize(obstacles, point+1, loc1, dp);
            }
            if(obstacles[point]!=loc2+1)
            {
                ans2 = solveMemoize(obstacles, point+1, loc2, dp);
            }
            ans = 1 + Math.min(ans1, ans2);
        }
        else
        {
            ans = solveMemoize(obstacles, point+1, lane, dp);
        }
        dp[lane][point]=ans;
        return ans;
    }
    public int solveIterative(int[] obstacles)
    {
        int n = obstacles.length;
        int[][] dp=new int[3][n];
        for(int point=n-2;point>=0;point--)
        {
            for(int lane=0;lane<3;lane++)
            {
                if(obstacles[point+1]==lane+1)
                {
                    int loc1=(lane+1)%3, loc2=(lane+2)%3;
                    int ans1=Integer.MAX_VALUE, ans2 = Integer.MAX_VALUE;
                    if(obstacles[point]!=loc1+1)
                        ans1=dp[loc1][point+1];
                    if(obstacles[point]!=loc2+1)
                        ans2=dp[loc2][point+1];
                    dp[lane][point]=1+Math.min(ans1,ans2);
                }
                else
                    dp[lane][point]=dp[lane][point+1];
            }
        }
        return dp[1][0];
    }
}

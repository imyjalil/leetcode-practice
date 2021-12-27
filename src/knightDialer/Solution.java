package knightDialer;

//problem link: https://leetcode.com/problems/knight-dialer/
//Author: imyjalil
public class Solution {
	public static final int mod = (int) Math.pow(10, 9) + 7;
    public int knightDialer(int n) {
        long s = 0;
        // for(int i=0;i<4;i++)
        // {
        //     for(int j=0;j<3;j++)
        //     {
        //         s=(s+numPathsRecursive(i,j,n))%mod;
        //     }
        // }
        // return (int) s;
        int dp[][][]=new int[4][3][n+1];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<3;j++)
            {
                Arrays.fill(dp[i][j],-1);
            }
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<3;j++)
            {
                s=(s+numPathsMemoize(i,j,n,dp))%mod;
            }
        }
        return (int) s;
    }
    public long numPathsRecursive(int i, int j, int n)
    {
        if(i<0 || j<0 || i>=4 || j>=3  || (i==3 && j!=1)) return 0;
        if(n==1) return 1;
        long s =    numPathsRecursive(i-1,j-2,n-1)%mod+
                    numPathsRecursive(i-2,j-1,n-1)%mod+
                    numPathsRecursive(i-2,j+1,n-1)%mod+
                    numPathsRecursive(i-1,j+2,n-1)%mod+
                    numPathsRecursive(i+1,j+2,n-1)%mod+
                    numPathsRecursive(i+2,j+1,n-1)%mod+
                    numPathsRecursive(i+2,j-1,n-1)%mod+
                    numPathsRecursive(i+1,j-2,n-1)%mod;
        return s;
    }
    public long numPathsMemoize(int i, int j, int n,int[][][] dp)
    {
        if(i<0 || j<0 || i>=4 || j>=3  || (i==3 && j!=1)) return 0;
        if(n==1) return dp[i][j][n]=1;
        if(dp[i][j][n]!=-1) return dp[i][j][n];
        long s =    numPathsMemoize(i-1,j-2,n-1,dp)%mod+
                    numPathsMemoize(i-2,j-1,n-1,dp)%mod+
                    numPathsMemoize(i-2,j+1,n-1,dp)%mod+
                    numPathsMemoize(i-1,j+2,n-1,dp)%mod+
                    numPathsMemoize(i+1,j+2,n-1,dp)%mod+
                    numPathsMemoize(i+2,j+1,n-1,dp)%mod+
                    numPathsMemoize(i+2,j-1,n-1,dp)%mod+
                    numPathsMemoize(i+1,j-2,n-1,dp)%mod;
        dp[i][j][n]=(int)(s%mod);
        return s;
    }
}

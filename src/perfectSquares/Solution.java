package perfectSquares;

//problem link: https://leetcode.com/problems/perfect-squares/
//Author: imyjalil
public class Solution {
	List<Integer>squares;
    int dp[];
    public int numSquaresMemoized(int n) {
        squares=new ArrayList<>();
        for(int i=1;i*i<=n;i++)
            squares.add(i*i);
        dp=new int[n+1];
        Arrays.fill(dp,-1);
        return numWays(n);
    }
    public int numWays(int n)
    {
        if(dp[n]!=-1) return dp[n];
        int ans=Integer.MAX_VALUE;
        for(int num:squares)
        {
            if(num>n)
                break;
            ans = Math.min(ans, 1+numWays(n-num));
        }
        if(ans==Integer.MAX_VALUE) dp[n]=0;
        else dp[n]=ans;
        return dp[n];
    }
    public int numSquaresIterative(int n)
    {
        squares=new ArrayList<>();
        int dp[]=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=n;i++)
        {
            //System.out.println("i="+i);
            for(int j=1;j*j<=i;j++)
            {
                dp[i]=Math.min(dp[i],1+dp[i-j*j]);

            }
        }
        return dp[n];
    }
    public int numSquaresBFS(int n)
    {
        int cntPerfectSquares[]=new int[n+1];
        Set<Integer>perfectSquares = new HashSet<>();
        Queue<Integer>q=new LinkedList<>();
        for(int i=1;i*i<=n;i++)
        {
            perfectSquares.add(i*i);
            q.add(i*i);//add all the perfect squares
            cntPerfectSquares[i*i]=1;
        }
        int currSteps=1;
        if(cntPerfectSquares[n]==1) return currSteps;
        while(!q.isEmpty())//aim of this bfs is to advance forward by adding perfect squares until we come across n
        {
            currSteps++;
            int len = q.size();
            for(int i=0;i<len;i++)
            {
                int ele=q.poll();
                for(int square:perfectSquares)//add all perfect squares to the current number to advance to the next level
                {
                    if(ele+square==n) return currSteps;
                    if(ele+square<n && cntPerfectSquares[ele+square]==0)// if this value is non-zero, it means that this value i.e ele+square was formed earlier itself and hence, no need to modify it again
                    {
                        q.offer(ele+square);
                        cntPerfectSquares[ele+square]=currSteps;
                    }
                }
            }
        }
        return 0;
    }
}

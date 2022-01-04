package asFarFromLandAsPossible;

//problem link: https://leetcode.com/problems/as-far-from-land-as-possible/submissions/
//Author: imyjalil
public class Solution {
	public boolean isValid(int x, int y, int n)
    {
        if(x>=0 && x<n && y>=0 && y<n) return true;
        return false;
    }
	//TC:O(n*n)
    public int maxDistance(int[][] grid) {
        int n = grid.length, ans = 0;
        int dis[][]=new int[n][n];
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]==1)
                    q.offer(new int[]{i,j,0});
                dis[i][j]=Integer.MAX_VALUE;
            }
        }
        if(q.size()==0 || q.size()==n*n) return -1;
        while(!q.isEmpty())
        {
            int[]loc = q.poll();
            int x =loc[0], y = loc[1], d = loc[2];
            if(dis[x][y]>d)
            {
                dis[x][y]=d;
                if(isValid(x+1,y,n) && grid[x+1][y]==0) {q.offer(new int[]{x+1,y,d+1});grid[x+1][y]=1;}
                if(isValid(x-1,y,n) && grid[x-1][y]==0) {q.offer(new int[]{x-1,y,d+1});grid[x-1][y]=1;}
                if(isValid(x,y+1,n) && grid[x][y+1]==0) {q.offer(new int[]{x,y+1,d+1});grid[x][y+1]=1;}
                if(isValid(x,y-1,n) && grid[x][y-1]==0) {q.offer(new int[]{x,y-1,d+1});grid[x][y-1]=1;}
            }
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                ans = Math.max(ans, dis[i][j]);
            }
        }
        return ans;
    }
}

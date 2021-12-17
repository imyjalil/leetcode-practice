package rottingOranges;

//problem link: https://leetcode.com/problems/rotting-oranges/
//Author: imyjalil
public class Solution {
	//TC: O(N * M) -> each cell is visited at least once
	//SC: O(N * M) -> in the worst case if all the oranges are rotten they will be added to the queue
	public int orangesRotting(int[][] grid) {
        int cnt=0, n=grid.length, m=grid[0].length;
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]==1)
                    cnt++;
                else if(grid[i][j]==2)
                    q.offer(new int[]{i,j});
            }
        }
        int steps=0;
        if(cnt==0) return steps;
        int dirs[]=new int[]{1,0,-1,0,1};
        while(!q.isEmpty())
        {
            ++steps;
            int len=q.size();
            for(int i=0;i<len;i++)
            {
                int loc[]=q.poll();
                for(int j=0;j<4;j++)
                {
                    int nr=loc[0]+dirs[j], nc=loc[1]+dirs[j+1];
                    if(nr<0 || nc<0 || nr>=n || nc>=m || grid[nr][nc]!=1) continue;
                    cnt--;
                    if(cnt==0) return steps;
                    grid[nr][nc]=2;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return -1;
    }
}

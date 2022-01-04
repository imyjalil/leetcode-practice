package detectCyclesIn2DGrid;

//problem link:https://leetcode.com/problems/detect-cycles-in-2d-grid/
//Author: imyjalil
public class Solution {
	public boolean isValid(int x, int y, int m, int n)
    {
        if(x>=m || x<0 || y>=n || y<0)
            return false;
        return true;
    }
    //TC:O(m*n)
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean visited[][]=new boolean[m][n];
        for(int i=0;i<m;i++) Arrays.fill(visited[i], false);
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(!visited[i][j] && dfs(i,j,grid,-1,-1,visited))
                    return true;
            }
        }
        return false;
    }
    public boolean dfs(int i, int j, char[][] grid, int pi,int pj,boolean[][] visited)
    {
        visited[i][j]=true;
        int dirs[] = new int[]{1,0,-1,0,1};
        for(int m=0;m<4;m++)
        {
            int x = i+dirs[m], y = j+dirs[m+1];
            if(isValid(x,y,grid.length,grid[0].length) && (!(x==pi && y==pj)) && grid[x][y]==grid[i][j] && (visited[x][y] || dfs(x,y,grid,i,j,visited)))
                return true;
        }
        return false;
    }
}

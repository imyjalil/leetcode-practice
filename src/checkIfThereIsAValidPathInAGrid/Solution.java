package checkIfThereIsAValidPathInAGrid;

//problem link:https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
//Author: imyjalil
public class Solution {
	public boolean hasValidPath(int[][] grid) {
        return solveBFS(grid);
    }
	//TC:O(m*n)
    public boolean solveBFS(int[][] grid){
        int[][][] dirs={
            {{0, -1}, {0, 1}},//1
            {{-1, 0}, {1, 0}},//2
            {{0, -1}, {1, 0}},//3
            {{0, 1}, {1, 0}},//4
            {{0, -1}, {-1, 0}},//5
            {{0, 1}, {-1, 0}}//6
        };
        int m = grid.length, n = grid[0].length;
        boolean[][] visited=new boolean[m][n];
        Queue<int[]>q=new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0]=true;
        while(!q.isEmpty()){
            int[]cur = q.poll();
            int x=cur[0], y=cur[1];
            int num=grid[x][y]-1;
            for(int[]dir:dirs[num]){
                int nx=x+dir[0],ny=y+dir[1];
                if(nx<0 || nx>=m || ny<0 || ny>=n || visited[nx][ny]) continue;
                for(int[] prev:dirs[grid[nx][ny]-1])//check if we can come back to the same location after moving ahead, if not there's no use going to that cell
                {
                    if(nx+prev[0]==x && ny+prev[1]==y)
                    {
                        visited[nx][ny]=true;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        return visited[m-1][n-1];
    }
}

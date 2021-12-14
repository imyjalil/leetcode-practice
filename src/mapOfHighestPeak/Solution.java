package mapOfHighestPeak;

//problem link: https://leetcode.com/problems/map-of-highest-peak/
//Author: imyjalil
public class Solution {
	 public int[][] highestPeak(int[][] isWater) {
	        int n=isWater.length;
	        int m=isWater[0].length;
	        int res[][] = new int[n][m];
	        Queue<int[]>q=new LinkedList<>();
	        for(int i=0;i<n;i++)
	        {
	            for(int j=0;j<m;j++)
	            {
	                if(isWater[i][j]==1)
	                {
	                    res[i][j]=0;
	                    q.offer(new int[]{i,j});
	                }
	                else
	                {
	                    res[i][j]=-1;
	                }
	            }
	        }
	        int dirs[]={1,0,-1,0,1};
	        while(!q.isEmpty())//multi source bfs, start from zero cells and expand into allthose cells which are not filled earlier
	        {
	            int[]dir=q.poll();
	            for(int i=0;i<4;i++)
	            {
	                int newX=dir[0]+dirs[i];
	                int newY=dir[1]+dirs[i+1];
	                if(newX>=0 && newX<n && newY>=0 && newY<m && res[newX][newY]==-1)
	                {
	                    res[newX][newY] = res[dir[0]][dir[1]]+1;
	                    q.offer(new int[]{newX,newY});
	                }
	            }
	        }
	        return res;
	    }
	}
}

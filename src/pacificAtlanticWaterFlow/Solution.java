package pacificAtlanticWaterFlow;

public class Solution {
	//TODO: refractor code to define only only one function for bfs so that it can be used for bfs by both pacific and atlantic
    public boolean isValid(int x, int y, int m, int n)
    {
        if(x>=m || x<0 || y>=n || y<0)
            return false;
        return true;
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        List<List<Integer>>res=new ArrayList<>();
        int[][] bfs = new int[m][n];
        bfsFromPacific(bfs, heights);
        bfsFromAtlantic(bfs, heights);
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(bfs[i][j]==3)
                {
                    List<Integer>l=new ArrayList<>();
                    l.add(i);l.add(j);
                    res.add(l);
                }
            }
        }
        return res;
    }
    public void bfsFromPacific(int[][] bfs, int[][] heights)
    {
        //System.out.println("bfsFromPacific enter");
        int m = bfs.length, n = bfs[0].length;
        boolean[][] visited=new boolean[m][n];
        int[] dirs=new int[]{1,0,-1,0,1};
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<n;i++)
            q.offer(new int[]{0,i});
        for(int i=1;i<m;i++)
            q.offer(new int[]{i,0});
        while(!q.isEmpty())
        {
            int[] loc=q.poll();
            //System.out.println("polled "+loc[0]+","+loc[1]);
            visited[loc[0]][loc[1]]=true;
            bfs[loc[0]][loc[1]] |= 1;
            for(int i=0;i<4;i++)
            {
                int x = loc[0]+dirs[i], y = loc[1]+dirs[i+1];
                if(isValid(x,y,m,n) && heights[loc[0]][loc[1]]<=heights[x][y] && !visited[x][y])
                    q.offer(new int[]{x,y});
            }
        }
        //System.out.println("bfsFromPacific exit");
    }
    public void bfsFromAtlantic(int[][] bfs, int[][] heights)
    {
        //System.out.println("bfsFromAtlantic enter");
        int m = bfs.length, n = bfs[0].length;
        boolean[][] visited=new boolean[m][n];
        int[] dirs=new int[]{1,0,-1,0,1};
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<n;i++)
            q.offer(new int[]{m-1,i});
        for(int i=0;i<m-1;i++)
            q.offer(new int[]{i,n-1});
        while(!q.isEmpty())
        {
            int[] loc=q.poll();
            //System.out.println("polled "+loc[0]+","+loc[1]);
            visited[loc[0]][loc[1]]=true;
            bfs[loc[0]][loc[1]] |= 2;
            for(int i=0;i<4;i++)
            {
                int x = loc[0]+dirs[i], y = loc[1]+dirs[i+1];
                if(isValid(x,y,m,n) && heights[loc[0]][loc[1]]<=heights[x][y] && !visited[x][y])
                    q.offer(new int[]{x,y});
            }
        }
        //System.out.println("bfsFromAtlantic exit");
    }
}

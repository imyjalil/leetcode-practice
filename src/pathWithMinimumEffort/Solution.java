package pathWithMinimumEffort;

//problem link: https://leetcode.com/problems/path-with-minimum-effort/
//Author: imyjalil
public class Solution {
	//TC: O(M*N*log(M*N)) - minheap holds all the vertices in the worst case
    //and all the vertices might go 4 times into the min heap in the worst case
    //SC: O(M*N) - min heap will hold all vertices in the worst case
    public int minimumEffortPathMinHeap(int[][] heights) {
        int dirs[]=new int[]{1,0,-1,0,1};
        int n=heights.length, m=heights[0].length;
        int[][] dist=new int[n][m];
        for(int i=0;i<n;i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        PriorityQueue<int[]>minHeap=new PriorityQueue<>((a,b)->(a[0]-b[0]));
        minHeap.offer(new int[]{0,0,0});
        while(!minHeap.isEmpty())
        {
            int[] prop=minHeap.poll();
            if(prop[1]==n-1 && prop[2]==m-1) return prop[0];
            for(int i=0;i<4;i++)
            {
                int nr=prop[1]+dirs[i], nc=prop[2]+dirs[i+1];
                if(nr<0 || nc<0 || nr>=n || nc>=m) continue;
                //this line ensures that the path being taken considers the cost as the maximum absolute difference
                int newDist=Math.max(prop[0],Math.abs(heights[prop[1]][prop[2]]-heights[nr][nc]));
                if(newDist<dist[nr][nc])
                {
                    dist[nr][nc]=newDist;
                    minHeap.offer(new int[]{newDist,nr,nc});
                    //min heap ensures that the least of all possible max values is the answer
                }
            }
        }
        return 0;
    }
    
    public int minimumEffortPath(int[][] heights){
        return minimumEffortPathBinarySearch(heights);
    }
    
    //TC: M*N*O(highest element)
    //SC: O(M*N) - visited array + implicit space for recursion calls
    public int minimumEffortPathBinarySearch(int[][] heights){
        int l=0,r=0;
        int ans=0,n=heights.length,m=heights[0].length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                r=Math.max(r,heights[i][j]);
            }
        }
        while(l<=r)
        {
            int mid=l+(r-l)/2;
            boolean visited[][]=new boolean[n][m];visited[0][0]=true;
            if(canReachDestination(heights,mid,0,0,visited))
            {
                r=mid-1;
                ans=mid;
            }
            else
                l=mid+1;
        }
        return ans;
    }
    public boolean canReachDestination(int[][] heights, int limit, int r, int c, boolean visited[][])
    {
        int dirs[]=new int[]{1,0,-1,0,1};
        int n=heights.length, m=heights[0].length;
        if(r==n-1 && c==m-1) return true;
        for(int i=0;i<4;i++)
        {
            int nr=r+dirs[i],nc=c+dirs[i+1];
            if(nr<0 || nr>=n || nc<0 || nc>=m || visited[nr][nc]) continue;
            
            if(Math.abs(heights[nr][nc]-heights[r][c])<= limit){
                visited[nr][nc]=true;
                if(canReachDestination(heights,limit,nr,nc,visited))
                    return true;
            }
        }
        return false;
    }
}

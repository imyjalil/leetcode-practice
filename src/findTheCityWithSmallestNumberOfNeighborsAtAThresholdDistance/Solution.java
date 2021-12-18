package findTheCityWithSmallestNumberOfNeighborsAtAThresholdDistance;

public class Solution {
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //return solveDjikstra(n, edges, distanceThreshold);
        return solveFloydWarshall(n, edges, distanceThreshold);
    }
    //TC:O(E*V*logV)-worst case: E=V*(V+1)/2=>total complexity:O(V*V*V*logV)
    public int solveDjikstra(int n, int[][] edges, int distanceThreshold)
    {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for(int i=0;i<n;i++)
            g.put(i, new HashMap<>());
        for(int []e : edges)
        {
            g.get(e[0]).put(e[1],e[2]);
            g.get(e[1]).put(e[0],e[2]);
        }
        int min=n-1;
        int res=-1;
        for(int i=0;i<n;i++)//Djikstra with every vertex as starting vertex
        {
            Queue<int[]>q=new PriorityQueue<>((a,b)->(b[1]-a[1]));
            q.add(new int[]{i, distanceThreshold});//setting limit, here after node's distance will be substracted from this
            Set<Integer>visited=new HashSet<>();
            int count=0;
            while(!q.isEmpty())
            {
                int[] city=q.poll();
                if(!visited.contains(city[0])){
                    visited.add(city[0]);
                    count++;
                }
                else
                    continue;
                Map<Integer, Integer>m=g.get(city[0]);
                for(int neighbour: m.keySet()){
                    if(!visited.contains(neighbour) && city[1]>=m.get(neighbour)){
                        q.add(new int[]{neighbour, city[1]-m.get(neighbour)});
                    }
                }
            }
            if(count-1<=min){
                min=count-1;
                res=i;
            }
        }
        return res;
    }
    //TC:O(V*V*V)
    public int solveFloydWarshall(int n, int [][]edges, int distanceThreshold)
    {
        int[][] dis=new int[n][n];
        int res=0, smallest=n;
        for(int i=0;i<n;i++)
            Arrays.fill(dis[i],10001);
        for(int[]edge:edges)
            dis[edge[0]][edge[1]]=dis[edge[1]][edge[0]]=edge[2];
        for(int i=0;i<n;i++)
            dis[i][i]=0;
        for(int k=0;k<n;k++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    dis[i][j]=Math.min(dis[i][j],dis[i][k]+dis[k][j]);
                }
            }
        }
        for(int i=0;i<n;i++)
        {
            int count=0;
            for(int j=0;j<n;j++)
            {
                if(dis[i][j]<=distanceThreshold)
                    count++;
            }
            if(count<=smallest){
                res=i;
                smallest=count;;
            }
        }
        return res;
    }
}

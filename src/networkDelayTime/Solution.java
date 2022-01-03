package networkDelayTime;

//problem link: https://leetcode.com/problems/network-delay-time/
//Author: imyjalil
public class Solution {
	public int networkDelayTime(int[][] times, int n, int k){
        //return networkDelayTimePriorityQueue(times, n, k);
        return networkDelayTimeBellmanFord(times, n, k);
    }
	//TC: O(N*E)
    public int networkDelayTimeBellmanFord(int[][] times, int n, int k){
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[k]=0;
        for(int i=1;i<=n-1;i++){
           
                for(int[] m:times){
                    int u=m[0];
                    int v=m[1];
                    int w=m[2];
                    if(dp[u]!=Integer.MAX_VALUE &&dp[u]+w<dp[v])dp[v]=dp[u]+w;
                }
            }
        
        int max=Integer.MIN_VALUE;
       for(int i=1;i< dp.length;i++){
            if(dp[i]==Integer.MAX_VALUE)return -1;
            max=Math.max(max,dp[i]);
        }
        return max;
    }
    //TC:O(N*logE)
    public int networkDelayTimePriorityQueue(int[][] times, int n, int k) {
        Set<Integer>unvisited=new HashSet<>();int ans=0;
        Queue<int[]>q=new PriorityQueue<>((a,b)->(a[1]-b[1]));
        q.add(new int[]{k,0});
        Map<Integer,List<int[]>>map=new HashMap<>();
        for(int i=1;i<=n;i++) {unvisited.add(i);map.put(i, new ArrayList<>());}
        for(int[] time: times)
            map.get(time[0]).add(new int[]{time[1],time[2]});
        while(!q.isEmpty() && !unvisited.isEmpty())
        {
            int[] loc=q.poll();
            if(!unvisited.contains(loc[0])) continue;
            unvisited.remove(loc[0]);
            List<int[]>paths=map.get(loc[0]);
            for(int[] path:paths)
                q.add(new int[]{path[0],path[1]+loc[1]});
            ans = Math.max(ans, loc[1]);
        }
        return unvisited.isEmpty()?ans:-1;
    }
}

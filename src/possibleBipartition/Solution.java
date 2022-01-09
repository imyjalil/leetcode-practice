package possibleBipartition;

//problem link:https://leetcode.com/problems/possible-bipartition/
//Author: imyjalil
public class Solution {
	public boolean possibleBipartition(int n, int[][] dislikes) {
      Map<Integer,List<Integer>>map=new HashMap<>();
      for(int i=1;i<=n;i++)
          map.put(i,new ArrayList<>());
      for(int[] dislike:dislikes)
      {
          map.get(dislike[0]).add(dislike[1]);
          map.get(dislike[1]).add(dislike[0]);
      }
      int color[]=new int[n+1];
      Arrays.fill(color,-1);
      //bfs on all nodes
      for(int i=1;i<=n;i++)
          if(color[i]==-1 && !isBipartite(map,i,color))
              return false;
      return true;
  }
  public boolean isBipartite(Map<Integer,List<Integer>>map, int i,int[] color)
  {
      Queue<Integer>q=new LinkedList<>();
      q.offer(i);
      color[i]=1;
      while(!q.isEmpty())
      {
          int ele=q.poll();
          for(int num:map.get(ele))
          {
              if(color[num]==-1)
              {
                  q.offer(num);
                  color[num]=1-color[ele];
              }
              else if(color[num]==color[ele])
                  return false;
          }
      }
      return true;
  }
}

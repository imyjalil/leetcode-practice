package courseScheduleIV;

//problem link:https://leetcode.com/problems/course-schedule-iv/
//Author: imyjalil
public class Solution {
	//for both the DFS approaches, dfs is on all nodes, hence
    //TC:O(n*n*n*n)-n=numCourses
    public List<Boolean> dfsWitHashSet(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer,Set<Integer>>map=new HashMap<>();
        for(int i=0;i<numCourses;i++)
            map.put(i,new HashSet<>());
        for(int[] prerequisite:prerequisites)
            map.get(prerequisite[0]).add(prerequisite[1]);
        boolean []visited=new boolean[numCourses];
        for(int i=0;i<numCourses;i++)
        {
            if(!visited[i])
                dfs(i,map,visited);
        }
        List<Boolean>res=new ArrayList<>();
        for(int[] query:queries)
        {
            if(map.get(query[0]).contains(query[1]))
                res.add(true);
            else
                res.add(false);
        }
        return res;
    }
    public Set<Integer> dfs(int node, Map<Integer,Set<Integer>>map, boolean[] visited)
    {
        Set<Integer> res = new HashSet<>();
        for(int nod:map.get(node))
        {
            Set<Integer>newSet;
            if(visited[nod])
                newSet=map.get(nod);
            else
                newSet=dfs(nod, map, visited);
            for(int ele:newSet)
                res.add(ele);
        }
        visited[node]=true;
        for(int ele:res)
            map.get(node).add(ele);
        return map.get(node);
    }
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //return dfsWitHashSet(numCourses,prerequisites,queries);
        return dfsWithMatrix(numCourses,prerequisites,queries);
    }
    
    public void fillGraph(boolean[][] graph, int i, boolean[] visited){
        if (visited[i])
            return;
        visited[i] = true;
        for (int j=0;j<graph[i].length;j++){
            if (graph[i][j]){
                fillGraph(graph, j, visited);
                for (int k=0;k<graph[j].length;k++){
                    if (graph[j][k])
                        graph[i][k] = true;
                }
            }
        }
    }
    
    public List<Boolean> dfsWithMatrix(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] graph = new boolean[n][n];
        
        for (int i=0;i<n;i++)
            graph[i][i] = true;

        for (int[] entry: prerequisites)
            graph[entry[0]][entry[1]] = true;
        
        boolean[] visited = new boolean[n];
        for (int i=0;i<n;i++)
            fillGraph(graph, i, visited);
        
        List<Boolean> responses = new ArrayList<Boolean>();
        for (int[] query: queries)
            responses.add(graph[query[0]][query[1]]);
        return responses;
    }
}

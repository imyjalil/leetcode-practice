package isGraphBipartite;

//problem link: https://leetcode.com/problems/greatest-sum-divisible-by-three/
//Author: imyjalil
public class Solution {
	public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];			
				
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean dfs(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }       
        colors[node] = color;       
        for (int next : graph[node]) {
            if (!dfs(graph, colors, -color, next)) {
                return false;
            }
        }
        return true;
    }
}

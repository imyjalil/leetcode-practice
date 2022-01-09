package minimizeHammingDistanceAfterSwapOperations;

//problem link:https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
//Author: imyjalil
public class Solution {
	public int find(int node, int[] parent)
    {
        if(node == parent[node])
            return node;
        return parent[node]=find(parent[node], parent);
    }
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
        //link all no.s with a common node
        //reason: set of numbers connected can be swapped to generate any permutation
        //hence a number can be present at any member of its union
        for(int[]swap:allowedSwaps)
        {
            int a = swap[0];
            int b = swap[1];
            int p1 = find(a, parent);
            int p2 = find(b, parent);
            if(p1 != p2)
                parent[p1]=p2;
        }
        //for every parent, maintain the no. of nos under it
        //parent,<number,cnt>
        Map<Integer, Map<Integer, Integer>>map = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            int p = find(i, parent);
            if(!map.containsKey(p))
                map.put(p, new HashMap<>());
            Map<Integer, Integer> store = map.get(p);
            store.put(source[i],store.getOrDefault(source[i],0)+1);
        }
        int res=0;
        //check if we can generate a permutation of the union such that the mismatching numbers are minimised 
        for(int i=0;i<n;i++)
        {
            int p = find(i, parent);
            Map<Integer, Integer>store=map.get(p);
            if(store.getOrDefault(target[i],0)==0)//target[i] might or might not exist in src's map, hence using getOrDefault
                res++;
            else
                store.put(target[i], store.get(target[i])-1);
        }
        return res;
    }
}

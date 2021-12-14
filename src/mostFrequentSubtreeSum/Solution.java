package mostFrequentSubtreeSum;

//problem link: https://leetcode.com/problems/most-frequent-subtree-sum/
//Author: imyjalil
public class Solution {
	Map<Integer,Integer>map;int maxCount=Integer.MIN_VALUE;
    public int[] findFrequentTreeSum(TreeNode root) {
        map=new HashMap<>();
        solve(root);
        List<Integer>res=new ArrayList<>();
        for(Map.Entry<Integer,Integer>s:map.entrySet())
            if(s.getValue()==maxCount)
                res.add(s.getKey());
        int[] arr = new int[res.size()];
        for(int i=0;i<arr.length;i++) arr[i]=res.get(i);
        return arr;
    }
    public int solve(TreeNode root)
    {
        if(root == null) return 0;
        int left=solve(root.left);
        int right=solve(root.right);
        int total=root.val+left+right;
        map.put(total,map.getOrDefault(total,0)+1);
        maxCount=Math.max(maxCount,map.get(total));
        return total;
    }
}

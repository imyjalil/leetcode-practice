package pathSumIII;

//problem link: https://leetcode.com/problems/path-sum-iii/
//Author: imyjalil
public class Solution {
	//key-sum, value- no. of times sum occured
    Map<Integer,Integer>map;int ans=0;
    public int pathSum(TreeNode root, int targetSum) {
        map=new HashMap<>();
        map.put(0,1);
        solve(root, targetSum, 0);
        return ans;
    }
    public void solve(TreeNode root, int targetSum, int currSum)
    {
        if(root==null)
            return;
        currSum += root.val;
        if(map.containsKey(currSum-targetSum))
            ans += map.get(currSum-targetSum);//if the sum is(x+y) and we had n ways of getting the sum as x, then the no. of ways in which addition of y gives the sum as (x+y) is n
        map.put(currSum, map.getOrDefault(currSum, 0)+1);//add this entry entry so that a child node can use this information
        solve(root.left, targetSum, currSum);
        solve(root.right, targetSum, currSum);
        map.put(currSum, map.get(currSum)-1);// once we exit this node, we don't need its entry's info
        
    }
}

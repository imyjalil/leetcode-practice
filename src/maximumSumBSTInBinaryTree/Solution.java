package maximumSumBSTInBinaryTree;

//problem link:https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
//Author: imyjalil
public class Solution {
	class Result{
        boolean res;//if subtree is BST
        int val;//sum of current sub tree
        int min;//minimum in current sub tree
        int max;//maximum in current subtree
        public Result(boolean res, int val, int min, int max)
        {
            this.res=res;this.val=val;this.min=min;this.max=max;
        }
        public boolean getKey(){
            return res;
        }
        public int getValue(){
            return val;
        }
        public int getMin(){
            return min;
        }
        public int getMax(){
            return max;
        }
    }
    int ans=0;
    //TC:O(n)-n=number of nodes
    public int maxSumBST(TreeNode root) {
        solve(root);
        return ans;
    }
    //for every node, check if left and right subtrees are BST and check if adding current node to both will result in a BST
    public Result solve(TreeNode root)
    {
        if(root==null)
            return new Result(true,0,Integer.MAX_VALUE, Integer.MIN_VALUE);
        int curr=0,min=0,max=0;
        Result left=solve(root.left);
        Result right=solve(root.right);
        if(left.getKey() && right.getKey())
        {
            if(root.left!=null)
            {
                if(root.right!=null)
                {
                    if(left.getMax()<root.val && root.val<right.getMin())
                    {
                        curr=left.getValue()+root.val+right.getValue();
                        min=left.getMin();
                        max=right.getMax();
                    }
                    else
                        return new Result(false,0,0,0);
                }
                else
                {
                    if(left.getMax()<root.val)
                    {
                        curr=left.getValue()+root.val;
                        min=left.getMin();
                        max=root.val;
                    }
                    else
                        return new Result(false,0,0,0);
                }
            }
            else if(root.right!=null)
            {
                if(root.val<right.getMin())
                {
                    curr=right.getValue()+root.val;
                    min=root.val;
                    max=right.getMax();
                }
                else
                    return new Result(false,0,0,0);
            }
            else
            {
                curr=root.val;
                min=root.val;
                max=root.val;
            }
        }
        else
            return new Result(false,0,0,0);
        ans = Math.max(ans, curr);
        return new Result(true,curr,min,max);
    }
}

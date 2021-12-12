package SmallestSubtreewithalltheDeepestNodes;

//problem link: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
//Author: imyjalil
	
class Solution {
    
    class Pair{
        
        TreeNode node;int level;
        
        Pair(TreeNode node, int d)
        {
            this.node=node;this.level=d;
        }
        
        public void incrementLevel()
        {
            this.level++;
        }
    }
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Pair pair=solve(root);
        return pair.node;
    }
    
    //TC: O(no. of nodes in the tree) as we end up visiting every node of the tree
    //SC: O(1)+implicit stack space
    private Pair solve(TreeNode root)
    {
        if(root==null)
            return new Pair(null,0);
        Pair leftPair = solve(root.left);
        Pair rightPair =solve(root.right);
        if(leftPair.level == rightPair.level)//if the heights of both left and right subtrees are same, it means that we have deepest leaves on both the sides
        {//hence the current node is the ancestor
            return new Pair(root, leftPair.level+1);
        }
        if(leftPair.level > rightPair.level)//if the height of left subtree is greater, it means that the deepest leaf is coming from left subtree
        {//note that on a higher node, there can be a deep leaf coming from the other side
            leftPair.incrementLevel();
            return leftPair;
        }//deepest leaf is from the right side
        rightPair.incrementLevel();
        return rightPair;
    }
}
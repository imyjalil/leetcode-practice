package numbersWithSameConsecutiveDifference;

//problem link:https://leetcode.com/problems/numbers-with-same-consecutive-differences/
//Author: imyjalil
public class Solution {
	Set<Integer>res;
    public int[] numsSameConsecDiff(int n, int k) {
        res=new HashSet<>();
        for(int i=1;i<10;i++)
            solve(n-1,k,i);
        int[] ans = new int[res.size()];
        int i=0;
        for(int num:res)
            ans[i++]=num;
        return ans;
    }
    public void solve(int n, int k, int prev)
    {
        if(n==0)
        {
            res.add(prev);
            return;
        }
        if(prev%10+k<10)
            solve(n-1,k,prev*10+prev%10+k);
        if(prev%10-k>=0)
            solve(n-1,k,prev*10+prev%10-k);
    }
}

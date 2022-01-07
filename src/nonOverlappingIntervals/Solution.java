package nonOverlappingIntervals;

//problem link:https://leetcode.com/problems/non-overlapping-intervals/
//Author: imyjalil
public class Solution {
	//TC:O(n*logn)
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->(a[1]-b[1]));
        int cnt=1;
        int end=intervals[0][1];
        for(int i=0;i<intervals.length;i++)
        {
            if(intervals[i][0]>=end)
            {
                end=intervals[i][1];
                cnt++;
            }
        }
        return intervals.length-cnt;//total-overlapping=non-overlapping
    }
}

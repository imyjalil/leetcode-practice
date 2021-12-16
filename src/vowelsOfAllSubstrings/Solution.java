package vowelsOfAllSubstrings;

//problem link: https://leetcode.com/problems/vowels-of-all-substrings/
//Author: imyjalil
public class Solution {
	public boolean isVowel(char c)
    {
        if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u')
            return true;
        return false;
    }
    
    //***(x letters)[vowel]****(y letters)->total substrings in which this letter is present is (x+1)*(y+1)
    public long countVowels(String word) {
        long n=word.length(), ans = 0;
        for(int i=0;i<n;i++)
        {
            if(isVowel(word.charAt(i)))
            {
                ans += ((i+1)*(n-i));
            }
        }
        return ans;
    }
    
    //alternate dp version--->
    //if i is vowel, dp[i]=dp[i-1]+(i+1), (current character will add up with all the substrings the previous one had)
    //else dp[i]=dp[i-1]
}

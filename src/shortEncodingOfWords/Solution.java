package shortEncodingOfWords;

//problem link: https://leetcode.com/problems/short-encoding-of-words/
//Author: imyjalil
public class Solution {
	class TrieNode{
        Map<Character, TrieNode>next=new HashMap<>();
        int depth;
    }
    Map<Character, TrieNode>map;
    List<TrieNode>leaves;
    public int minimumLengthEncoding(String[] words) {
        return minimumLengthEcodingTrie(words);
    }
    public int minimumLengthEncodingIterative(String[] words)
    {
        int ans=0;
        Set<String>set=new HashSet<>(Arrays.asList(words));
        for(String s:words)
        {
            for(int i=1;i<s.length();i++)//remove all strings which are suffixes of s
                set.remove(s.substring(i));
        }//for example: if set contains time and me, me will be removed as it is a suffix
        for(String s:set)
            ans += (s.length()+1);//1 for '#'
        return ans;
    }
    public int minimumLengthEcodingTrie(String[] words)
    {
        int ans=0;
        TrieNode root = new TrieNode();//insert all the words in reverse into the trie
        leaves=new ArrayList<>();//leaves contains last letter trienode
        for(String word:new HashSet<>(Arrays.asList(words)))
        {
            TrieNode cur=root;
            for(int i=word.length()-1;i>=0;i--)
            {
                char ch = word.charAt(i);
                if(!cur.next.containsKey(ch))
                    cur.next.put(ch,new TrieNode());
                cur=cur.next.get(ch);
            }
            cur.depth=word.length()+1;//1 for '#'
            leaves.add(cur);//add the ending node into leaves
        }
        for(TrieNode leaf:leaves)//check if the leaf node's ash map is empty
            if(leaf.next.isEmpty())//if it is empty, it means that this word will go into the reference string
                ans += leaf.depth;//if next is not empty, it means there is another string whose suffix is the current string
        return ans;
    }
}

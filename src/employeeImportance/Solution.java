package employeeImportance;

//problem link: https://leetcode.com/problems/employee-importance/
//Author: imyjalil
public class Solution {
	class Emp{
        public int importance;
        public List<Integer> subordinates;
        
        Emp(int importance, List<Integer> subordinates){
            this.importance=importance;
            this.subordinates=subordinates;
        }
    }
    
    public int getImportance(List<Employee> employees, int id) {
        
    	//create a map to store importance and subordinates as value and id as key
    	//this will act like a map and can be used to iterate on the keys 
        Map<Integer,Emp>empMap=new HashMap<>();
        for(Employee e:employees)
        {
            empMap.put(e.id, new Emp(e.importance, e.subordinates));
        }
        return solveRecursive(empMap, id);
        
    }
    public int solveIterative(Map<Integer,Emp>empMap, int id)
    {
        int ans=0;
        Queue<Integer> ids=new LinkedList<>();
        ids.offer(id);
        while(!ids.isEmpty())
        {
            int rootId=ids.poll();
            Emp e = empMap.get(rootId);
            ans += e.importance;
            for(int child:e.subordinates)
                ids.offer(child);
        }
        return ans;
    }
    public int solveRecursive(Map<Integer,Emp>empMap, int id)
    {
        int ans=0;
        Emp e=empMap.get(id);
        for(int subordinate: e.subordinates)//solve eveery child
        {
            ans += solveRecursive(empMap, subordinate);
        }
        ans += e.importance;//add answer for a node
        return ans;
    }
    
}

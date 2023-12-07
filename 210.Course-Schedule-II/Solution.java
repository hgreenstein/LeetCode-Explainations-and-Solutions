class Solution {
    Map<Integer, List<Integer>> adjacencyList = new HashMap<>(); 
    Set<Integer> result = new LinkedHashSet<>();
    Set<Integer> cycle = new HashSet<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for(int[] prereq: prerequisites){
            adjacencyList.computeIfAbsent(prereq[0], key -> new ArrayList<>()).add(prereq[1]); 
        }
        for(int i = 0; i < numCourses; i++){
            if(!dfs(i)){
                return new int[0];
            }
        }
        int[] toReturn = new int[result.size()];
        int i = 0;
        for(Integer j: result){
            toReturn[i] = j;
            i++; 
        }
        return toReturn; 
    }
    public boolean dfs(int course){
        if(cycle.contains(course)){
            return false;
        }
        if(result.contains(course)){
            return true;
        }
        cycle.add(course);
        if(adjacencyList.containsKey(course)){
            for(Integer i : adjacencyList.get(course)){
                if(!dfs(i)){
                    return false;
                }
            }
        }
        cycle.remove(course);
        result.add(course);
        return true; 
    }
}


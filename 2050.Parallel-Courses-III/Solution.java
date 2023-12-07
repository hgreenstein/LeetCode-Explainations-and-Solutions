class Solution {
    int longestPath = 0;
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] minTime = new int[n];
        Arrays.fill(minTime, -1);
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        for(int[] relation : relations){
            adjacencyMap.computeIfAbsent(relation[0], key -> new ArrayList<>()).add(relation[1]);
        }
        for(int i = 1; i <= n; i++){
            dfs(adjacencyMap, time, minTime, i);
        }
        return longestPath; 
    }
    public int dfs(Map<Integer, List<Integer>> adjacencyMap, int[] time, int[] minTime, int currentCourse){
        if(minTime[currentCourse - 1] != -1){
            return minTime[currentCourse - 1];
        }
        int maxDfs = 0; 
        if(adjacencyMap.containsKey(currentCourse)){
            for(Integer course : adjacencyMap.get(currentCourse)){
                maxDfs = Math.max(maxDfs, dfs(adjacencyMap, time, minTime, course));
            }
        }
        minTime[currentCourse - 1] = maxDfs + time[currentCourse - 1];
        longestPath = Math.max(longestPath, minTime[currentCourse - 1]);
        return minTime[currentCourse - 1];
    }
}

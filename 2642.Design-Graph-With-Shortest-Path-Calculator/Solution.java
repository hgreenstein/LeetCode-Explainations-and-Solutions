import java.util.*; 
class Edge{
    int id;
    int cost;
    public Edge(int id, int cost){
        this.id = id;
        this.cost = cost;
    }
}

class Graph {
    Map<Integer, List<Edge>> graphMap = new HashMap<>();
    public Graph(int n, int[][] edges) {    
        for(int[] edge : edges){
            Edge currentEdge = new Edge(edge[1], edge[2]);
            graphMap.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(currentEdge);
        }
    }
    
    public void addEdge(int[] edge) {
        Edge currentEdge = new Edge(edge[1], edge[2]);
        graphMap.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(currentEdge);
    }
    
    public int shortestPath(int node1, int node2) {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        Set<Integer> visited = new HashSet<>();
        Edge origin = new Edge(node1, 0);
        minHeap.offer(origin);
        while(!minHeap.isEmpty()){
            Edge currentEdge = minHeap.poll();
            if(currentEdge.id == node2){
                return currentEdge.cost; 
            }
            if(!visited.add(currentEdge.id)) continue;
            for(Edge e : graphMap.getOrDefault(currentEdge.id, new ArrayList<>())){
                if(visited.contains(e.id)) continue;
                minHeap.offer(new Edge(e.id, e.cost + currentEdge.cost));
            }
        }
        return -1;
    }
}

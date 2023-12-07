# [2642. Design Graph With Shortest Path Calculator](https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/)
tags:
  - Dijkstra
  - Graph
  - ObjectCreation
  - customComparator
  - Heap
  - HashMap
  - O_n2
  - O_n2space
  - Hard
  - Greedy
---
## Problem Statement: 

There is a **directed weighted** graph that consists of `n` nodes numbered from `0` to `n - 1`. The edges of the graph are initially represented by the given array `edges` where `edges[i] = [fromi, toi, edgeCosti]` meaning that there is an edge from `fromi` to `toi` with the cost `edgeCosti`.

Implement the `Graph` class:

- `Graph(int n, int[][] edges)` initializes the object with `n` nodes and the given edges.
- `addEdge(int[] edge)` adds an edge to the list of edges where `edge = [from, to, edgeCost]`. It is guaranteed that there is no edge between the two nodes before adding this one.
- `int shortestPath(int node1, int node2)` returns the **minimum** cost of a path from `node1` to `node2`. If no path exists, return `-1`. The cost of a path is the sum of the costs of the edges in the path.

**Example 1:**

![](https://assets.leetcode.com/uploads/2023/01/11/graph3drawio-2.png)

**Input**
["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
**Output**
[null, 6, -1, null, 6]

**Explanation**
Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.
g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.
g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.

## Logic & Algorithms:

### Custom Interface & Object Creation

The first two functions of this class are very straightforward uses of a HashMap, in this case we utilize the efficient `computeIfAbsent()` function to generate a list in the map at the key if it doesn't already exist. 

We are very used to storing a list of strings or integers as edges in an adjacency map, and while we could store strings of `nodeID@cost` and then split at the `@` symbol later, it seemed most straightforward and inline with OOP principles to implement a simple custom `Edge` class that has two properties, `id` and `cost`. 

### Dijkstra's Shortest Path Algorithm

For computing the shortest path between two nodes, we can thank Edsger Dijkstra in 1956 for the shortest path algorithm, which is explained step by step in the pseudo code


## Pseudo Code:

### Initialize Graph

1. For every edge in the graph
	1. Create an edge object with cost and id of the destination node
	2. Add the edge to the list of neighbors in the adjacency map with key of the source node
### Add Edge

1. Create an edge object with cost and id of the destination node
2. Add the edge to the list of neighbors in the adjacency map with key of the source node

### Dijkstra's Shortest Path

1. Create a minimum heap with comparator of the cost of the edge
2. Create an empty visited set to store visited nodes, this avoids cycles by never visiting a node twice
3. Offer the origin node to the heap with cost of 0 (implicitly)
4. While the heap isn't empty (and we haven't visited the destination)
	1. Get the top node off the heap, this is the cheapest possible node to visit taking all hops into account
	2. If the current node is our destination
		1. Return the edge cost equal to the total cost to get there
	3. Else, if we have already visited this node, continue/go to next node
	4. Given our current node is unvisited and not the destination, visit the current node
	5. For every neighbor edge connected to this current node
		1. Add that edge to the heap with cost of  `currentNodeCost + neighborEdgeCost` to keep track of total cost to travel to that node
5. If we empty the heap and haven't found the node, the path is not possible 
	1. Return -1
## Time Complexity O($n^2$) - We might have to visit every edge in the graph one, up to n^2 edges for fully conneceted
## Space Complexity O($n^2$) - Since we store edges in the Heap, we store up to n^2 edges
## Java Code:

```java
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
```


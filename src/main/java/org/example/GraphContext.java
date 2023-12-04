package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphContext {
    private final GraphTraversalTemplate traversalStrategy;
    public GraphContext(Algorithm algo){
        if (algo == Algorithm.BFS)
            traversalStrategy = new BFSTraversal();
        else if (algo == Algorithm.RANDOM)
            traversalStrategy = new RANDTraversal();
        else
            traversalStrategy = new DFSTraversal();
    }
    public Path GraphSearch(Graph<String, DefaultEdge> G, String src, String dst)
    {
        return traversalStrategy.GraphSearch(G, src, dst);
    }
}

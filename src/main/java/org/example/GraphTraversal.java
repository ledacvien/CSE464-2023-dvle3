package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class GraphTraversal {
    protected Set<String> vertexes;
    protected Map<String, Boolean> visited = new HashMap<String, Boolean>();
    protected Map<String, String> parent = new HashMap<String, String>();

    public void GraphSearch(Graph<String, DefaultEdge> G, String src, String dst)
    {
        init(G, src, dst);
        String curr = src;
        while (!reachDestination(curr, dst))
        {
            curr = fetchNextVertex(G, curr);
            onNextVertex(curr);
        }
    }

    public abstract void init(Graph<String, DefaultEdge> G, String src, String dst);
    public abstract boolean reachDestination(String curr, String dst);
    public abstract String fetchNextVertex(Graph<String, DefaultEdge> G, String curr);
    public abstract void onNextVertex(String curr);
}

package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class DFSTraversal extends GraphTraversalTemplate{
    @Override
    protected void Traversal(Graph<String, DefaultEdge> G, String src, String dst) {
        visited.put(src, true);
        if (src.equals(dst)) return;

        for (DefaultEdge e : G.edgesOf(src))
        {
            String t = G.getEdgeTarget(e);
            if (!visited.get(t))
            {
                parent.put(t, src);
                Traversal(G, t, dst);
            }
        }
    }
}

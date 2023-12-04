package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTraversal extends GraphTraversalTemplate{
    Queue<String> queue = new LinkedList<>();

    @Override
    protected void Traversal(Graph<String, DefaultEdge> G, String src, String dst) {
        visited.put(src, true);
        queue.add(src);
        while(!queue.isEmpty())
        {
            String node = queue.poll();
            if (node.equals(dst)) return;
            for (DefaultEdge e : G.edgesOf(node))
            {
                String t = G.getEdgeTarget(e);
                if (!visited.get(t))
                {
                    visited.put(t, true);
                    parent.put(t, G.getEdgeSource(e));
                    queue.add(t);
                }
            }
        }
    }
}

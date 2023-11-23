package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Random;

public class RANDTraversal extends GraphTraversalTemplate{
    Random rand = new Random();
    @Override
    protected void Traversal(Graph<String, DefaultEdge> G, String src, String dst) {
        visited.put(src, true);
        if (src.equals(dst)) return;

        int n = G.edgesOf(src).size();
        int[] randed = new int[n];
        int count = 0;
        while(count < n)
        {
            int idx = rand.nextInt(n);
            while(randed[idx] == 0) idx = rand.nextInt(n);
            randed[idx] = 1;
            int i = 0;
            DefaultEdge edge = null;
            for (DefaultEdge e : G.edgesOf(src)){
                edge = e;
                if (i == idx) break;
                i++;
            }
            String t = G.getEdgeTarget(edge);
            if (!visited.get(t))
            {
                parent.put(t, src);
                Traversal(G, t, dst);
            }
            count++;
        }
    }
}

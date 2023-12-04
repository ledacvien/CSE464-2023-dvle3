package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Random;

public class RANDTraversal extends GraphTraversalTemplate{
    Random rand = new Random();
    @Override
    protected void Traversal(Graph<String, DefaultEdge> G, String src, String dst) {
        System.out.println("visiting: " + src);
        visited.put(src, true);
        if (src.equals(dst)) return;

        int n = G.edgesOf(src).size();
        boolean[] randed = new boolean[n];
        int count = 0;
        while(count < n)
        {
            String t = G.getEdgeTarget(randomEdge(G, src, randed, n));
            if (!visited.get(t))
            {
                parent.put(t, src);
                Traversal(G, t, dst);
            }
            count++;
        }
    }
    private DefaultEdge randomEdge(Graph<String, DefaultEdge> G, String src, boolean[] randed, int n)
    {
        int idx = rand.nextInt(n);
        while (randed[idx]) idx = rand.nextInt(n);
        randed[idx] = true;

        int i = 0;
        for(DefaultEdge e : G.edgesOf(src))
        {
            if (i == idx)
                return e;
            i++;
        }
        return null;
    }
}

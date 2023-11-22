package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class GraphTraversal {
    Set<String> vertexes;
    Map<String, Boolean> visited = new HashMap<String, Boolean>();
    Map<String, String> parent = new HashMap<String, String>();

    public void GraphSearch(Graph<String, DefaultEdge> G, String src, String dst)
    {
        vertexes = G.vertexSet();
        init();
        onCurrentVertex();
        fetchNextVertex();
        onNextVertex();
    }
//    public void init()
//    {
//        for (String s : vertexes)
//        {
//            visited.put(s, false);
//            parent.put(s,null);
//        }
//    }
    public abstract void init();
    public abstract void onCurrentVertex();
    public abstract String fetchNextVertex();
    public abstract void onNextVertex();

}

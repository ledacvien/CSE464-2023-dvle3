package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class GraphTraversalTemplate {
    protected Set<String> vertexes;
    protected Map<String, Boolean> visited = new HashMap<String, Boolean>();
    protected Map<String, String> parent = new HashMap<String, String>();
    public Path GraphSearch(Graph<String, DefaultEdge> G, String src, String dst)
    {
        Init(G, src);
        Traversal(G, src, dst);
        return TraceBack(src, dst);
    }
    protected void Init(Graph<String, DefaultEdge> G, String src)
    {
        vertexes = G.vertexSet();
        for (String s : vertexes)
        {
            visited.put(s, false);
            parent.put(s, null);
        }
    }
    protected abstract void Traversal(Graph<String, DefaultEdge> G, String src, String dst);
    protected Path TraceBack(String src, String dst)
    {
        Path path = new Path();
        String v = dst;
        while (parent.get(v) != null && !v.equals(src))
        {
            path.add(v);
            v = parent.get(v);
        }
        if (v.equals(src))
        {
            path.add(v);
            return path;
        }
        return null;
    }
}

package org.example;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import javax.imageio.ImageIO;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.GraphImporter;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

public class MyGraph {
    private Graph<String, DefaultEdge> g;

    // Constructor
    public MyGraph()
    {
        g = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    // Feature 1: Parse a DOT graph file to create a graph
    // Accept DOT graph file and create directed graph object
    public void parseGraph(String filepath) {

        String input = "";
        File myfile = new File(filepath);
        Scanner myreader;

        try {
            myreader = new Scanner(myfile);
            while(myreader.hasNextLine()) {
                input += myreader.nextLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        GraphImporter<String, DefaultEdge> importer = new DOTImporter<>();
        ((DOTImporter<String, DefaultEdge>) importer).setVertexFactory(label->label);
        importer.importGraph(g, new StringReader(input));

        //System.out.println(g);
    }

    // Output the number of nodes, label of nodes, number of edges
    //	and directed edges.
    public String toString() {
        String result = "Number of nodes: ";
        result += g.vertexSet().size() + "\n";
        for (String src : g.vertexSet()) {
            result += src + "\n";
        }
        result += "\nNumber of edges: " + g.edgeSet().size() + "\n";
        for(DefaultEdge edge : g.edgeSet()) {
            result += g.getEdgeSource(edge) + "->" +
                    g.getEdgeTarget(edge) + "\n";
        }

        return result;
    }

    // Output to file
    public void outputGraph(String filepath) throws IOException {
        FileWriter myWriter = new FileWriter(filepath);
        myWriter.write(toString());
        myWriter.close();
    }

    // Feature 2: Adding nodes from the imported graph
    // Add a note and check of duplicate labels
    public boolean addNode(String label)
    {
        return g.addVertex(label);
    }

    // Add a list of nodes
    public boolean addNodes(String[] label)
    {
        boolean flag = true;
        for (String s : label) {
            boolean t = addNode(s);
            if (!t)
                flag = false;
        }
        return flag;
    }

    // Feature 3: Adding edges from the imported graph
    // Add and check for duplicate edge
    public boolean addEdge(String srcLabel, String dstLabel)
    {
        return g.addEdge(srcLabel, dstLabel) != null;
    }

    // Feature 4: Output the imported graph into a DOT file or graphics
    // Output to DOT file
    public void outputDOTGraph(String path) throws IOException
    {
        DOTExporter<String, DefaultEdge> exporter =
                new DOTExporter<>(v->v.toString());
        FileWriter myWriter = new FileWriter(path);
        Writer writer = new StringWriter();

        exporter.exportGraph(g, writer);
        myWriter.write(writer.toString());
        myWriter.close();
    }

    // Output to graphic (format = "PNG")
    public void outputGraphic(String path, String format) throws IOException {
        JGraphXAdapter<String, DefaultEdge> gAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
        mxIGraphLayout layout = new mxCircleLayout(gAdapter);
        layout.execute(gAdapter.getDefaultParent());

        BufferedImage img =
                mxCellRenderer.createBufferedImage(gAdapter, null, 2, Color.WHITE, true, null);

        File imgFile = new File(path);
        imgFile.createNewFile();
        ImageIO.write(img, (format == null ? "PNG" : format), imgFile);
    }

    public boolean removeNode(String label)
    {
        return g.removeVertex(label);
    }

    public boolean removeNodes(String[] label)
    {
        for (String s : label) {
            if (!g.containsVertex(s))
                return false;
        }

        for (String s : label) {
            g.removeVertex(s);
        }
        return true;
    }

    public boolean removeEdge(String src, String dst)
    {
        return g.removeEdge(src, dst) != null;
    }

    private void BFSTraversal(String src, String dst, Map<String, Boolean> visited, Map<String, String> parent)
    {
        Queue<String> queue = new LinkedList<String>();

        visited.put(src, true);
        queue.add(src);

        while(!queue.isEmpty())
        {
            String node = queue.poll();

            if (node.equals(dst)) return;

            for (DefaultEdge e : g.edgesOf(node))
            {
                String t = g.getEdgeTarget(e);
                if (!visited.get(t))
                {
                    visited.put(t, true);
                    parent.put(t, g.getEdgeSource(e));
                    queue.add(t);
                }
            }
        }
    }

    private void DFSTraversal(String src, String dst, Map<String, Boolean> visited, Map<String, String> parent)
    {
        visited.put(src, true);
        if (src.equals(dst)) return;

        for (DefaultEdge e : g.edgesOf(src))
        {
            String t = g.getEdgeTarget(e);
            if (!visited.containsKey(t) || !visited.get(t))
            {
                parent.put(t, src);
                DFSTraversal(t, dst, visited, parent);
            }
        }
    }

    enum Algorithm{
        DFS, BFS
    }
    public Path GraphSearch(String src, String dst, Algorithm algo)
    {
        Set<String> vertexes = g.vertexSet();
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        Map<String, String> parent = new HashMap<String, String>();
        for (String s : vertexes)
        {
            visited.put(s, false);
            parent.put(s, null);
        }

        if (algo == Algorithm.BFS)
            BFSTraversal(src, dst, visited, parent);
        else if (algo == Algorithm.DFS)
            DFSTraversal(src, dst, visited, parent);

        Path path = new Path();
        String v = dst;
        while (parent.get(v) != null && !parent.get(v).equals(src))
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

import org.example.MyGraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyGraphTest {
    private String inputPath = "src/test/java/input.dot";
    private String outputText = "src/test/java/output.txt";
    private String outputGraphic = "src/test/java/graphic.png";
    private String outputDOT = "src/test/java/output.dot";
    private String exptectFile1 = "src/test/java/expected1.txt";

    private MyGraph graph;
    @Before
    public void setUp()
    {
        System.out.println("This is set up");
        graph = new MyGraph();
    }

    @Test
    public void feature1Test() throws IOException {
        graph.parseGraph(inputPath);
        graph.outputGraph(outputText);
        String expected = Files.readString(Paths.get(exptectFile1));
        String output = Files.readString(Paths.get(outputText));
        Assert.assertEquals(output, expected);
    }

    @Test
    public void feature2Test()
    {
        String[] nodelist = {"f", "g", "h"};
        boolean result = graph.addNodes(nodelist);
        Assert.assertEquals(result, true);
    }

    @Test
    public void feature3Test()
    {
        graph.parseGraph(inputPath);
        String srcLabel = "b";
        String dstLabel = "a";
        boolean result = graph.addEdge(srcLabel, dstLabel);
        Assert.assertEquals(result, true);
    }

    @Test
    public void feature4Test() throws IOException {
        graph.parseGraph(inputPath);
        graph.outputGraphic(outputGraphic, "PNG");
        graph.outputDOTGraph(outputDOT);

        File imgFile = new File(outputGraphic);
        boolean result = imgFile.exists();

        File dotFile = new File(outputDOT);
        result = result & dotFile.exists();

        Assert.assertEquals(result, true);
    }

    // Test case: Node in Graph
    @Test
    public void removeNodeTest1()
    {
        graph.parseGraph(inputPath);
        String rmNode = "c";
        boolean result = graph.removeNode(rmNode);
        Assert.assertEquals(result, true);

        System.out.println("Remove Node Test Output: ");
        System.out.println(graph.toString());
    }


    // Test case: Node not in Graph
    @Test
    public void removeNodeTest2()
    {
        graph.parseGraph(inputPath);
        String rmNode = "h";
        boolean result = graph.removeNode(rmNode);
        Assert.assertEquals(result, false);

        System.out.println("Remove Node Test Output: ");
        System.out.println(graph.toString());
    }

    // Test case: remove Node List. All nodes exits
    @Test
    public void removeNodesListTest1()
    {
        graph.parseGraph(inputPath);
        String[] rmNodes = {"a", "b", "c"};
        boolean result = graph.removeNodes(rmNodes);
        Assert.assertEquals(result, true);

        System.out.println("Remove Node List Test Output: ");
        System.out.println(graph.toString());
    }

    // Test case: remove Node List. Some nodes not exits
    @Test
    public void removeNodesListTest2()
    {
        graph.parseGraph(inputPath);
        String[] rmNodes = {"f", "b", "c"};
        boolean result = graph.removeNodes(rmNodes);
        Assert.assertEquals(result, false);
    }

    // Test case: remove Edge, edge exits
    @Test
    public void removeEdgeTest1()
    {
        graph.parseGraph(inputPath);
        String src = "a";
        String dst = "b";
        boolean result = graph.removeEdge(src, dst);
        Assert.assertEquals(result, true);

        System.out.println("Remove Edge Test Output: ");
        System.out.println(graph.toString());
    }

    // Test case: remove Edge, edge not exits
    @Test
    public void removeEdgeTest2()
    {
        graph.parseGraph(inputPath);
        String src = "r";
        String dst = "b";
        boolean result = graph.removeEdge(src, dst);
        Assert.assertEquals(result, false);

        System.out.println("Remove Edge Test Output: ");
        System.out.println(graph.toString());
    }

    @After
    public void tearDown()
    {
        System.out.println("This is tear down");
    }
}

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
    private String inputPath = "src\\test\\java\\input.dot";
    private String outputText = "src\\test\\java\\output.txt";
    private String outputGraphic = "src\\test\\java\\graphic.png";
    private String outputDOT = "src\\test\\java\\output.dot";
    private String exptectFile1 = "src\\test\\java\\expected1.txt";

    private MyGraph graph;
    @Before
    public void setUp()
    {
        graph = new MyGraph();
    }

    @Test
    public void feature1Test() throws IOException {
        graph.parseGraph(inputPath);
        graph.outputGraph(outputText);
        String expected = Files.readString(Paths.get(exptectFile1));
        String output = Files.readString(Paths.get(outputText));
        Assert.assertEquals(expected, output);
    }

    @Test
    public void feature2Test()
    {
        String[] nodelist = {"e", "f", "g", "h"};
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

    @After
    public void tearDown()
    {
        System.out.println("This is tear down");
    }
}

package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph();
        String filepath = "./src/main/java/org/example/input.dot";
        graph.parseGraph(filepath);


        String src = "a";
        String dst = "c";

        Path path = graph.GraphSearch(src, dst, Algorithm.BFS);
        System.out.println("Path using BFS algo: ");
        System.out.println(path.toString());

        path = graph.GraphSearch(src, dst, Algorithm.DFS);
        System.out.println("Path using DFS algo: ");
        System.out.println(path.toString());
    }
}
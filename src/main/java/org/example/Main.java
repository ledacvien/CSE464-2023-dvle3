package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph();
        String filepath = "C:\\Users\\levie\\OneDrive\\Documents\\aFall2023\\CSE464\\Project1\\input.dot";

        graph.parseGraph(filepath);
        System.out.println(graph.toString());
    }
}
package org.example;
import java.util.Stack;

public class Path
{
    private final Stack<String> s = new Stack<String>();

    public void add(String vertex)
    {
        s.push(vertex);
    }

    public String pop()
    {
        return s.pop();
    }

    public String toString()
    {
        StringBuilder result = new StringBuilder();
        while (s.size() > 1)
        {
            result.append(pop()).append("->");
        }
        // giving some change I guess??
        result.append(pop());
        return result.toString();
    }
}

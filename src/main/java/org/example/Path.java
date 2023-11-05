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
        String result = "";
        while (s.size() > 1)
        {
            result += pop() + "->";
        }
        // giving some change I guess??
        result += pop();
        return result;
    }
}

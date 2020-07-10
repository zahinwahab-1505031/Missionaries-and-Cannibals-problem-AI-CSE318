/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missionariesandcannibalsproblem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Zahin
 */
public class Graph {

    Node startNode;
    Node goalNode;
    int k;
    int BFS_expandedSize;
    int BFS_exploredSize;
    int DFS_expandedSize;
    int DFS_exploredSize;

    public Graph(Node startNode, Node goalNode, int k) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.k = k;
    }

    public Node BFS(int t) {

        if (this.startNode.equals(goalNode)) {
            return startNode;
        }

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> expanded = new ArrayList<>();
        queue.add(this.startNode);
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (!queue.isEmpty()) {

            Node current = queue.remove();
            expanded.add(current);
            if (current.equals(this.goalNode)) {
                BFS_expandedSize = expanded.size();
                BFS_exploredSize = queue.size() + expanded.size();
                return current;
            } else {
                List<Node> children = current.getChildren(k);
                if (!children.isEmpty()) {

                    for (Node n : children) {

                        if (!expanded.contains(n) && !queue.contains(n)) {
                            if (n.equals(goalNode)) {
                                BFS_expandedSize = expanded.size();
                                BFS_exploredSize = queue.size() + expanded.size();
                                return n;
                            }
                            queue.add(n);

                        }
                    }

                }
            }
            elapsedTime = (new Date()).getTime() - startTime;
            if (elapsedTime >= t * 1000) {
                BFS_expandedSize = expanded.size();
                BFS_exploredSize = queue.size() + expanded.size();
                break;
            }
        }
        //   System.out.println("queue empty");
        BFS_expandedSize = expanded.size();
        BFS_exploredSize = queue.size() + expanded.size();
        return null;
    }

    public Node DFS(int t) {
        if (this.startNode.equals(goalNode)) {
             return startNode;
        }
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> expanded = new ArrayList<>();

        stack.push(startNode);
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.equals(goalNode)) {
                DFS_expandedSize = expanded.size();
        DFS_exploredSize = stack.size() + expanded.size();
                return current;
            } else {
                expanded.add(current);
                List<Node> children = current.getChildren(k);
                if (!children.isEmpty()) {
                    for (Node i : children) {
                        if (!expanded.contains(i) && !stack.contains(i)) {
                            if (i.equals(goalNode)) {
                                
                DFS_expandedSize = expanded.size();
        DFS_exploredSize = stack.size() + expanded.size();
                                return i;
                            }
                            stack.push(i);
                        }
                    }
                }

            }
            elapsedTime = (new Date()).getTime() - startTime;
            if (elapsedTime >= t * 1000) {
                
                DFS_expandedSize = expanded.size();
        DFS_exploredSize = stack.size() + expanded.size();
                break;
            }
        }
        
                DFS_expandedSize = expanded.size();
        DFS_exploredSize = stack.size() + expanded.size();
        return null;
    }
}

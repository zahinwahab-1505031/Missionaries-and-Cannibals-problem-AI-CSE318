/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missionariesandcannibalsproblem;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Zahin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter no of missionaries:");
        Scanner in  = new Scanner (System.in);
        int m = Integer.parseInt(in.nextLine()); 
        System.out.println("Enter no of cannibals:");
        
         int c = Integer.parseInt(in.nextLine());
          System.out.println("Enter no of passengers in boat:");
          int k = Integer.parseInt(in.nextLine());
            System.out.println("Enter cut off time:");
          int t = Integer.parseInt(in.nextLine());
          if(m>=c){
        Node startNode = new Node(Position.left, new Cannibals(c, 0), new Missionaries(m, 0), null);
        Node goalNode = new Node(Position.right, new Cannibals(0, c), new Missionaries(0, m), null);

        Graph graph = new Graph(startNode, goalNode,k);

        long startTime = System.nanoTime();
        Node n = graph.BFS(t);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        
        System.out.println("---------------------------------------bfs done-----------------------------------");
        System.out.println("Time requited for BFS: " + totalTime/1000+" us");
        System.out.println("#expanded nodes for BFS: " + graph.BFS_expandedSize);
        System.out.println("#explored nodes for BFS: " + graph.BFS_exploredSize);
        
        System.out.println("---------------------------------Required solution is : -------------------------");
        Stack<String> res = new Stack<>();
        int stepCount = 0;
        while (n != null) {
           // System.out.println(n);
            res.push(n.toString());
              if(n.move!=null) {
                  res.push(n.move);
                  stepCount++;
                 // System.out.println(n.move);
              }
       
            n = n.parentNode;
            
            
        }
        while(!res.isEmpty()){
            System.out.println(res.pop());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
              System.out.println("Steps for BFS: "+stepCount);
        startTime = System.nanoTime();
        n = graph.DFS(t);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("---------------------------------------dfs done-------------------------");
        System.out.println("Time requited for DFS: " + totalTime/1000+" us");
        System.out.println("#expanded nodes for DFS: " + graph.DFS_expandedSize);
        System.out.println("#explored nodes for DFS: " + graph.DFS_exploredSize);
        
        System.out.println(n);
        System.out.println("----------------------Required solution is--------------------");
        res = new Stack<>();
        stepCount = 0;
        while (n != null) {
           // System.out.println(n);
            res.push(n.toString());
              if(n.move!=null) {
                  res.push(n.move);stepCount++;
                 // System.out.println(n.move);
              }
       
            n = n.parentNode;
            
            
        }
        while(!res.isEmpty()){
            
            System.out.println(res.pop());
        }
              System.out.println("------------------------------------------------------------------------------");
             System.out.println("Steps for DFS: "+stepCount);
    }
    
    else {
            System.out.println("#Missionaries must be equal or greater than that of cannibals");
}
    }
}

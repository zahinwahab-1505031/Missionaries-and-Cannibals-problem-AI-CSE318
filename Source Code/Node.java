/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missionariesandcannibalsproblem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zahin
 */
public class Node {

    public Position boatPosition;
    public Node parentNode;
    public Cannibals cannibal;
    public Missionaries missionary;
    public String move;
    public List childrenNode = new ArrayList<Node>();

    public Node(Position boatPosition, Cannibals cannibal, Missionaries missionary,Node parentNode) {
        this.boatPosition = boatPosition;
        this.cannibal = cannibal;
        this.missionary = missionary;
        this.parentNode = parentNode;
    }

    public boolean isSafe() {
        if (missionary.left >= 0 && missionary.right >= 0 && cannibal.left >= 0 && cannibal.right >= 0
                && (missionary.left == 0 || missionary.left >= cannibal.left)
                && (missionary.right == 0 || missionary.right >= cannibal.right)) {
            return true;
        }
        return false;
    }
    public void GenerateChildren(int maximum) {
        
        for(int i=1;i<=maximum;i++){
            for(int j=0;j<=(i);j++){
                if(j==0 || j>=(i-j)) {
                        
                    
                if (boatPosition == Position.left) {
                    
                Node node = new Node(Position.right, new Cannibals(cannibal.left - (i-j), cannibal.right + (i-j)), new Missionaries(missionary.left-j, missionary.right+j),this);
                 node.setMove("<<Moving "+ (i-j)+ " cannibals and "+ j+" missionaries from left to right>>");
                 if (node.isSafe()) {
                        childrenNode.add(node);
                    }
                 }
                
            else if (boatPosition == Position.right) {
            
                Node node = new Node(Position.left, new Cannibals(cannibal.left + (i-j), cannibal.right - (i-j)), new Missionaries(missionary.left+j, missionary.right-j),this);
                 node.setMove("<<Moving "+ (i-j)+ " cannibals and "+ j+" missionaries from right to left>>");
                 if (node.isSafe()) {
                        childrenNode.add(node);
                    }
                 }
                }
            }
        }
       
    }
    /*public void GenerateChildren() {
        if (boatPosition == Position.left) {
            //two cannibals from left to right
            Node node = new Node(Position.right, new Cannibals(cannibal.left - 2, cannibal.right + 2), new Missionaries(missionary.left, missionary.right),this);
            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //two missionaries from left to right
            node = new Node(Position.right, new Cannibals(cannibal.left, cannibal.right), new Missionaries(missionary.left - 2, missionary.right + 2),this);
            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //one missionary and one cannibal from left to right
            node = new Node(Position.right, new Cannibals(cannibal.left - 1, cannibal.right + 1), new Missionaries(missionary.left - 1, missionary.right + 1),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //one missionary from left to right
            node = new Node(Position.right, new Cannibals(cannibal.left, cannibal.right), new Missionaries(missionary.left - 1, missionary.right + 1),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //one cannibal from left to right
            node = new Node(Position.right, new Cannibals(cannibal.left - 1, cannibal.right + 1), new Missionaries(missionary.left, missionary.right),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
        } else if (boatPosition == Position.right) {

            //two cannibals from right to left
            Node node = new Node(Position.left, new Cannibals(cannibal.left + 2, cannibal.right - 2), new Missionaries(missionary.left, missionary.right),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //two missionaries from right to left
            node = new Node(Position.left, new Cannibals(cannibal.left, cannibal.right), new Missionaries(missionary.left + 2, missionary.right - 2),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //one missionary and one cannibal from right to left
            node = new Node(Position.left, new Cannibals(cannibal.left + 1, cannibal.right - 1), new Missionaries(missionary.left + 1, missionary.right - 1),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //one missionary from right to left
            node = new Node(Position.left, new Cannibals(cannibal.left, cannibal.right), new Missionaries(missionary.left + 1, missionary.right - 1),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
            //one cannibal from right to left
            node = new Node(Position.left, new Cannibals(cannibal.left + 1, cannibal.right - 1), new Missionaries(missionary.left, missionary.right),this);

            if (node.isSafe()) {
                childrenNode.add(node);
            }
        }
    }public List getChildren(){
        GenerateChildren();
        return childrenNode;
    }*/
    public List getChildren(int k){
        GenerateChildren(k);
        return childrenNode;
    }
    
    @Override
	public boolean equals(Object obj) {
         //   System.out.println("in overriden equals method..............");
		if (!(obj instanceof Node)) {
			return false;
		}
		Node s = (Node) obj;
        if ((s.cannibal.left == cannibal.left) && (s.missionary.left == missionary.left)
        		&& (s.boatPosition == boatPosition) && (s.cannibal.right == cannibal.right)
        		&& (s.missionary.right == missionary.right)) return true;
        else return false;
	}
    public String toString(){
        return "Boat on: " + boatPosition + " | Left: Cannibals: " + cannibal.left +"  Missionaries: "+missionary.left+ " | Right:  Cannibals: "+cannibal.right+
                " Missionaries: "+missionary.right;
    }

    public void setMove(String move) {
        this.move = move;
          }
}

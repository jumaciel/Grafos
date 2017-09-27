/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luiz Fernando
 */
public class Graph {
    private String id;
    private String edgedefault;
    public List<Node> nodes = new ArrayList<Node>();
    public List<Edge> edges = new ArrayList<Edge>();
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
    public String getEdgedefault() {
        return edgedefault;
    }

    public void setEdgedefault(String edgedefault) {
        this.edgedefault = edgedefault;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    public void addNode(Node node){
        this.nodes.add(node);
    }
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}

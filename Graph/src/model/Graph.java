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
 * @author Anah
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
    public String AddEdges(String n1,String n2){
        Edge e = new Edge();
        for(int i=0; i< getNodes().size();i++){
            if(getNodes().get(i).getId().equals(n1))
            {
                e.setNode1(getNodes().get(i));
            }
        }
        for(int i=0; i< getNodes().size();i++){
            if(getNodes().get(i).getId().equals(n2))
            {
                e.setNode2(getNodes().get(i));
                addEdge(e);
                return "Aresta ("+n1+","+n2+") adicionado com sucesso!";
            }
        }
        return "Erro ao tentar adicionar!";
    }
    public String getGrau(Graph graph) {
       String listaGrau = "";
        for (Edge e : graph.getEdges()) {
            e.getNode1().setGrau(0);
            e.getNode2().setGrau(0);
        }
        for (Edge e : graph.getEdges()) {
            e.getNode1().setGrau(e.getNode1().getGrau() + 1);
            e.getNode2().setGrau(e.getNode2().getGrau() + 1);
        }
            for (Node no : graph.getNodes()) {

                listaGrau += "\n Grau Vertice " + no.getId() + " é " + no.getGrau();
            }         
        return listaGrau;
    }
    
}

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
        System.out.println(edges.isEmpty() + " " + edges.size());
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

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);

    }

    public void AddNodes(String node) {
        Node n = new Node();
        n.setId(node);
        addNode(n);

    }

    public void AddEdges(String n1, String n2) {

        Edge e = new Edge();

        for (int i = 0; i < getNodes().size(); i++) {
            if (getNodes().get(i).getId().equals(n1)) {

                e.setNode1(getNodes().get(i));

            }
        }

        for (int i = 0; i < getNodes().size(); i++) {
            if (getNodes().get(i).getId().equals(n2)) {

                e.setNode2(getNodes().get(i));

            }
        }

        addEdge(e);

    }
     public String getGrau(Graph graph) {
       String listaGrau = "";
      
        for (Edge e : graph.getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
            e.getNode1().setGrau(e.getNode1().getGrau() + 1);
            e.getNode2().setGrau(e.getNode2().getGrau() + 1);
        }
        for (Node no : graph.getNodes()) {
            listaGrau += "\n Grau Vertice " + no.getId() + " é " + no.getGrau();
        }         
        return listaGrau;
    }
    public String matrizAdjacencia(Graph graph) {
        String espaco = "    ";
        int size = graph.getNodes().size(), i, j;
        int matriz[][] = new int[size][size];
        String matrizTotal = "  ";
        for (Edge e : graph.getEdges()) {
            int no1 = graph.getNodes().indexOf(e.getNode1());
            int no2 = graph.getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
            matriz[no2][no1] = 1;
        }
        for (int a = 0; a < size; a++) {
            matrizTotal += espaco + graph.getNodes().get(a).getId();
        }
        for (i = 0; i < size; i++) {
            matrizTotal += "\n" + graph.getNodes().get(i).getId();
            for (j = 0; j < size; j++) {
                matrizTotal += espaco + matriz[i][j];
            }
        }
        return (matrizTotal);
    }

}

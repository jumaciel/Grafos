/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anah
 */
public class Edge {

    private Node node1;
    private Node node2;
    private String id;
    private String source;
    private String target;
    private float weight;

    public Edge() {
    }

    public Node getNode1() {
        return node1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
        this.source = node1.getId();
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
        this.target = node2.getId();
    }

    public String getSoucer() {
        return source;
    }

    public void setSoucer(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public float getPeso() {
        return weight;
    }

    public void setPeso(float peso) {
        this.weight = peso;
    }

}

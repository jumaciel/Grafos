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

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);

    }

    public String addNodes(String node) {
        Node n = new Node();
        
        for (int i = 0; i < getNodes().size(); i++) {  // O laço verifica se o vertice já existe se não exite retorna erro
            if (getNodes().get(i).getId().equals(node)) {
                return "erro";
            }
        }
        n.setId(node);
        addNode(n);
        return "Vértice " + n + " adicionado";
     }
    public String removerNode(String node){
    String r="";
    for(int i = 0; i < getNodes().size();i++){
        if(getNodes().get(i).id.equals(node)){
            getNodes().remove(i);
            for(int a = 0; a < getEdges().size();a++){
                if(getEdges().get(a).getNode1().getId().equals(node)){
                    r+="Aresta ("
                    +getEdges().get(a).getNode1().getId()+","+getEdges().get(a).getNode2().getId()
                    +") removida com sucesso!\n";
                    getEdges().remove(a); 
                    a--;
                }
            }
            for(int a2 = 0; a2 < getEdges().size();a2++){
                if(getEdges().get(a2).getNode2().getId().equals(node)){
                    r+="Aresta ("
                    +getEdges().get(a2).getNode1().getId()+","+getEdges().get(a2).getNode2().getId()
                    +") removida com sucesso!\n";
                    getEdges().remove(a2);
                    a2--;
                }
            }
            return "Vertice "+ node+" removido com sucesso!\n\n"+r;
            }
        }
        return "Erro! não foi possivel remover  ";
    }
    public String EditarNode(Graph graph,String node, String novo){
        String r="";
        String b= node;
        
        for(int i = 0; i < graph.getNodes().size();i++){
            if(graph.getNodes().get(i).getId().equals(node)){
                graph.getNodes().get(i).setId(novo);
                r+= "Vertice alterado com sucesso.\n\n"
                + "Vertice "+node+" ============== > "+novo+"\n\n"
                + "Edges atualizadas com sucesso.\n\n";
            }
        }
        for(int a = 0; a < graph.getEdges().size();a++){
            if(getEdges().get(a).getSoucer().equals(b)){
                getEdges().get(a).setSoucer(novo);
//                a--;
            }
        }
        for(int a2 = 0; a2 < graph.getEdges().size();a2++){
            if(getEdges().get(a2).getTarget().equals(b)){
                r+="Edge ("+getEdges().get(a2).getSoucer()+","+getEdges().get(a2).getTarget()+") ============== > ("
                +getEdges().get(a2).getNode1().getId()+","+getEdges().get(a2).getNode2().getId()+")\n";
                getEdges().get(a2).setTarget(novo);
//                a2--;
            }      
        } 
        return r;
    }
    public String addEdges(String n1, String n2,float peso, String nome) {
        Edge e = new Edge();
        
        for (int i = 0; i < getNodes().size(); i++) {
            if (getNodes().get(i).getId().equals(n1)) { //Verifica se o id do vertice na posição i é igual o valor de n1
                e.setNode1(getNodes().get(i)); //Seta o vertice na posição de i
            }
        }

        for (int i = 0; i < getNodes().size(); i++) {
            if (getNodes().get(i).getId().equals(n2)) { //Verifica se o id do vertice na posição i é igual o valor de n2
                e.setNode2(getNodes().get(i)); //Seta o vertice na posição de i
            }
        }
        e.setPeso(peso);
        e.setId(nome); //Seta o nome da aresta
        addEdge(e); //adiciona o objeto na lista de arestas

        return ImprimeEdge();
    }
    public String removerEdge(String node1,String node2){
        for(int i = 0; i < edges.size();i++){
            if(edges.get(i).getNode1().getId().equals(node1)&& edges.get(i).getNode2().getId().equals(node2)){
                edges.remove(i);
                return "Aresta ("+node1+","+node2+") removido com sucesso";
            }
        }
        return "Não foi possivel remover";
    }
    public String getGrau(Graph graph) {
        String listaGrau = "";
        if(graph.getEdgedefault().equals("directed")){
            for (Edge e : graph.getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
                e.getNode1().setGrau(e.getNode1().getGrau() * 0);
                e.getNode2().setGrau(e.getNode2().getGrau() * 0);
            }
            for (Edge e : getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
                e.getNode1().setGrau(e.getNode1().getGrau() + 1);
//                e.getNode2().setGrau(e.getNode2().getGrau() + 1);
            }
            for (Node no : getNodes()) {
                listaGrau += "\n Grau Vertice " + no.getId() + " é " + no.getGrau();
            } 
        }else{
            for (Edge e : graph.getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
                e.getNode1().setGrau(e.getNode1().getGrau() * 0);
                e.getNode2().setGrau(e.getNode2().getGrau() * 0);
            }
            for (Edge e : getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
                e.getNode1().setGrau(e.getNode1().getGrau() + 1);
                e.getNode2().setGrau(e.getNode2().getGrau() + 1);
            }
            for (Node no : getNodes()) {
                listaGrau += "\n Grau Vertice " + no.getId() + " é " + no.getGrau();
            }
        }
        return listaGrau;
    }
    public String matrizAdjacencia(Graph graph) {
        String espaco = "    ";
        String matrizTotal = "  ";
        int i, j;
        int matriz[][] = new int[graph.getNodes().size()][graph.getNodes().size()];
        
        for (Edge e : graph.getEdges()) {
            int no1 = graph.getNodes().indexOf(e.getNode1());
            int no2 = graph.getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
            matriz[no2][no1] = 1;
        }
        for (int a = 0; a < graph.getNodes().size(); a++) {
            matrizTotal += espaco + graph.getNodes().get(a).getId();
        }
        for (i = 0; i < graph.getNodes().size(); i++) {
            matrizTotal += "\n" + graph.getNodes().get(i).getId();
            for (j = 0; j < graph.getNodes().size(); j++) {
                matrizTotal += espaco + matriz[i][j];
            }
        }
        return (matrizTotal);
    }
    public String matrizIncidencia(Graph graph) {
        String incidencia = "  ";
        String espaco = "       ";
        int index = 0, i, j;
        int matriz[][] = new int[graph.getNodes().size()][graph.getEdges().size()];
        
        for (Edge e : graph.getEdges()) {
            int no1 = graph.getNodes().indexOf(e.getNode1());
            int no2 = graph.getNodes().indexOf(e.getNode2());
            
            matriz[no1][index] = 1;
            matriz[no2][index] = 1;
            index++;
        }
        for (int a = 1; a < graph.getEdges().size()+1; a++) {
            incidencia += espaco + graph.getEdges().get(a-1).getId();
        }
        for (i = 0; i < graph.getNodes().size(); i++) {
            incidencia += "\n" + getNodes().get(i).getId();
            for (j = 0; j < graph.getEdges().size(); j++) {
                incidencia += espaco + matriz[i][j];
            }
        }
        return incidencia;
    }
    public String listaAdjacencia(Graph graph) {
        String lista = "";
        int i, j;
        int matriz[][] = new int[graph.getNodes().size()][graph.getNodes().size()];
        
        for (Edge e : graph.getEdges()) {
            int no1 = graph.getNodes().indexOf(e.getNode1());
            int no2 = graph.getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
            matriz[no2][no1] = 1;
        }
        for (i = 0; i < graph.getNodes().size(); i++) {
            lista += "\n[ " + graph.getNodes().get(i).getId()+" ]";
            for (j = 0; j < graph.getNodes().size(); j++) {
                if (matriz[i][j] == 1) {
                    lista += " =>[ " + graph.getNodes().get(j).getId()+" ]";
                }
            }
        }
      return lista;  
    }
    public String getIncidentes(){
        String incidentes = "";
            for (Edge e : getEdges()) {
                incidentes += " Os Vertices " + e.getNode1().getId() + " e " + e.getNode2().getId() + " são incidente com a aresta ("+ 
                         e.getNode1().getId() + "," + e.getNode2().getId()+")\n";
            }
            return incidentes;
    }
    public String getAdjacentes(){
        String adjacentes = "";
            for (Edge e : edges) {
                adjacentes += " Os Vertices " + e.getNode1().getId() + " e " + e.getNode2().getId() + " são adjacentes \n";
            }
         return adjacentes;
    }
    public String conjunto(Graph graph) {
        String conjunto, no = "", e = "";
        for (Node n : graph.getNodes()) {
            no += n.getId() + " => ";
        }
        for (int i =0; i < getEdges().size();i++) {
            e += getEdges().get(i).getNode1().getId()+","+getEdges().get(i).getNode2().getId() + " => ";
        }
        conjunto = "Vértices \n" + no + "\n Arestas \n" + e;
        return conjunto;
    }
    public boolean completo() {
	for (int i = 0; i < getNodes().size(); i++) {
		if (getNodes().get(i).getGrau() != (getNodes().size() - 1))
			return false;
	}
	return true;
    }
    public boolean regular(){
        for (int i = 0; i < getNodes().size();i++) {
            if(getNodes().get(0).getGrau() == getNodes().get(i).getGrau()){
                if(i == getNodes().size() -1){
                    return true;
                }
            }  
        }
        return false;
    }
    public boolean multigrafo(){
        for (int i = 0; i < getEdges().size();i++) {
            for (int i2 = 0; i2 < getEdges().size();i2++) {
                if(getEdges().get(i).getNode1().getId() == getEdges().get(i2).getNode2().getId() ){
                    for (int i3 = 0; i3 < getEdges().size();i3++) {
                        if(getEdges().get(i2).getNode1().getId() == getEdges().get(i).getNode2().getId() ){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public String grauRecepcao(Graph graph){
        String lista = "Grau Recepção\n********************\n";
        int i, j,grauR = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < graph.getEdges().size(); i++) {
                auxQr.add(graph.getEdges().get(i).getNode2());
            }
        for (i = 0; i < graph.getNodes().size(); i++) {
            for(int i2 = 0; i2 < auxQr.size();i2++){
                if(auxQr.get(i2).getId().equals(graph.getNodes().get(i).getId())){
                    grauR++;
                }
            }
            lista +=getNodes().get(i).getId()+" é: "+grauR+"\n";
            grauR =0;
        }
      return lista;  
    }
    public String grauEmissao(Graph graph){
        String lista = "Grau de Emissão\n********************\n";
        int i, j,grauE = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < graph.getEdges().size(); i++) {
                auxQr.add(graph.getEdges().get(i).getNode1());
            }
        for (i = 0; i < graph.getNodes().size(); i++) {
            for(int i2 = 0; i2 < auxQr.size();i2++){
                if(auxQr.get(i2).getId().equals(graph.getNodes().get(i).getId())){
                    grauE++;
                }
            }
            lista +=getNodes().get(i).getId()+" é: "+grauE+"\n";
            grauE =0;
        }

      return lista;  
    }
    public String fonte(Graph graph){
          String lista = "Fontes\n********\n";
        int i, j,grauR = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < graph.getEdges().size(); i++) {
                auxQr.add(graph.getEdges().get(i).getNode2());
            }
        for (i = 0; i < graph.getNodes().size(); i++) {
            for(int i2 = 0; i2 < auxQr.size();i2++){
                if(auxQr.get(i2).getId().equals(graph.getNodes().get(i).getId())){
                    grauR++;
                }
            }
            if(grauR == 0){
                lista +=getNodes().get(i).getId()+"\n";
            }
            grauR =0;
        }
      return lista;  
    }
    public String sumidouro(Graph graph){
          String lista = "Sumidouro\n********\n";
        int i, j,grauR = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < graph.getEdges().size(); i++) {
                auxQr.add(graph.getEdges().get(i).getNode1());
            }
        for (i = 0; i < graph.getNodes().size(); i++) {
            for(int i2 = 0; i2 < auxQr.size();i2++){
                if(auxQr.get(i2).getId().equals(graph.getNodes().get(i).getId())){
                    grauR++;
                }
            }
            if(grauR == 0){
                lista +=getNodes().get(i).getId()+"\n";
            }
            grauR =0;
        }
      return lista;  
    
    }
    public String ImprimeNode() {
        String r = " Vertice: {";

        for (int i = 0; i < getNodes().size(); i++) {
            r += getNodes().get(i).getId();

            if (i < getNodes().size() - 1) {
                r += ",";
            }
        }
        r += "}";
        return r;
    }
    
    public String ImprimeEdge() {
        String r = " Aresta: {";
        
        for (int i = 0; i < getEdges().size(); i++) {
                r += "(" + getEdges().get(i).getNode1().getId() + "," + getEdges().get(i).getNode2().getId() + ")";
                if (i < getEdges().size() - 1) {
                    r += ",";
                }
            }       
        r += "}";
       return r;
    }
    public String imprimeListas() {
        String r = "";
        
        if (!getNodes().isEmpty()) {
            r += " Listas:\n*********\n";
            r += " Vertice: {";

            for (int i = 0; i < getNodes().size(); i++) {
                r += getNodes().get(i).getId();

                if (i < getNodes().size() - 1) {
                    r += ",";
                }
            }
            r += "}";
        } else {
            r += "\t==========ATENÇÂO==========\n INFORMAÇÂO:\n*****************\n";
            r += " Vertice: Lista vazia";
        }
        if (getEdges().size() != 0) {
            r += "\n Aresta: {";

            for (int i = 0; i < getEdges().size(); i++) {
                r += "(" + getEdges().get(i).getNode1().getId() + "," + getEdges().get(i).getNode2().getId() + ")";
                if (i < getEdges().size() - 1) {
                    r += ",";
                }
            }
            r += "}";
        } else {
            r += "\n Aresta: Lista vazia";
        }
        return r;
    }
public String geraGraphUndirected(int m){
        String espaco = " ";
        String lista = "digraph G {\n";
        int i,v;
        
        if(getEdgedefault().equals("directed")){
            if(m == 0){
                for (i=0; i< getEdges().size();i++) {
                    lista +=  getEdges().get(i).getSoucer() + " -> " +  getEdges().get(i).getTarget() +"[label="+getEdges().get(i).getId()+"]\n";
                }
            }else{
                for (i=0; i< getEdges().size();i++) {
                    lista +=  getEdges().get(i).getSoucer() + " -> " +  getEdges().get(i).getTarget() +"[label="+getEdges().get(i).getPeso()+"]\n";
                } 
            }
        }else{
            if(m == 0){
                for (i=0; i< getEdges().size();i++) {
                    lista +=  getEdges().get(i).getSoucer() + " -> " +  getEdges().get(i).getTarget() +" [dir=none][label="+getEdges().get(i).getId()+"]\n";
                }
            }else{
                for (i=0; i< getEdges().size();i++) {
                    lista +=  getEdges().get(i).getSoucer() + " -> " +  getEdges().get(i).getTarget() +" [dir=none][label="+getEdges().get(i).getPeso()+"]\n";
                } 
            }
        }
            for (v = 0; v < getNodes().size();v++) {
            lista += getNodes().get(v).getId()+";\n";

            }
        lista += "}";
        return lista;
    }
}

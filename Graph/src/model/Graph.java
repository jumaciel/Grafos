package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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

    public boolean addNodes(String node) {
        Node n = new Node();

        for (int i = 0; i < getNodes().size(); i++) {  // Varre a lista de vertice
            if (getNodes().get(i).getId().equals(node)) { //Verifica se o vertice  i  é igual o parametro passado
                return true;
            }
        }
        n.setId(node);
        addNode(n);
        return false;
    }

    public boolean removerNode(String node) {
        String r = "";

        for (int i = 0; i < getNodes().size(); i++) { //Varre a lista de vertices 
            if (getNodes().get(i).id.equals(node)) { // Verifica se o vertice na posição i é igual o passado pelo parametro
                getNodes().remove(i); // Remove o vertice na posição de i

                for (int a = 0; a < getEdges().size(); a++) { // Varre a lista de arestas
                    // Verifica se a aresta na posição de i é igual o passado pelo paramentro
                    if (getEdges().get(a).getNode1().getId().equals(node)) {
                        getEdges().remove(a);  // Remove a aresta na posição de i
                        a--;
                    }
                }
                for (int a2 = 0; a2 < getEdges().size(); a2++) { // Varre a lista de arestas
                    // Verifica se a aresta na posição de i é igual o passado pelo paramentro
                    if (getEdges().get(a2).getNode2().getId().equals(node)) {
                        getEdges().remove(a2); // Remove a aresta na posição de i2
                        a2--;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public String EditarNode(String node, String novo) {
        String r = "";
        String b = node;

        for (int i = 0; i < getNodes().size(); i++) { // FOR VARENDO TODOS OS NOS
            if (getNodes().get(i).getId().equals(node)) {// VERIFICANDO SE É O NO 
                getNodes().get(i).setId(novo); // SE FOR, ALTERA
                r += "Vertice alterado com sucesso.\n\n"
                        + "Vertice " + node + " === Trocado por === > " + novo + "\n\n"
                        + "Edges atualizadas com sucesso.\n\n";
            }
        }
        for (int a = 0; a < getEdges().size(); a++) { // FOR VARENDO TDS AS ARESTAS
            if (getEdges().get(a).getSoucer().equals(b)) {// VENDO SE A ARESTA ANTIGA É A ORIGEM
                getEdges().get(a).setSoucer(novo);  //SE FOR, COLOCA O NOVO PARAMETRO
//                a--;
            }
        }
        for (int a2 = 0; a2 < getEdges().size(); a2++) {// OUTRO FOR VARENDO ARESTAS
            if (getEdges().get(a2).getTarget().equals(b)) {//VERIFICA SE A ARESTA ANTIGA É O DESTINO
                r += "Edge (" + getEdges().get(a2).getSoucer() + "," + getEdges().get(a2).getTarget() + ") ============== > ("
                        + getEdges().get(a2).getNode1().getId() + "," + getEdges().get(a2).getNode2().getId() + ")\n";
                getEdges().get(a2).setTarget(novo);
//                a2--; // ADC OS VERTICES Q FORAM ALTERADOS E COLOCA EM R
            }
        }
        return r;
    }

    public String addEdges(String n1, String n2, float peso, String nome) {
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
        e.setPeso(peso); //seta o peso da aresta
        e.setId(nome); //Seta o nome da aresta
        addEdge(e); //adiciona o objeto na lista de arestas

        return ImprimeEdge();
    }

    public String removerEdge(String node1, String node2) {
        for (int i = 0; i < edges.size(); i++) {//VARENDO A LISTA DE ARESTAS
            //PROCURANDO SE OS NÓS SÃO = AOS PASSADOS
            if (edges.get(i).getNode1().getId().equals(node1) && edges.get(i).getNode2().getId().equals(node2)) {
                edges.remove(i);
                return "Aresta (" + node1 + "," + node2 + ") removido com sucesso";
            }
        }
        return "Não foi possivel remover";
    }

    public String getGrau() {
        String listaGrau = "";
        if (getEdgedefault().equals("directed")) {
            for (Edge e : getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
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
        } else {
            for (Edge e : getEdges()) {  //Varre a lista de aresta contando quantas vezes o vertice aparece
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

    public int getGrauVertice(String vertice) {
        int grau = 0;
        for (Node no : getNodes()) {
            grau++;
        }
        return grau;
    }

    public String matrizAdjacencia() {
        String espaco = "    ";
        String matrizTotal = "  ";
        int i, j;
        int matriz[][] = new int[getNodes().size()][getNodes().size()];
        if (getEdgedefault().equals("directed")) {
            for (Edge e : getEdges()) {
                int no1 = getNodes().indexOf(e.getNode1());
                int no2 = getNodes().indexOf(e.getNode2());
                matriz[no1][no2] = 1;
            }
            for (int a = 0; a < getNodes().size(); a++) {
                matrizTotal += espaco + getNodes().get(a).getId();
            }
            for (i = 0; i < getNodes().size(); i++) {
                matrizTotal += "\n" + getNodes().get(i).getId();
                for (j = 0; j < getNodes().size(); j++) {
                    matrizTotal += espaco + matriz[i][j];
                }
            }
        } else {
            for (Edge e : getEdges()) {
                int no1 = getNodes().indexOf(e.getNode1());
                int no2 = getNodes().indexOf(e.getNode2());
                matriz[no1][no2] = 1;
                matriz[no2][no1] = 1;
            }
            for (int a = 0; a < getNodes().size(); a++) {
                matrizTotal += espaco + getNodes().get(a).getId();
            }
            for (i = 0; i < getNodes().size(); i++) {
                matrizTotal += "\n" + getNodes().get(i).getId();
                for (j = 0; j < getNodes().size(); j++) {
                    matrizTotal += espaco + matriz[i][j];
                }
            }
        }
        return (matrizTotal);
    }

    public String matrizIncidencia(Graph graph) {
        String incidencia = "  ";
        String espaco = "       ";
        int index = 0;
        int i, j;
        int matriz[][] = new int[graph.getNodes().size()][graph.getEdges().size()];

        if (getEdgedefault().equals("directed")) {
            for (Edge e : graph.getEdges()) {
                int no1 = graph.getNodes().indexOf(e.getNode1());
                int no2 = graph.getNodes().indexOf(e.getNode2());

                if (!e.getNode1().getId().equals(e.getNode2().getId())) {
                    matriz[no1][index] = 1;
                    matriz[no2][index] = -1;

                    index++;
                } else {
                    matriz[no1][index] = 2;
                    matriz[no2][index] = 2;
                    index++;

                }

            }

            for (int a = 1; a < graph.getEdges().size() + 1; a++) {
                incidencia += espaco + graph.getEdges().get(a - 1).getId();
            }

            for (i = 0; i < graph.getNodes().size(); i++) {
                incidencia += "\n" + getNodes().get(i).getId();
                for (j = 0; j < graph.getEdges().size(); j++) {
                    incidencia += espaco + matriz[i][j];
                }
            }
        } else {
            for (Edge e : graph.getEdges()) {
                int no1 = graph.getNodes().indexOf(e.getNode1());
                int no2 = graph.getNodes().indexOf(e.getNode2());

                matriz[no1][index] = 1;
                matriz[no2][index] = 1;
                index++;
            }
            for (int a = 1; a < graph.getEdges().size() + 1; a++) {
                incidencia += espaco + graph.getEdges().get(a - 1).getId();
            }
            for (i = 0; i < graph.getNodes().size(); i++) {
                incidencia += "\n" + getNodes().get(i).getId();
                for (j = 0; j < graph.getEdges().size(); j++) {
                    incidencia += espaco + matriz[i][j];
                }
            }
        }
        return incidencia;
    }

    public String listaAdjacencia(Graph graph) {
        String lista = "";
        int i, j;
        int matriz[][] = new int[graph.getNodes().size()][graph.getNodes().size()];

        if (getEdgedefault().equals("directed")) {
            for (Edge e : graph.getEdges()) {
                int no1 = graph.getNodes().indexOf(e.getNode1());
                int no2 = graph.getNodes().indexOf(e.getNode2());
                matriz[no1][no2] = 1;
            }
            for (i = 0; i < graph.getNodes().size(); i++) {
                lista += "\n[ " + graph.getNodes().get(i).getId() + " ]";
                for (j = 0; j < graph.getNodes().size(); j++) {
                    if (matriz[i][j] == 1) {
                        lista += " =>[ " + graph.getNodes().get(j).getId() + " ]";
                    }
                }
            }
        }
        for (Edge e : graph.getEdges()) {
            int no1 = graph.getNodes().indexOf(e.getNode1());
            int no2 = graph.getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
            matriz[no2][no1] = 1;
        }
        for (i = 0; i < graph.getNodes().size(); i++) {
            lista += "\n[ " + graph.getNodes().get(i).getId() + " ]";
            for (j = 0; j < graph.getNodes().size(); j++) {
                if (matriz[i][j] == 1) {
                    lista += " =>[ " + graph.getNodes().get(j).getId() + " ]";
                }
            }
        }
        return lista;
    }

    public String getIncidentes() {
        String incidentes = "";
        for (Edge e : getEdges()) {
            incidentes += " Os Vertices " + e.getNode1().getId() + " e " + e.getNode2().getId() + " são incidente com a aresta ("
                    + e.getNode1().getId() + "," + e.getNode2().getId() + ")\n";
        }
        return incidentes;
    }

    public String getAdjacentes() {
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
        for (int i = 0; i < getEdges().size(); i++) {
            e += getEdges().get(i).getNode1().getId() + "," + getEdges().get(i).getNode2().getId() + " => ";
        }
        conjunto = "Vértices \n" + no + "\n Arestas \n" + e;
        return conjunto;
    }

    public boolean completo() {
        for (int i = 0; i < getNodes().size(); i++) {
            if (getNodes().get(i).getGrau() != (getNodes().size() - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean regular() {
        for (int i = 0; i < getNodes().size(); i++) {
            if (getNodes().get(0).getGrau() == getNodes().get(i).getGrau()) {
                if (i == getNodes().size() - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean multigrafo() {
        for (int i = 0; i < getEdges().size(); i++) {
            for (int i2 = 0; i2 < getEdges().size(); i2++) {
                if (getEdges().get(i).getNode1().getId() == getEdges().get(i2).getNode2().getId()) {
                    for (int i3 = 0; i3 < getEdges().size(); i3++) {
                        if (getEdges().get(i2).getNode1().getId() == getEdges().get(i).getNode2().getId()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String grauRecepcao() {
        String lista = "Grau Recepção\n********************\n";
        int i, j, grauR = 0;
        List<Node> auxGrauR = new ArrayList<Node>();

        for (i = 0; i < getEdges().size(); i++) {//adiciona todas os detisno na lista auxiliar
            auxGrauR.add(getEdges().get(i).getNode2());
        }
        for (i = 0; i < getNodes().size(); i++) {
            for (int i2 = 0; i2 < auxGrauR.size(); i2++) { // conta quantas vezes o vertice aparece na lista auxiliar
                if (auxGrauR.get(i2).getId().equals(getNodes().get(i).getId())) {
                    grauR++;
                }
            }
            lista += getNodes().get(i).getId() + " é: " + grauR + "\n";
            grauR = 0;
        }
        return lista;
    }

    public String grauEmissao() {
        String lista = "Grau de Emissão\n********************\n";
        int i, j, grauE = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < getEdges().size(); i++) {
            auxQr.add(getEdges().get(i).getNode1());
        }
        for (i = 0; i < getNodes().size(); i++) {
            for (int i2 = 0; i2 < auxQr.size(); i2++) {
                if (auxQr.get(i2).getId().equals(getNodes().get(i).getId())) {
                    grauE++;
                }
            }
            lista += getNodes().get(i).getId() + " é: " + grauE + "\n";
            grauE = 0;
        }

        return lista;
    }

    public String fonte() {
        String lista = "Fontes\n********\n";
        int i, j, grauR = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < getEdges().size(); i++) {
            auxQr.add(getEdges().get(i).getNode2());
        }
        for (i = 0; i < getNodes().size(); i++) {
            for (int i2 = 0; i2 < auxQr.size(); i2++) {
                if (auxQr.get(i2).getId().equals(getNodes().get(i).getId())) {
                    grauR++;
                }
            }
            if (grauR == 0) {
                lista += getNodes().get(i).getId() + "\n";
            }
            grauR = 0;
        }
        return lista;
    }

    public String sumidouro() {
        String lista = "Sumidouro\n********\n";
        int i, j, grauR = 0;
        List<Node> auxQr = new ArrayList<Node>();
        for (i = 0; i < getEdges().size(); i++) {
            auxQr.add(getEdges().get(i).getNode1());
        }
        for (i = 0; i < getNodes().size(); i++) {
            for (int i2 = 0; i2 < auxQr.size(); i2++) {
                if (auxQr.get(i2).getId().equals(getNodes().get(i).getId())) {
                    grauR++;
                }
            }
            if (grauR == 0) {
                lista += getNodes().get(i).getId() + "\n";
            }
            grauR = 0;
        }
        return lista;

    }

    public String verificaCadeia(String origem, String destino, int o, List<Node> visitado) {

        List<Node> auxVisitado = new ArrayList<Node>();
        List<Node> auxVisita = new ArrayList<Node>();
        auxVisitado = (List<Node>) visitado;
        String result = "{", auxOrigem = "";
        int prox = o, posicao = 0;
        int matriz[][] = new int[getNodes().size()][getNodes().size()];

        if (getEdges().isEmpty()) {
            return "Cadeia: não existe";
        } else if (origem.equals(destino)) {
            return "Cadeia: não existe";
        } else if (getGrauVertice(origem) == 0) {
            return "Cadeia: não existe";
        }

        for (Edge e : getEdges()) {
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
            matriz[no2][no1] = 1;
        }

        for (int i6 = 0; i6 < auxVisitado.size(); i6++) { // varre a lista de visitado
            if (auxVisitado.get(i6).getId().equals(origem)) { // verifica se a origem esta na lista 
                verificaCadeia(auxVisita.get(prox++).getId(), destino, prox, auxVisitado);// passa para o proximo da lista que ira ser visitado
            }
        }

        for (int i = 0; i < getNodes().size(); i++) {//varra a lista de vertice
            if (getNodes().get(i).getId().equals(origem)) { //verifica se encontrou 
                posicao = i; //guardando a posição do vertice
                auxVisita.clear(); //limpa a lista
                auxVisitado.add(getNodes().get(i));//adiciona na lista de visitado
            }
        }

        for (int j = 0; j < getNodes().size(); j++) {//varre a lista de vertice
            if (matriz[posicao][j] == 1) {
                auxVisita.add(getNodes().get(j));//adiciona na lista que ira ser visitado

            }
        }

        for (int i2 = 0; i2 < auxVisita.size(); i2++) {//varre a lista de visita
            if (auxVisita.get(i2).getId().equals(destino)) {//verifica se na lista que ira visitar encontra-se o destino
                for (int j2 = 0; j2 < auxVisitado.size(); j2++) {//varre a lista de visita
                    result += auxVisitado.get(j2).getId() + ",";//pega o resultado da lista de visitado
                }
                return result + destino + "}";
            }
            auxOrigem = auxVisita.get(0).getId(); // pega o primeiro elemento da lista
        }

        return verificaCadeia(auxOrigem, destino, prox, auxVisitado);
    }

    public String verificaCaminho(String origem, String destino, int o, List<Node> visitado) {

        List<Node> auxVisitado = new ArrayList<Node>();
        List<Node> auxVisita = new ArrayList<Node>();
        auxVisitado = (List<Node>) visitado;
        String result = "{", auxOrigem = "";
        int prox = o, posicao = 0;
        int matriz[][] = new int[getNodes().size()][getNodes().size()];

        if (origem.equals(destino)) {
            return "Caminho: não existe";
        }

        for (Edge e : getEdges()) {
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
        }

        for (int i6 = 0; i6 < auxVisitado.size(); i6++) { // varre a lista de visitado
            if (auxVisitado.get(i6).getId().equals(origem)) { // verifica se a origem esta na lista 
                verificaCadeia(auxVisita.get(prox++).getId(), destino, prox, auxVisitado);// passa para o proximo da lista que ira ser visitado
            }
        }

        for (int i = 0; i < getNodes().size(); i++) {//varra a lista de vertice
            if (getNodes().get(i).getId().equals(origem)) { //verifica se encontrou 
                posicao = i; //guardando a posição do vertice
                auxVisita.clear(); //limpa a lista
                auxVisitado.add(getNodes().get(i));//adiciona na lista de visitado
            }
        }

        for (int j = 0; j < getNodes().size(); j++) {//varre a lista de vertice
            if (matriz[posicao][j] == 1) {
                auxVisita.add(getNodes().get(j));//adiciona na lista que ira ser visitado

            }
        }

        for (int i2 = 0; i2 < auxVisita.size(); i2++) {//varre a lista de visita
            if (auxVisita.get(i2).getId().equals(destino)) {//verifica se na lista que ira visitar encontra-se o destino
                for (int j2 = 0; j2 < auxVisitado.size(); j2++) {//varre a lista de visita
                    result += auxVisitado.get(j2).getId() + ",";//pega o resultado da lista de visitado
                }
                return result + destino + "}";
            }
            auxOrigem = auxVisita.get(0).getId(); // pega o primeiro elemento da lista
        }

        return verificaCaminho(auxOrigem, destino, prox, auxVisitado);
    }

    public String Kruskal() {
        String r = "";
        int matrizPeso[][] = new int[getNodes().size()][getNodes().size()];
        int[] pai = new int[getNodes().size()];
        int min = 0;
        int u = 0;
        int v = 0;
        int x, y;
        int nArestas = 1;
        int total = 0;

        //Inicializa matrizPeso com pesos do grafo dado.
        for (Edge e : getEdges()) {
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matrizPeso[no1][no2] = (int) e.getPeso();
        }

        //Define valor infinitamente alto para arestas == 0
        for (int i = 0; i < getNodes().size(); i++) {
            pai[i] = 0;
            for (int j = 0; j < getNodes().size(); j++) {
                if (matrizPeso[i][j] == 0) {
                    matrizPeso[i][j] = 999;
                }
            }
        }

        // Percorre arestas pegando sempre a aresta de menor peso
        // e guardando em min este menor peso.
        // Min é reinicializado para um número alto 
        // a cada iteração do while.
        while (nArestas < getNodes().size()) {
            min = 99999;
            for (int i = 0; i < getNodes().size(); ++i) {
                for (int j = 0; j < getNodes().size(); ++j) {
                    if (matrizPeso[i][j] < min) {
                        //Grava em u a origem e em v o destino da aresta de menor peso
                        min = matrizPeso[i][j];
                        u = i;
                        v = j;
                    }
                }
            }

            x = u;
            y = v;
            //Se o pai da origem da aresta de menor peso é diferente de 0,
            //O pai de x é armazenado em x
            while (pai[x] != 0) {
                x = pai[x];
            }
            //Se o pai do destino da aresta de menor peso é diferente de 0,
            //O pai de y é armazenado em y

            while (pai[y] != 0) {
                y = pai[y];
            }

            if (x != y) {
                nArestas++;
                r += "Aresta: {" + getNodes().get(u).getId() + "," + getNodes().get(v).getId() + "} Peso: " + min;
                total += min;
//                System.out.println("pai[" + v + "] = " + u);
                pai[v] = u;
                r += "\n";
            }
            //A aresta mínima selecionada recebe pesso 99999 e não volta
            //a passar no  if (matrizPeso[i][j] < min)
            matrizPeso[u][v] = matrizPeso[v][u] = 99999;
        }
        r += "Peso minimo da arvore: " + total;
        return r;
    }

    public String Prim() {
        String r = "";
        int[] visitado = new int[getNodes().size()];
        int min = 999;
        int u = 0;
        int v = 0;
        int total = 0;
        int matrizPeso[][] = new int[getNodes().size()][getNodes().size()];
//PREENCHE UMA MATRIZ DE PESOS
        for (Edge e : getEdges()) {
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matrizPeso[no1][no2] = (int) e.getPeso();
        }
//VARE OS VERTICES, COLOCANDO OS VISITADOS COMO 0
        for (int i = 0; i < getNodes().size(); i++) {
            visitado[i] = 0;
            //VARE A LISTA DE VERTICE E VE ONDE O PESO É 0, E COLOCA "INFINITO", PESO MAX...
            for (int j = 0; j < getNodes().size(); j++) {
                if (matrizPeso[i][j] == 0) {
                    matrizPeso[i][j] = 999;
                }
            }
        }
        visitado[0] = 1; //PRIMEIRA POSIÇÃO DO VISITADO 

        //VARE A LISTA DE VERTICES
        for (int cont = 0; cont < getNodes().size() - 1; cont++) {
            min = 999;

            for (int i = 0; i < getNodes().size(); i++) {
                if (visitado[i] == 1) { //VERIFICA SE JA FOI VISITADO
                    for (int j = 0; j < getNodes().size(); j++) {
                        if (visitado[j] != 1) {
                            if (min > matrizPeso[i][j]) {
                                min = matrizPeso[i][j]; //SE O MIN FOR MAIOR Q A MATRIZ DE PESO , O MIN RECEBE AQUELE PESO...
                                u = i;//GUARDA AS POSIÇÕES
                                v = j;
                            }
                        }
                    }
                }
            }
            visitado[v] = 1; //NA POSICAO DE V , RECEBE 1 COMO VISITADO
            total += min; //ENCREMENTA O TOTAL
            r += "Aresta: {" + getNodes().get(u).getId() + "," + getNodes().get(v).getId() + "}: peso " + min;
            r += "\n";

        }
        r += "Peso minimo da arvore: " + total;
        return r;
    }

    public String Dijkstra() {

        int min;
        int nextNode = 0;
        int[] distancia = new int[getNodes().size()];
        int[] visitado = new int[getNodes().size()];
        int[] preD = new int[getNodes().size()];
        int matrizPeso[][] = new int[getNodes().size()][getNodes().size()];

        for (Edge e : getEdges()) { //PREENCHE A MATRIZ DE PESO
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matrizPeso[no1][no2] = (int) e.getPeso();
        }
        //VARENDO OS VERTICES  COLOCA 0 NOS VISITADOS
        for (int i = 0; i < getNodes().size(); i++) {
            visitado[i] = 0;
            preD[i] = 0;
            //COLOCA "INFINITO" (PESO MAX), NOS QUE TEM PESO 0
            for (int j = 0; j < matrizPeso[0].length; j++) {
                if (matrizPeso[i][j] == 0) {
                    matrizPeso[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        distancia = matrizPeso[0];
        distancia[0] = 0;
        visitado[0] = 1;

        //VARE OS VERTICES COLOCANDO O "INFINITO" NO MIN
        for (int i = 0; i < getNodes().size(); i++) {
            min = 999;

            //FOR TA VARENDO OS VERTICES VERIFICANDO SE O MIN É MAIOR Q A DISTANCIA E SE OS VISITADOS E != 1
            for (int j = 0; j < getNodes().size(); j++) {
                if (min > distancia[j] && visitado[j] != 1) {
                    min = distancia[j];
                    nextNode = j;
                }
            }

            visitado[nextNode] = 1;
            //VARE A LISTA DE VERTICE VERIFICANDO SE VISITADO É != 1
            for (int i2 = 0; i2 < getNodes().size(); i2++) {
                if (visitado[i2] != 1) {
                    if (min + matrizPeso[nextNode][i2] < distancia[i2]) { //SOMA O MIN + MATRIZ DE PESO E VERIFICA SE É MENOR Q A DISTANCIA 
                        distancia[i2] = min + matrizPeso[nextNode][i2]; //DISYANCIA RECEBE MIN+MATRIZ DE PESO
                        preD[i2] = nextNode;
                    }
                }
            }
        }
        String r = "";
//PARA IMPRIMIR
        for (int i = 0; i < getNodes().size(); i++) {
            int j;
            r += "Caminho: " + getNodes().get(i).getId();
            j = i;

            do {
                j = preD[j];
                r += " <= " + getNodes().get(j).getId();
            } while (j != 0);
            r += "\n\n";
        }
        return r;
    }

    public String Malgrange() {
        String r = "";
        int matriz[][] = new int[getNodes().size()][getNodes().size()];
        List<String> RMais = new ArrayList<String>();
        List<String> RMenos = new ArrayList<String>();
        List<String> Result = new ArrayList<String>();

        RMenos.add(getNodes().get(0).getId());

        for (Edge e : getEdges()) {
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
        }

        for (int i = 0; i < getNodes().size(); i++) {
            r += "\n" + getNodes().get(i).getId();
            for (int j = 0; j < getNodes().size(); j++) {
                r += "      " + matriz[i][j];
            }
        }
        r += "\n\n\n";
        r += "R-\n";
        r += RMenos.get(0);
        r += ",";
        for (int i = 0; i < getNodes().size(); i++) {
            for (int j = 0; j < getNodes().size(); j++) {
                if (matriz[i][j] == 1) {
                    if (i == 0) {
                        RMenos.add(getNodes().get(j).getId());
                        r += getNodes().get(j).getId();
                        r += ",";
                    }
                    boolean tem = false;
                    for (int i2 = 0; i2 < RMenos.size(); i2++) {
                        if (getNodes().get(j).getId().equals(RMenos.get(i2))) {
                            tem = true;
                        }
                    }
                    if (tem == false) {
                        RMenos.add(getNodes().get(j).getId());
                        r += getNodes().get(j).getId();
                        r += ",";
                    }
                }
            }
        }
        RMais.add(getNodes().get(0).getId());
        r += "\n\n\n";
        r += "R+\n";
        r += RMais.get(0);
        r += ",";
        for (int i = 0; i < getNodes().size(); i++) {
            for (int j = 0; j < getNodes().size(); j++) {
                if (matriz[j][i] == 1) {
                    if (j == 0) {
                        RMais.add(getNodes().get(i).getId());
                        r += getNodes().get(i).getId();
                        r += ",";
                    }
                    boolean tem = false;
                    for (int i2 = 0; i2 < RMais.size(); i2++) {
                        if (getNodes().get(i).getId().equals(RMais.get(i2))) {
                            tem = true;
                        }
                    }
                    if (tem == false) {
                        RMais.add(getNodes().get(i).getId());
                        r += getNodes().get(i).getId();
                        r += ",";
                    }
                }
            }
        }
        r += "\n\n"
                + "Resultado: ";
        for (int i = 0; i < RMenos.size(); i++) {
            for (int j = 0; j < RMais.size(); j++) {
                if (RMenos.get(i).equals(RMais.get(j))) {
                    r += RMenos.get(i);
                }
            }
            r += ",";
        }

        return r;
    }

    public String BuscaProf(String origem, String destino, int o, List<Node> visitado) {
        List<Node> auxVisitado = new ArrayList<Node>();
        List<Node> auxVisita = new ArrayList<Node>();
        int prox = o, posicao = 0;
        int matriz[][] = new int[getNodes().size()][getNodes().size()];

        String result = "{", auxOrigem = "";
        auxVisitado = (List<Node>) visitado;

        //PREENCHE A MATRIZ ADJACENTE
        for (Edge e : getEdges()) {
            int no1 = getNodes().indexOf(e.getNode1());
            int no2 = getNodes().indexOf(e.getNode2());
            matriz[no1][no2] = 1;
        }

        for (int i = 0; i < getNodes().size(); i++) {//varra a lista de vertice
            if (getNodes().get(i).getId().equals(origem)) { //verifica se encontrou 
                posicao = i; //guardando a posição do vertice
                auxVisita.clear(); //limpa a lista
                auxVisitado.add(getNodes().get(i));//adiciona na lista de visitado
            }
        }

        for (int j = 0; j < getNodes().size(); j++) {//varre a lista de vertice
            if (matriz[posicao][j] == 1) {
                auxVisita.add(getNodes().get(j));//adiciona na lista que ira ser visitado

            }
        }

        for (int i2 = 0; i2 < auxVisita.size(); i2++) {//varre a lista de visita
            if (auxVisita.get(i2).getId().equals(destino)) {//verifica se na lista que ira visitar encontra-se o destino
                for (int j2 = 0; j2 < auxVisitado.size(); j2++) {//varre a lista de visita
                    result += auxVisitado.get(j2).getId() + " => ";//pega o resultado da lista de visitado
                }
                return result + destino + "}";
            }
            auxOrigem = auxVisita.get(0).getId(); // pega o primeiro elemento da lista
        }

        return BuscaProf(auxOrigem, destino, prox, auxVisitado);
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

}

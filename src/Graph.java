import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/** Grafo representado pela matriz de adjacencia */

public class Graph {
    private int V;
    private boolean existPath;
    int parents[];
    int distances[];
    private int adj[][];
    private int g[];
    private boolean visited[];
    private Stack<Integer> path;

    /* O grafo tem V vértices {0, 1, ..., V-1} */
    public Graph(int V){
        this.V = V;
        this.existPath = false;
        this.parents = new int[V];
        this.distances = new int[V];
        this.path = new Stack<Integer>();
        this.adj = new int[V][V];
        this.g = new int[V]; /* Grau do vertice := numero de arestas que incide no vertice */
        this.visited = new boolean[V];
    }

    public void addEdge(int u, int v) {
        if (adj[u][v] == 0) {
            g[u]++;
            g[v]++;
            adj[u][v] = 1;
            adj[v][u] = 1;
        }
    }

    public void removeEdge(int u, int v) {
        /* Esse método é completamente análogo ao anterior */
        if (adj[u][v] == 1) {
            g[u]--;
            g[v]--;
            adj[u][v] = adj[v][u] = 0;
        }
    }

    /* Grau do vertice := numero de arestas que incide no vertice */
    public int getDegree(int u) {
        return g[u];
    }

    public void showPath(){
        System.out.println(path.clone());
    }

    public void showMatrix(){
        int i =0;
        int j = 0;
        for (int[] v: this.adj){
            for(int w: v) {
                System.out.print("v"+ i +":"+ v[j] +", ");
                j++;
            }
            System.out.println("\n");
            j=0;
            i++;
        }
    }

    public void preencheMatriz(Graph G){
        G.addEdge(0, 1);
        G.addEdge(0, 2);

        G.addEdge(2, 3);

        G.addEdge(3, 4);
        G.addEdge(3, 5);

        G.addEdge(4, 6);

        G.addEdge(5, 6);

        G.addEdge(6, 7);

        G.addEdge(7, 8);

        G.addEdge(8, 9);
        G.addEdge(8, 15);

        G.addEdge(9, 10);
        G.addEdge(9, 14);

        G.addEdge(10, 11);

        G.addEdge(11, 12);

        G.addEdge(12, 13);

        G.addEdge(15, 14);
        G.addEdge(15, 17);

        G.addEdge(14, 16);

        G.addEdge(16, 17);
        G.addEdge(16, 18);

        G.addEdge(18, 19);

        G.addEdge(19,20);
        G.addEdge(19, 39);

        G.addEdge(39, 40);
        G.addEdge(39, 44);

        G.addEdge(40, 41);

        G.addEdge(41, 42);
        G.addEdge(41, 43);

        G.addEdge(44, 45);

        G.addEdge(45, 46);

        G.addEdge(46, 47);

        G.addEdge(47, 48);

        G.addEdge(48, 49);

        G.addEdge(20, 21);

        G.addEdge(21, 22);

        G.addEdge(22, 23);

        G.addEdge(23, 24);

        G.addEdge(24, 25);

        G.addEdge(25, 26);

        G.addEdge(26, 27);

        G.addEdge(27, 28);

        G.addEdge(28, 29);

        G.addEdge(29, 30);

        G.addEdge(30, 31);

        G.addEdge(31, 32);

        G.addEdge(32, 33);

        G.addEdge(33, 34);

        G.addEdge(34, 35);

        G.addEdge(35, 36);

        G.addEdge(36, 37);

        G.addEdge(37, 38);

    }

    public void DFS(int fim){
        if (fim >= this.V){
            return;
        }
        path.clear();
        for(int i=0; i < this.V; i++){
            visited[i] = false;
        }
        for(int i=0; i < this.V; i++){
            if(!visited[i]){
                depth(i, fim);
            }
        }
    }

    public void depth(int i, int fim){
        if (!visited[i]) {
            visited[i] = true;
            path.push(i);
        }

        int visits = 0;
        for(int j=0; j<this.V; j++) {
            if (adj[i][j] == 1 && !visited[j]){
                depth(j, fim);
            } else {
                visits++;
            }
        }
        if(path.peek() == fim){
            existPath = true;
            return;
        }

        if (visits == this.V){
            path.pop();
            depth(path.peek(), fim);
        }

    }

    public void BFS(int beggin, int end){
        ArrayList<Integer> queue = new ArrayList<Integer>();
        int dist = 1;

        if (visited.length > 0){
            Arrays.fill(visited, false);
        }

        parents[beggin] = beggin;
        for (int i=0; i < V; i++){
            if (adj[beggin][i] == 1 && !visited[i]){
                queue.add(i);
                parents[i] = beggin;
                distances[i] = dist;
            }
        }
        visited[beggin] = true;

        while (!queue.isEmpty()){
            dist++;
            queue = breadth(queue, dist);
        }

        int p = end;
        while (p != beggin){
            path.push(p);
            p = parents[p];
        }
        path.push(p);
        existPath = true;
    }

    private ArrayList<Integer> breadth(ArrayList<Integer> queue, int dist){
        ArrayList<Integer> aux = new ArrayList<>();
        for (int n: queue){
            visited[n] = true;
        }
        for(int v: queue){
            for(int i=0; i<V; i++){
                if (adj[v][i] == 1 && !visited[i]){
                    if (!aux.contains(i)){
                        aux.add(i);
                        parents[i] = v;
                        distances[i] = dist;
                    }
                }
            }
        }
        return aux;
    }

    public static void main(String[] args) {
        Graph G = new Graph(50);

        G.preencheMatriz(G);

//        G.addEdge(0, 1);
//        G.addEdge(0, 3);
//
//        G.addEdge(1, 2);
//        G.addEdge(1, 4);
//
//        G.addEdge(3, 4);
//
//        G.addEdge(4, 5);
//        G.addEdge(4, 6);
//
//        G.addEdge(5, 7);
//
//        G.addEdge(6, 7);
//
//        G.addEdge(7, 8);

        // TEMPO BFS
        double inicioBFS = System.currentTimeMillis();
        G.BFS(0, 49);
        double fimBFS  = System.currentTimeMillis();
        if (G.existPath){
            System.out.println("Caminho BFS:");
            G.showPath();
        }
        else{
            System.out.println("Não há path.");
        }
        System.out.println("Tempo BFS: "+ (fimBFS - inicioBFS));
        System.out.println("\n");

        // TEMPO DFS
        double inicioDFS = System.currentTimeMillis();
        G.DFS(49);
        double fimDFS  = System.currentTimeMillis();
        if (G.existPath){
            System.out.println("Caminho DFS:");
            G.showPath();
        }
        else{
            System.out.println("Não há path.");
        }
        System.out.println("Tempo DFS: "+ (fimDFS - inicioDFS));

    }
}






















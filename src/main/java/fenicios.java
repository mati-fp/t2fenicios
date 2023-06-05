import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class fenicios {
    private int linhas;
    private int colunas;
    private nodoLocalizacao[][] mapa;
    private nodoLocalizacao terraFenicios;
    private PriorityQueue<Integer> portosParaNegociar;
    private List<Integer> portosNegociados;
    private List<Integer> combustivelPorPorto;
    private int combustivelNecessario;

    public fenicios(resultadoLeitura resulta) {
        this.linhas = resulta.linhas;
        this.colunas = resulta.colunas;
        this.mapa = resulta.mapa;
        this.terraFenicios = resulta.terraFenicios;
        this.portosParaNegociar  = new PriorityQueue<Integer>();
        this.combustivelPorPorto = new ArrayList<Integer>();
        this.combustivelNecessario = 0;
        this.portosNegociados = new ArrayList<Integer>();
    }

    public void iniciaNegocios() {
        fazCaminhamentoDeLarguraDoMapa();
        visitarParceirosDeNegocio(terraFenicios, portosParaNegociar.poll());
        this.combustivelNecessario = combustivelPorPorto.stream().mapToInt(Integer::intValue).sum();
    }

    public void fazCaminhamentoDeLarguraDoMapa() {
        // Define a ordem de visita para cima, baixo, esquerda, direita
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Marca todos os nodos como não visitados
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (mapa[i][j] != null)
                    mapa[i][j].setVisitado(false);
            }
        }

        // Fila para armazenar os nodos a serem visitados
        Queue<nodoLocalizacao> queue = new LinkedList<>();

        // Ponto de partida: terraFenicios
        terraFenicios.setVisitado(true);
        queue.add(terraFenicios);

        // Processamento da BFS
        while (!queue.isEmpty()) {
            nodoLocalizacao currentNode = queue.poll();

            // Percorre os vizinhos em todas as direções (norte, sul, leste, oeste)
            for (int i = 0; i < 4; i++) {
                int newX = currentNode.getX() + dx[i];
                int newY = currentNode.getY() + dy[i];

                // Verifica se o vizinho está dentro dos limites do mapa
                if (newX >= 0 && newX < linhas && newY >= 0 && newY < colunas) {
                    nodoLocalizacao vizinho = mapa[newX][newY];
                    if (vizinho != null) {
                        if (!vizinho.isVisitado()) {
                            // Marca o vizinho como visitado e adiciona à fila
                            vizinho.setVisitado(true);
                            queue.add(vizinho);
                            if (vizinho.getNumeroPorto() != 0){
                                portosParaNegociar.add(vizinho.getNumeroPorto());
                                portosNegociados.add(vizinho.getNumeroPorto());
                            }

                            // Atualiza a referência para o vizinho no nó atual
                            switch (i) {
                                case 0: // Norte
                                    currentNode.setNodoNorte(vizinho);
                                    break;
                                case 1: // Sul
                                    currentNode.setNodoSul(vizinho);
                                    break;
                                case 2: // Leste
                                    currentNode.setNodoLeste(vizinho);
                                    break;
                                case 3: // Oeste
                                    currentNode.setNodoOeste(vizinho);
                                    break;
                            }
                        }
                    }
                }
            }
        }
        // Refaz a matriz mantendo apenas o que é possível visitar que acaba com o problema do caixeiro-viajante
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (mapa[i][j] != null)
                    if (!mapa[i][j].isVisitado())
                        mapa[i][j] = null;
            }
        }
        terraFenicios.setCombustivel(0);
        
    }

    public void visitarParceirosDeNegocio(nodoLocalizacao portoAtual, int proximoPorto) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                if (mapa[i][j] != null)
                    if (mapa[i][j].getNumeroPorto() != portoAtual.getNumeroPorto())
                        this.mapa[i][j].setCombustivel(Integer.MAX_VALUE);
            }
        }

        portoAtual.setCombustivel(0);
    
        PriorityQueue<nodoLocalizacao> queue = new PriorityQueue<>(Comparator.comparingInt(nodoLocalizacao::getCombustivel));      

        queue.add(portoAtual);
    
        while (!queue.isEmpty()) {
            nodoLocalizacao atual = queue.poll();   
    
            //if (combustivelParaChegar[atual.getX()][atual.getY()] < atual.getNumeroPorto()) continue;
            //if (atual.getCombustivel() < atual.getNumeroPorto()) continue;
    
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
    
            for (int i = 0; i < 4; i++) {
                int newX = atual.getX() + dx[i];
                int newY = atual.getY() + dy[i];
    
                if (newX >= 0 && newX < linhas && newY >= 0 && newY < colunas) {
                    nodoLocalizacao vizinho = mapa[newX][newY];
                    if (vizinho != null) {
                        int novoCombustivel = atual.getCombustivel() + 1;
                        if (novoCombustivel < vizinho.getCombustivel())
                        {
                            vizinho.setCombustivel(novoCombustivel);
                            if (vizinho.getNumeroPorto() == proximoPorto) {
                                if ( portosParaNegociar.peek() != null ) {
                                    combustivelPorPorto.add(vizinho.getCombustivel());
                                    visitarParceirosDeNegocio(vizinho, portosParaNegociar.poll());
                                }
                                else {
                                    combustivelPorPorto.add(vizinho.getCombustivel());
                                    visitarParceirosDeNegocio(vizinho, terraFenicios.getNumeroPorto());
                                }
                                break;
                            }
                            else {
                                queue.add(vizinho);
                            }
                        }
                    }
                }
            }
        }
    
    }
    
    public int getCombustivelNecessario() {
        return combustivelNecessario;
    }

    public void getPortosNegociados() {
        portosNegociados.sort(null);
        System.out.println(portosNegociados);
    }

    public void getDistanciaPortos() {
        combustivelPorPorto.stream().forEach(c -> System.out.print(c+" "));
    }
}

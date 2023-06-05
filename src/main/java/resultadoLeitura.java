
public class resultadoLeitura {

    public int linhas;
    public int colunas;
    public nodoLocalizacao mapa [][];
    public nodoLocalizacao terraFenicios;

    public resultadoLeitura(int linhas, int colunas, nodoLocalizacao mapa[][], nodoLocalizacao terraFenicios) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.mapa = mapa;
        this.terraFenicios = terraFenicios;
    }
}

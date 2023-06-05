public class nodoLocalizacao {

    private boolean visitado;
    private int numeroPorto = 0;
    private nodoLocalizacao nodoNorte;
    private nodoLocalizacao nodoSul;
    private nodoLocalizacao nodoLeste;
    private nodoLocalizacao nodoOeste;
    private int x;
    private int y;
    private int combustivel;

    public nodoLocalizacao(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public nodoLocalizacao(int numeroPorto, int x, int y) {
        this.numeroPorto = numeroPorto;
        this.x = x;
        this.y = y;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public int getNumeroPorto() {
        return numeroPorto;
    }

    public void setNumeroPorto(int numeroPorto) {
        this.numeroPorto = numeroPorto;
    }

    public nodoLocalizacao getNodoNorte() {
        return nodoNorte;
    }

    public void setNodoNorte(nodoLocalizacao nodoNorte) {
        this.nodoNorte = nodoNorte;
    }

    public nodoLocalizacao getNodoSul() {
        return nodoSul;
    }

    public void setNodoSul(nodoLocalizacao nodoSul) {
        this.nodoSul = nodoSul;
    }

    public nodoLocalizacao getNodoLeste() {
        return nodoLeste;
    }

    public void setNodoLeste(nodoLocalizacao nodoLeste) {
        this.nodoLeste = nodoLeste;
    }

    public nodoLocalizacao getNodoOeste() {
        return nodoOeste;
    }

    public void setNodoOeste(nodoLocalizacao nodoOeste) {
        this.nodoOeste = nodoOeste;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    @Override
    public String toString() {
        return "nodoLocalizacao{" +
                "visitado=" + visitado +
                ", numeroPorto=" + numeroPorto +
                ", nodoNorte=" + nodoNorte +
                ", nodoSul=" + nodoSul +
                ", nodoLeste=" + nodoLeste +
                ", nodoOeste=" + nodoOeste +
                ", x=" + x +
                ", y=" + y +
                ", combustivel=" + combustivel +
                '}';
    }
}

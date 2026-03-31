public class Pilha {
    private No topo;

    public Pilha() {
        this.topo = null;
    }

    public void empilhar(Pixel p) {
        No novoNo = new No(p);
        if (!estaVazia()) {
            novoNo.setProximo(topo);
            topo.setAnterior(novoNo);
        }
        topo = novoNo;
    }

    public Pixel desempilhar() {
        if (estaVazia()) {
            return null;
        }
        Pixel p = topo.getPixel();
        topo = topo.getProximo();

        if (topo != null) {
            topo.setAnterior(null);
        }

        return p;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}
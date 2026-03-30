public class Pilha {
    private No topo;

    public Pilha() {
        this.topo = null;
    }

    public void empilhar(Pixel p) {
        No novoNo = new No(p);
        novoNo.setProximo(topo);
        topo = novoNo;
    }

    public Pixel desempilhar() {
        if (estaVazia()) {
            return null;
        }
        Pixel p = topo.getPixel();
        topo = topo.getProximo();
        return p;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}
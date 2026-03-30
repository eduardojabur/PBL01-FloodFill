public class Fila {
    private No inicio;
    private No fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    public void enfileirar(Pixel p) {
        No novoNo = new No(p);
        if (estaVazia()) {
            inicio = novoNo;
        } else {
            fim.setProximo(novoNo);
        }
        fim = novoNo;
    }

    public Pixel desenfileirar() {
        if (estaVazia()) {
            return null;
        }
        Pixel p = inicio.getPixel();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        return p;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
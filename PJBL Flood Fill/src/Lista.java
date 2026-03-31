public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }

    public void adicionar(Pixel p) {
        No novoNo = new No(p);
        if (estaVazia()) {
            inicio = novoNo;
        } else {
            fim.setProximo(novoNo);
            novoNo.setAnterior(fim);
        }
        fim = novoNo;
    }


    public boolean remover(Pixel p) {
        if (estaVazia()) return false;

        No atual = inicio;
        while (atual != null) {

            if (atual.getPixel().getX() == p.getX() && atual.getPixel().getY() == p.getY()) {


                if (atual == inicio) {
                    inicio = atual.getProximo();
                    if (inicio != null) inicio.setAnterior(null);
                    else fim = null;
                }

                else if (atual == fim) {
                    fim = atual.getAnterior();
                    if (fim != null) fim.setProximo(null);
                    else inicio = null;
                }

                else {
                    atual.getAnterior().setProximo(atual.getProximo());
                    atual.getProximo().setAnterior(atual.getAnterior());
                }
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
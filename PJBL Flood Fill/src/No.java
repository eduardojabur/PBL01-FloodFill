public class No {
    private Pixel pixel;
    private No proximo;
    private No anterior;

    public No(Pixel pixel) {
        this.pixel = pixel;
        this.proximo = null;
        this.anterior = null;
    }

    public Pixel getPixel() {
        return pixel;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
}
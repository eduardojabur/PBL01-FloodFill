import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FloodFill {


    private boolean coresParecidas(int cor1, int cor2, int tolerancia) {
        int r1 = (cor1 >> 16) & 0xFF;
        int g1 = (cor1 >> 8) & 0xFF;
        int b1 = cor1 & 0xFF;

        int r2 = (cor2 >> 16) & 0xFF;
        int g2 = (cor2 >> 8) & 0xFF;
        int b2 = cor2 & 0xFF;

        return Math.abs(r1 - r2) <= tolerancia &&
                Math.abs(g1 - g2) <= tolerancia &&
                Math.abs(b1 - b2) <= tolerancia;
    }

    private void salvarImagemFinal(BufferedImage imagem, String nomeArquivo) {
        try {
            File outputfile = new File(nomeArquivo);
            ImageIO.write(imagem, "png", outputfile);
            System.out.println("\nSalvamento concluído: " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar a imagem final: " + e.getMessage());
        }
    }


    private void salvarFrame(BufferedImage imagem, String pasta, int frameIndex) {
        try {
            File outputfile = new File(pasta + "/frame_" + frameIndex + ".png");
            ImageIO.write(imagem, "png", outputfile);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o frame: " + e.getMessage());
        }
    }

    public void preencherComPilha(BufferedImage imagem, int startX, int startY, int corNova, String arquivoSaida, String pastaFrames) {
        int corFundo = imagem.getRGB(startX, startY);
        if (coresParecidas(corFundo, corNova, 10)) {
            System.out.println("A cor selecionada já é a mesma do fundo.");
            return;
        }

        Pilha pilha = new Pilha();
        pilha.empilhar(new Pixel(startX, startY));
        int tolerancia = 80;

        JFrame janela = new JFrame("Animando Flood Fill - PILHA");
        JLabel rotuloImagem = new JLabel(new ImageIcon(imagem));
        janela.add(rotuloImagem);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            int modificacoes = 0;
            int frameCount = 0;

            while (!pilha.estaVazia()) {
                Pixel atual = pilha.desempilhar();
                int x = atual.getX();
                int y = atual.getY();

                if (x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight()) {
                    if (coresParecidas(imagem.getRGB(x, y), corFundo, tolerancia)) {
                        imagem.setRGB(x, y, corNova);
                        modificacoes++;


                        if (modificacoes % 500 == 0) {
                            salvarFrame(imagem, pastaFrames, ++frameCount);
                        }

                        pilha.empilhar(new Pixel(x + 1, y));
                        pilha.empilhar(new Pixel(x - 1, y));
                        pilha.empilhar(new Pixel(x, y + 1));
                        pilha.empilhar(new Pixel(x, y - 1));

                        rotuloImagem.repaint();

                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            salvarFrame(imagem, pastaFrames, ++frameCount);
            salvarImagemFinal(imagem, arquivoSaida);
        }).start();
    }

    public void preencherComFila(BufferedImage imagem, int startX, int startY, int corNova, String arquivoSaida, String pastaFrames) {
        int corFundo = imagem.getRGB(startX, startY);
        if (coresParecidas(corFundo, corNova, 10)) {
            System.out.println("A cor selecionada já é a mesma do fundo.");
            return;
        }

        Fila fila = new Fila();
        fila.enfileirar(new Pixel(startX, startY));
        int tolerancia = 80;

        JFrame janela = new JFrame("Animando Flood Fill - FILA");
        JLabel rotuloImagem = new JLabel(new ImageIcon(imagem));
        janela.add(rotuloImagem);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            int modificacoes = 0;
            int frameCount = 0;

            while (!fila.estaVazia()) {
                Pixel atual = fila.desenfileirar();
                int x = atual.getX();
                int y = atual.getY();

                if (x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight()) {
                    if (coresParecidas(imagem.getRGB(x, y), corFundo, tolerancia)) {
                        imagem.setRGB(x, y, corNova);
                        modificacoes++;

                        if (modificacoes % 500 == 0) {
                            salvarFrame(imagem, pastaFrames, ++frameCount);
                        }

                        fila.enfileirar(new Pixel(x + 1, y));
                        fila.enfileirar(new Pixel(x - 1, y));
                        fila.enfileirar(new Pixel(x, y + 1));
                        fila.enfileirar(new Pixel(x, y - 1));

                        rotuloImagem.repaint();

                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            salvarFrame(imagem, pastaFrames, ++frameCount);
            salvarImagemFinal(imagem, arquivoSaida);
        }).start();
    }
}
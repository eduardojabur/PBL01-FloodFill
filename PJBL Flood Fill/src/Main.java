import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {

        // Try-with-resources: Garante que o BufferedReader será fechado no final, afim de evitar o vazamento de memória
        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in))) {

            File entrada = new File("entrada.png");
            if (!entrada.exists()) {
                System.out.println("Erro: Não encontrei o arquivo 'entrada.png' na pasta raiz do projeto.");
                return;
            }

            BufferedImage imagem = ImageIO.read(entrada);
            FloodFill ff = new FloodFill();

            System.out.println("=== CONFIGURAR FLOOD FILL ===");
            System.out.println("Dimensões da Imagem: " + imagem.getWidth() + "x" + imagem.getHeight());

            System.out.print("Digite a coordenada inicial X: ");
            int startX = Integer.parseInt(leitor.readLine());

            System.out.print("Digite a coordenada inicial Y: ");
            int startY = Integer.parseInt(leitor.readLine());

            System.out.println("\n-- Informe a Nova Cor (Valores de 0 a 255) --");
            System.out.print("Vermelho (R): ");
            int r = Integer.parseInt(leitor.readLine());
            System.out.print("Verde (G): ");
            int g = Integer.parseInt(leitor.readLine());
            System.out.print("Azul (B): ");
            int b = Integer.parseInt(leitor.readLine());

            // Converte o RGB fornecido para ARGB
            int corNova = (255 << 24) | (r << 16) | (g << 8) | b;

            System.out.println("\n-- Escolha a Estrutura --");
            System.out.println("1 - Pilha (Stack)");
            System.out.println("2 - Fila (Queue)");
            System.out.print("Opção: ");
            int escolha = Integer.parseInt(leitor.readLine());

            // Criação das pastas para armazenar a animação
            new File("frames_pilha").mkdirs();
            new File("frames_fila").mkdirs();

            System.out.println("\nIniciando... Preste atenção na janela que vai abrir!");

            if (escolha == 1) {
                ff.preencherComPilha(imagem, startX, startY, corNova, "saida_pilha.png", "frames_pilha");
            } else if (escolha == 2) {
                ff.preencherComFila(imagem, startX, startY, corNova, "saida_fila.png", "frames_fila");
            } else {
                System.out.println("Opção inválida! Escolha 1 ou 2.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro de digitação: Por favor, insira apenas números inteiros válidos.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
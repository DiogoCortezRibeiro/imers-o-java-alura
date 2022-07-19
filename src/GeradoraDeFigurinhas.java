import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws IOException {

        // leitura da imagem
        var imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        var novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem em memoria
        Graphics2D graficos = (Graphics2D) novaImagem.getGraphics();
        graficos.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        graficos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
        graficos.setColor(Color.YELLOW);

        // escrever uma frase na nova imagem
        graficos.drawString("ESSE É BOM DEMAIS", 100, novaAltura - 100);

        // escrever a imagem nova em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        // pegas os dados da API do IMDB
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String filmes = buscarFilmes(url);

        // pegar só os dados que nos interessa (titulo, imagem, classificação)
        List<Map<String, String>> listaDeFilmes = retornarListaFilmes(filmes);

        // exibir e manipular os dados
        listarFilmes(listaDeFilmes);

    }

    public static String buscarFilmes(String url) throws IOException, InterruptedException {
        var uri = URI.create(url);
        var http = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        return http.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public static List<Map<String, String>> retornarListaFilmes(String filmes) {
        var parser = new JsonParser();
        return parser.parse(filmes);
    }

    public static void listarFilmes(List<Map<String, String>> listaDeFilmes) throws IOException {
        var geradoraDeFigurinhas = new GeradoraDeFigurinhas();

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\n");
            System.out.println("\u001b[97m \u001b[104m     Ttitulo: " + filme.get("title") + " \u001b[m");
            System.out.println("       Imagem: " + filme.get("image"));
            Double quantidadeEstrelas = Double.valueOf(Math.floor(Double.valueOf(filme.get("imDbRating"))));
            printarEstrelas(quantidadeEstrelas, filme.get("imDbRating"));

            InputStream inputStream = new URL(filme.get("image")).openStream();
            geradoraDeFigurinhas.cria(inputStream, filme.get("title") + ".png");
        }
    }

    private static void printarEstrelas(Double quantidadeEstrelas, String imDbRating) {
        System.out.print("Classificação: ");
        for (int i = 0; i < quantidadeEstrelas; i++) {
            System.out.print("\u001b[33m \u001b[1m\ud83c\udf1f\u001b[m");
        }
        System.out.print(" " + imDbRating);
    }

}

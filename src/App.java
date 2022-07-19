import java.io.IOException;
import java.net.URI;
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
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("--------------------------");
            System.out.println("         Nome: " + filme.get("title"));
            System.out.println("       Imagem: " + filme.get("image"));
            System.out.println("Classificação: " + filme.get("imDbRating"));
        }

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

}

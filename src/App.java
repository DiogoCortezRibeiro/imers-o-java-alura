import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        // pegas os dados da API do IMDB
        String url    = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String filmes = buscarFilmes(url);

        // pegar só os dados que nos interessa (titulo, imagem, classificação)



        // exibir e manipular os dados

    }

    public static String buscarFilmes(String url) throws IOException, InterruptedException {
        var uri = URI.create(url);
        var http = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        return http.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

}

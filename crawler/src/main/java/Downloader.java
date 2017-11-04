import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.apache.http.HttpHeaders.USER_AGENT;

/**
 * Created by ferney on 26.10.17.
 */
public class Downloader {
    public String download(String url) throws IOException {

        // Создаю объект клиента.
        HttpClient client = HttpClientBuilder.create().build();

        // Создаю объект запроса, передавая ссылку в конструктор.
        HttpGet request = new HttpGet(url);

        //Добавляю заголовок для запроса. Иначе некоторые серверы не станут отвечать.
        request.addHeader("User-Agent", USER_AGENT);

        //Создаю объект ответа, в него помещаю ответ сервера на запрос.
        HttpResponse response = client.execute(request);

        //Отлавливаю ошибки возвращаемые сервером.
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new DownloadException(url, response.getStatusLine().getStatusCode());
        }

        //Обертываю содержимое ответа читателем входящего потока, а потом - буферизованным читателем.
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));



        StringBuffer result = new StringBuffer();
        String line = "";

        while ((line = rd.readLine()) != null) {
            result.append(line + "\n");
        }

        return result.toString();
    }
}

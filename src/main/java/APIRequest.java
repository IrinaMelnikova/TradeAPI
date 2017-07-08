package APIRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class APIRequest<T> {

    private Class<T> curClass;
    public APIRequest(Class<T> curClass)
    {
        this.curClass = curClass;
    }
    public T makeRequest(String url)  {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpUriRequest request = RequestBuilder.get().setUri(new URL(url).toURI()).build();
            HttpResponse response = httpClient.execute(request);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(EntityUtils.toString(response.getEntity()), curClass);
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
            return null;
        } catch (URISyntaxException ue) {
            System.out.println(ue.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Currency eutherum = new Currency();
        APIRequest<Currency> request = new APIRequest<Currency>(Currency.class);
        eutherum = request.makeRequest("https://btc-e.com/api/3/ticker/eth_usd");
    }
}

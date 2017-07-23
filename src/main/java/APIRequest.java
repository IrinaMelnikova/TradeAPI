package APIRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class APIRequest<T> {

    private Class<T> resultClass;
    public APIRequest(Class<T> resultClass)
    {
        this.resultClass = resultClass;
    }
    public T makeRequest(String url)  {
        return convertResponse(doRequest(prepareParams("GET", url)));
    }
    private HttpUriRequest prepareParams(String method, String url) {
        try {
            if(method.toUpperCase().equals("POST")) {
                return RequestBuilder.post().setUri(new URL(url).toURI()).build();
            } else {
                return RequestBuilder.get().setUri(new URL(url).toURI()).build();
            }
        } catch (URISyntaxException ue) {
            System.out.println(ue.getMessage());
        } catch (MalformedURLException me) {
            System.out.println(me.getMessage());
        } return null;
    }
    private HttpResponse doRequest(HttpUriRequest request) {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } return null;
    }
    private T convertResponse(HttpResponse response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(EntityUtils.toString(response.getEntity()), resultClass);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } return null;
    }
    public static void main(String[] args) {
        Currency eutherum = new Currency();
        APIRequest<Currency> request = new APIRequest<Currency>(Currency.class);
        eutherum = request.makeRequest("https://btc-e.com/api/3/ticker/eth_usd");
    }
}

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
    public T makeRequest(String url) throws URISyntaxException, IOException {
        return convertResponse(doRequest(prepareParams("GET", url)));
    }
    private HttpUriRequest prepareParams(String method, String url) throws URISyntaxException, MalformedURLException {
        if(method.toUpperCase().equals(HttpMethods.POST)) {
            return RequestBuilder.post().setUri(new URL(url).toURI()).build();
        } else {
            return RequestBuilder.get().setUri(new URL(url).toURI()).build();
        }
    }
    private HttpResponse doRequest(HttpUriRequest request) throws IOException{
        HttpClient httpClient = HttpClients.createDefault();
        return httpClient.execute(request);
    }
    private T convertResponse(HttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(EntityUtils.toString(response.getEntity()), resultClass);
    }
    public static void main(String[] args) {
        Currency eutherum = new Currency();
        APIRequest<Currency> request = new APIRequest<Currency>(Currency.class);
        try {
            eutherum = request.makeRequest(BtcUrl.GET_ETH);
        } catch(URISyntaxException ue) {
            System.out.println("Wrong URI syntax " + ue.getReason());
        }catch (IOException ie) {
            System.out.println("Received content type doesn`t match JSON Schema or is not JSON " + ie.getMessage());
        }
    }
}

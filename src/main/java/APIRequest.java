package APIRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class APIRequest {

    private Currency currency;
    public APIRequest(Currency currency) {
        this.currency = currency;
    }
    public Currency makeRequest(String request)   {
        try {
            InputStream inputStream = new URL(request).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String result = readAll(reader);
            ObjectMapper mapper = new ObjectMapper();
            currency = mapper.readValue(result, Currency.class);
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        }
        return currency;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder builder = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            builder.append((char) cp);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Currency eutherum = new Currency();
        APIRequest request = new APIRequest(eutherum);
        eutherum = request.makeRequest("https://btc-e.com/api/3/ticker/eth_usd");
    }
}

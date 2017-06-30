import org.json.simple.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class APIRequest {

    private Currency currency;
    public APIRequest(Currency currency) {
        this.currency = currency;
        makeRequest();
    }
    public Currency makeRequest()   {
        try {
            for(int minutes=0; minutes<3; minutes++) {
                InputStream inputStream = new URL("https://btc-e.com/api/3/ticker/eth_usd").openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                String result = readAll(reader);
                org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
                JSONObject ethRate = (JSONObject) parser.parse(result);
                JSONObject rates = (JSONObject) ethRate.get("eth_usd");
                addDotsValues(rates);
                Thread.sleep(5000);
            }
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println("Parsing error");
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        return currency;
    }

    private void addDotsValues (JSONObject rates) {
        currency.addValue(currency.getHigh(),  rates.get("high"));
        System.out.println("Update " + currency.getUpdated());
        currency.addValue(currency.getVol(),  rates.get("vol"));
        currency.addValue(currency.getAvg(),  rates.get("avg"));
        currency.addValue(currency.getVolCur(),  rates.get("vol_cur"));
        currency.addValue(currency.getLast(),  rates.get("last"));
        currency.addValue(currency.getLow(),  rates.get("low"));
        currency.addValue(currency.getBuy(),  rates.get("buy"));
        currency.addValue(currency.getSell(),  rates.get("sell"));
        currency.addValue(currency.getUpdated(),  rates.get("updated"));
    }

    private static String readAll(Reader rd) throws IOException {
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
        request.makeRequest();
    }
}

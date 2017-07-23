package APIRequest;

import static io.restassured.RestAssured.get;
import io.restassured.http.ContentType;
import org.junit.Test;

public class APIRequestTest {
    @Test
    public void makeSureBTCIsUp() throws Exception {
        get("https://btc-e.com/api").then().statusCode(200);
    }
    @Test
    public void makeSureContentTypeIsJson() throws Exception {
        get("https://btc-e.com/api/3/ticker/eth_usd").then().assertThat().contentType(ContentType.JSON);
    }
    @Test
    public void checkRequestMethodReturnedClass() throws Exception {
        APIRequest<Currency> request = new APIRequest<Currency>(Currency.class);
        assert(request.makeRequest("https://btc-e.com/api/3/ticker/eth_usd").getClass().equals(Currency.class));
    }
    /* JSON Schema validation will be implementing later
    @Test
    public void makeSureToRetrieveAppropriateRates() throws Exception {
    }
    */
}
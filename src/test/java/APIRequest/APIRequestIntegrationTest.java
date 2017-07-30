package APIRequest;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import static io.restassured.RestAssured.get;

public class APIRequestIntegrationTest {
    @Test
    public void makeSureBTCIsUp() throws Exception {
        get(BtcUrl.GET_ETH).then().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void makeSureBTCContentTypeIsJSON() throws Exception {
        get(BtcUrl.GET_ETH).then().contentType(ContentType.JSON);
    }
    /*
    JSON Schema validation will be implementing later due to btc-e.com is down.

    @Test
    public void makeSureBTCAnswerSuitsJSONSchema() throws Exception {
    }
    */
}
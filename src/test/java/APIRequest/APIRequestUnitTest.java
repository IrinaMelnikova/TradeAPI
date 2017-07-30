package APIRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(MockitoJUnitRunner.class)
public class APIRequestUnitTest {

    private Currency etherum;
    private Rates rates;

    @Mock
    private APIRequest<Currency> request;

    @Before
    public void setUp() {
        etherum = new Currency();
        rates = new Rates();
        rates.setVol_cur(11.1);
        rates.setVol(11.1);
        rates.setUpdated(11.1);
        rates.setSell(11.1);
        rates.setLow(11.1);
        rates.setLast(11.1);
        rates.setHigh(11.1);
        rates.setBuy(11.1);
        rates.setAvg(11.1);
        etherum.setEth_usd(rates);
    }

    @Test
    public void checkValidAnswerProcessing() throws Exception {
        when(request.makeRequest(BtcUrl.GET_ETH)).thenReturn(etherum);
        assertEquals(request.makeRequest(BtcUrl.GET_ETH), etherum);
    }

    @Test
    public void checkInvalidAnswerTypeProcessing() throws Exception {
        String validSource = "https://google.com";
        when(request.makeRequest(validSource)).thenThrow(new IOException());
        try {
            request.makeRequest(validSource);
        } catch (IOException ie) {
            System.out.println("Received content type doesn`t match JSON Schema or is not JSON " + ie.getMessage());
        }
    }

    @Test
    public void checkAnswerProcessingFromInvalideSource() throws Exception {
        String invalidSource = "https://invalid.source.com";
        when(request.makeRequest(invalidSource)).thenThrow(new URISyntaxException(invalidSource, "Invalid source syntax"));
        try {
            request.makeRequest(invalidSource);
        } catch (URISyntaxException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
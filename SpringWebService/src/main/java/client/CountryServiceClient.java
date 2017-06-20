package client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.phh.gs_producing_web_service.GetTestCountryRequest;
import com.phh.gs_producing_web_service.GetTestCountryResponse;

public class CountryServiceClient extends WebServiceGatewaySupport {
    public GetTestCountryResponse getCountryDetails(String country){
        String uri = "http://localhost:8080/ws/countries.wsdl";
        GetTestCountryRequest request = new GetTestCountryRequest();
        request.setName(country);

        GetTestCountryResponse response =(GetTestCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, request);
        return response;
    }
}
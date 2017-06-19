package hello;

import com.phh.gs_producing_web_service.GetTestCountryRequest;
import com.phh.gs_producing_web_service.GetTestCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://com/phh/gs-producing-web-service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTestCountryRequest")
	@ResponsePayload
	public GetTestCountryResponse getCountry(@RequestPayload GetTestCountryRequest request) {
		GetTestCountryResponse response = new GetTestCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}

package client;

import com.phh.gs_producing_web_service.GetTestCountryResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


public class MainApp {
    public static void main(String[] args) {
        CountryServiceClient client = new CountryServiceClient();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.phh.gs_producing_web_service");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        GetTestCountryResponse response = client.getCountryDetails("Poland");

        System.out.println("Country : " + response.getCountry().getName());
        System.out.println("Capital : " + response.getCountry().getCapital());
        System.out.println("Population : " + response.getCountry().getPopulation());
        System.out.println("Currency : " + response.getCountry().getCurrency());
        System.out.println("GDP : " + response.getCountry().getGdp());
    }
}
package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

public class PCAPI {	

	public String PC2JSON(String postCode)
	{
		Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		WebTarget webResource;
		webResource = client.target("api.postcodes.io/postcodes/" + postCode);
		String response = webResource.request(MediaType.TEXT_PLAIN).get(String.class);
		return response;
	}
}


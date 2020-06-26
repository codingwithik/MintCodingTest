package com.mint.cardservice.binlistapi;

import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import org.junit.runner.RunWith;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BinListApi {
	
	@Test
	public void getCardDetailsFromBinListApi() throws ClientProtocolException, IOException{
		
		// Given
	    String accountNumber = "45717360";
	    HttpUriRequest request = new HttpGet( "https://lookup.binlist.net/" + accountNumber );
	 
	    // When
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	    // Then
	    Assert.assertThat(
	      httpResponse.getStatusLine().getStatusCode(),
	      equalTo(200));
	    
	    Assert.assertNotNull(request);
	}
	

}

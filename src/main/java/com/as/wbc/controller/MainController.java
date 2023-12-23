package com.as.wbc.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.as.wbc.model.ImageModel;
import com.as.wbc.model.SearchBar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MainController {

	private final ImageModel imageModel;

	@Autowired
	public MainController(ImageModel imageModel) {
		this.imageModel = imageModel;
	}

	@GetMapping("/welcome")
	public String Welcome() {
		return "welcomepage";
	}

	@PostMapping("/search")
	public String searchCountry(@ModelAttribute SearchBar sb, Model model) throws IOException, InterruptedException {
		
		 List<ImageModel> countries = new ArrayList<>();
		    var countrycurrencyUrl = "https://restcountries.com/v3.1/currency/" + sb.getCurrencyName();
		    var currencyRequest = HttpRequest.newBuilder().GET().uri(URI.create(countrycurrencyUrl)).build();

		    var currencyClient = HttpClient.newBuilder().build();
		    var currencyResponse = currencyClient.send(currencyRequest, HttpResponse.BodyHandlers.ofString());
		    var currencyResponseBody = currencyResponse.body();

		    ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode jsonNode = objectMapper.readTree(currencyResponseBody);

		    for (JsonNode record : jsonNode) {
		        ImageModel imageModel = new ImageModel(); 

		        String countryName = record.at("/name/common").asText();
		        String countryCode = record.at("/cca2").asText();

		        JsonNode capitalNode = record.at("/capital");
		        String capitalName = "";

		        if (capitalNode.isArray()) {
		            for (JsonNode capital : capitalNode) {
		                capitalName = capital.asText();
		                break;
		            }
		        }

		        imageModel.setCountryCapital(capitalName);
		        imageModel.setCountryName(countryName);
		        imageModel.setCountryCode(countryCode);

		        var countryFlagUrl = "https://flagsapi.com/" + countryCode + "/shiny/64.png";
		        imageModel.setCountryImage(countryFlagUrl);

		        countries.add(imageModel);
		    }

		    model.addAttribute("countries", countries);
		   
		    return "welcomepage";
	}

}

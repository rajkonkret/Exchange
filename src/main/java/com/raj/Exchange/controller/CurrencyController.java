package com.raj.Exchange.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.Exchange.model.*;
import com.raj.Exchange.repository.LogingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.security.auth.login.LoginContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class CurrencyController {

    // @GetMapping(value="currency", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    //@Autowired
    //LogTable logTable= new LogTable();
    @CrossOrigin
    @GetMapping("exchange")
    public List<UsdToSearch> getCurrency() throws IOException {
        String url = "https://api.nbp.pl/api/exchangerates/tables/A/?format=json";
        RestTemplate template = new RestTemplate();
        //Joke joke = template.getForObject("https://api.chucknorris.io/jokes/random", Joke.class);
//
        List<UsdToSearch> gold = template.exchange(url,
                HttpMethod.GET,
                null, new ParameterizedTypeReference<List<UsdToSearch>>() {
                }).getBody();
        //System.out.println(httpRequest);

        return gold;
//        ObjectMapper objectMapper = new ObjectMapper();
//        UsdToSearch[] listCurencies = (template.getForEntity(url,UsdToSearch[].class)).getBody();
//        List<UsdToSearch> forObject = objectMapper.readValue(listCurencies.toString(),
//                new TypeReference<List<UsdToSearch>>() {
//        });
//         return forObject.get(0).getRates();
//        List<Object> response = gold.getBody().stream()
//                .map(UsdToSearch::getRates)
//                .collect(Collectors.<Object>toList());
//        response.forEach( s -> System.out.println(s));
       // return forObject;
//        try {
//            URL link = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            JSONParser parser = new JSONParser();
//            JSONArray jsonObject = (JSONArray) parser.parse(bufferedReader);
//            JSONObject listCurrency = (JSONObject) jsonObject.get(0);
//            JSONArray  listJsonCurrency = (JSONArray) listCurrency.get("rates");
//
//            return listJsonCurrency;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @PostMapping("exchange")
    public Double exchange(@RequestBody ToChange toChange) {
        Usd firstMid = new Usd();
        Usd secondMid = new Usd();
        Rate rate = new Rate();
        rate.setMid(1.00);
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);

        RestTemplate template = new RestTemplate();

        if (!toChange.getFirst().equalsIgnoreCase("pln")) {
            firstMid = template.getForObject("https://api.nbp.pl/api/exchangerates/rates/a/"
                    + toChange.getFirst(), Usd.class);
        } else firstMid.setRates(rates);

        if (!toChange.getSecond().equalsIgnoreCase("pln")) {
            secondMid = template.getForObject("https://api.nbp.pl/api/exchangerates/rates/a/"
                    + toChange.getSecond(), Usd.class);
        } else secondMid.setRates(rates);

        return toChange.getValue() * firstMid.getRates().

                get(0).

                getMid() / secondMid.getRates().

                get(0).

                getMid();

    }


}

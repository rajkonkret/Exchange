package com.raj.Exchange.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.raj.Exchange.model.Gold;
import com.raj.Exchange.model.Rate;
import com.raj.Exchange.model.Usd;
import com.raj.Exchange.model.ToChange;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CurrencyController {

    // @GetMapping(value="currency", produces = MediaType.APPLICATION_JSON_VALUE)
   // @CrossOrigin
    @GetMapping("exchange")
    @ResponseBody
    public ResponseEntity<List<Usd>> getCurrency() {
        RestTemplate template = new RestTemplate();
        //Joke joke = template.getForObject("https://api.chucknorris.io/jokes/random", Joke.class);
        ResponseEntity<List<Usd>> gold =
                template.exchange("https://api.nbp.pl/api/exchangerates/tables/B/?format=json",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Usd>>() {
                        });
//        // Usd usd = template.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/usd", Usd.class);
//
//        //System.out.println(gold.getBody().get(0).getCurrency().toString());
//        // return new JSONObject.SimpleEntry<Integer, Usd>(0, usd);
//        //listCurrenies.forEach( s -> System.out.println(s.getCurrency()));
//        RestTemplate template = new RestTemplate();
//        ResponseEntity<List<Usd>> exchangeList = template.exchange(
//                "http://api.nbp.pl/api/exchangerates/tables/a/?format=json",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Usd>>(){});

        return  gold;
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
        } else  secondMid.setRates(rates);

        return toChange.getValue() * firstMid.getRates().

                get(0).

                getMid() / secondMid.getRates().

                get(0).

                getMid();

    }


}

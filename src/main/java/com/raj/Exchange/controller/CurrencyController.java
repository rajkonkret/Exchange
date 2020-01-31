package com.raj.Exchange.controller;

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
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CurrencyController {

    // @GetMapping(value="currency", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    // @ResponseBody

    public ResponseEntity<List<Usd>> getCurrency() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Usd>> listCurrenies =
                template.exchange("http://api.nbp.pl/api/exchangerates/tables/a",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Usd>>() {
                });
        //listCurrenies.forEach( s -> System.out.println(s.getCurrency()));
        // Usd usd = template.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/usd", Usd.class);

        //System.out.println(gold.getBody().get(0).getCurrency().toString());
        // return new JSONObject.SimpleEntry<Integer, Usd>(0, usd);
        return  listCurrenies;
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
            firstMid = template.getForObject("http://api.nbp.pl/api/exchangerates/rates/a?format=json"
                    + toChange.getFirst(), Usd.class);
        } else firstMid.setRates(rates);

        if (!toChange.getSecond().equalsIgnoreCase("pln")) {
            secondMid = template.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/"
                    + toChange.getSecond(), Usd.class);
        } else  secondMid.setRates(rates);

        return toChange.getValue() * firstMid.getRates().

                get(0).

                getMid() / secondMid.getRates().

                get(0).

                getMid();

    }


}

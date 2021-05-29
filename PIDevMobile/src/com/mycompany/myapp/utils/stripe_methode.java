/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amine
 */
public class stripe_methode {

    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_51IjY3IGuzidhY6plUmvlby584mxsTL1sFaHEOSsBLr0HaLsauaHktFvIOaA8o8cEOiDxyQOujJTLMQevF7rbTGOO00AiWiR4QW";

    public stripe_methode() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
    }

    public String makecus(String name, String email) throws StripeException {
        String id = null;
        Map<String, Object> params = new HashMap<>();
        params.put(
                "email",
                email
        );
        params.put("name",
                name);
        System.out.println(params);
        Customer customer = Customer.create(params);
        id = customer.getId();
        return id;

    }

    public void makecard(String id, String number, String exp_month, String exp_year, String cvc) throws StripeException {
        Map<String, Object> retrieveParams
                = new HashMap<>();
        List<String> expandList = new ArrayList<>();
        expandList.add("sources");
        retrieveParams.put("expand", expandList);
        Customer customer
                = Customer.retrieve(
                        id,
                        retrieveParams,
                        null
                );

        Map< String, Object> cardParam = new HashMap< String, Object>();
        cardParam.put("number", number);
        cardParam.put("exp_month", exp_month);
        cardParam.put("exp_year", exp_year);
        cardParam.put("cvc", cvc);

        Map< String, Object> tokenParam = new HashMap< String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam);
        Map< String, Object> source = new HashMap< String, Object>();
        source.put("source", token.getId());

        customer.getSources().create(source);
        System.out.println("card created");
    }/*
     cardParam.put("number", "4242424242424242");
        cardParam.put("exp_month", "03");
        cardParam.put("exp_year", "2022");
        cardParam.put("cvc", "577");
     */
    public void payer(int amount, String id) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");
        params.put(
                "customer",
                id
        );

        Charge.create(params);
    }
}

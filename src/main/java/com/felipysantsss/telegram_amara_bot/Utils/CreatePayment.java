package com.felipysantsss.telegram_amara_bot.Utils;

import com.felipysantsss.telegram_amara_bot.enums.Plans;
import com.mercadopago.client.order.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.order.Order;

import java.util.*;

public class CreatePayment {
    public static Order paymentGenerator(Plans chosenPlan, String syntheticEmail, String chatIdRef) throws MPException, MPApiException{
        OrderClient client = new OrderClient();

        // cria o método de pagamento
        OrderPaymentMethodRequest paymentMethod = OrderPaymentMethodRequest
                .builder()
                .id("pix")
                .type("bank_transfer")
                .build();

        // cria o pedido de pagamento de acordo com o método
        OrderPaymentRequest payment = OrderPaymentRequest
                .builder()
                .amount(chosenPlan.getPrice().toString())
                .paymentMethod(paymentMethod)
                .build();

        // cria a lista de pagamentos
        List<OrderPaymentRequest> payments = new ArrayList<>();
        payments.add(payment);

        // teste de comprador
        OrderPayerRequest payer = OrderPayerRequest.builder()
                .email(syntheticEmail)
                .firstName("APRO")
                .build();

        // criação do pedido
        OrderCreateRequest request = OrderCreateRequest
                .builder()
                .type("online")
                .description(chosenPlan.getText())
                .totalAmount(chosenPlan.getPrice().toString())
                .externalReference(chatIdRef)
                .payer(payer)
                .transactions(OrderTransactionRequest.builder()
                        .payments(payments)
                        .build())
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Idempotency-key", UUID.randomUUID().toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(headers)
                .build();

        Order order = client.create(request, requestOptions);
        return order;
    }
}

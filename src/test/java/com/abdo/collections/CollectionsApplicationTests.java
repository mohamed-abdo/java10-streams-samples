package com.abdo.collections;

import domain.Payment;
import domain.Refund;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionsApplicationTests {

    private static List<Payment> payments;
    private static List<Refund> refunds;

    //setup values.
    static {
        payments = List.of(
                new Payment("CASH", Map.ofEntries(
                        Map.entry("B1", BigDecimal.valueOf(10)),
                        Map.entry("B2", BigDecimal.valueOf(10)))),
                new Payment("CREDIT", Map.ofEntries(
                        Map.entry("B3", BigDecimal.valueOf(20.0)),
                        Map.entry("B4", BigDecimal.valueOf(30.0)))),
                new Payment("BANK", Map.ofEntries(
                        Map.entry("B1", BigDecimal.valueOf(50.0)),
                        Map.entry("B4", BigDecimal.valueOf(20.0)))),
                new Payment("CASH", Map.ofEntries(
                        Map.entry("B4", BigDecimal.valueOf(10.0)),
                        Map.entry("B3", BigDecimal.valueOf(20.0))))
        );
        refunds = List.of(
                new Refund("CASH", Map.ofEntries(
                        Map.entry("B1", BigDecimal.valueOf(50)),
                        Map.entry("B3", BigDecimal.valueOf(40)))),
                new Refund("CASH", Map.ofEntries(
                        Map.entry("B2", BigDecimal.valueOf(20.0)),
                        Map.entry("B3", BigDecimal.valueOf(20.0)))),
                new Refund("OTHER", Map.ofEntries(
                        Map.entry("B1", BigDecimal.valueOf(10.0)),
                        Map.entry("B2", BigDecimal.valueOf(20.0))
                )));
    }

    @Test
    public void collectionJoinTest() {
        var getPaymentBeneficiaries = payments.stream()
                .collect(groupingBy(Payment::getType))
                .entrySet()
                .stream()
                .map(p -> Map.entry(p.getKey(),
                        p.getValue().stream()
                                .flatMap(f -> f.getBeneficiaries().keySet().stream())
                                .collect(Collectors.toList())
                )).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var refundList =
                refunds.stream()
                        .filter(r ->
                                getPaymentBeneficiaries.containsKey(r.getType())
                                        &&
                                        getPaymentBeneficiaries.get(r.getType())
                                                .containsAll(r.getBeneficiaries().keySet()))
                        .collect(toList());

        assertTrue("refund list should have items", refundList.size() >= 1);
    }

}

package domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

@Entity
@IdClass(Id.class)
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long Id;
    private final String Type;
    private final Map<String,BigDecimal> Beneficiaries;

    public long getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public Map<String, BigDecimal> getBeneficiaries() {
        return Beneficiaries;
    }

    public Payment(String type, Map<String, BigDecimal> beneficiaries) {
        Type = type;
        Beneficiaries = beneficiaries;
    }
}

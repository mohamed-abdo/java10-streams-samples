package domain;

import javax.persistence.*;
import javax.swing.text.Position;
import java.math.BigDecimal;
import java.sql.Ref;
import java.util.Map;

@Entity
@IdClass(Id.class)
public class Refund {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long Id;
    private final String Type;
    private final Map<String,BigDecimal> Beneficiaries;

    public Refund(String type, Map<String, BigDecimal> beneficiaries) {
        Type = type;
        Beneficiaries = beneficiaries;
    }

    public long getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public Map<String, BigDecimal> getBeneficiaries() {
        return Beneficiaries;
    }
}

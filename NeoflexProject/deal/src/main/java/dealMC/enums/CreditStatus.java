package dealMC.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CreditStatus {
    @JsonProperty("CALCULATED")
    CALCULATED,
    @JsonProperty("ISSUED")
    ISSUED
}

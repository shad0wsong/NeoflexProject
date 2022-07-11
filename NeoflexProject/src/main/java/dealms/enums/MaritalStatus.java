package dealms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MaritalStatus {
    @JsonProperty("MARRIED")
    MARRIED("MARRIED"),
    @JsonProperty("DIVORCED")
    DIVORCED("DIVORCED"),
    @JsonProperty("SINGLE")
    SINGLE("SINGLE"),
    @JsonProperty("WIDOW_WIDOWED")
    WIDOW_WIDOWED("WIDOW_WIDOWED");

    private String name;

    MaritalStatus(String name) {
        this.name = name;
    }

    MaritalStatus() {
    }

    @JsonValue
    public String getName() {
        return name;
    }
}

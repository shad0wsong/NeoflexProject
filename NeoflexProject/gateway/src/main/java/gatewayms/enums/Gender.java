package gatewayms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    @JsonProperty("MALE")
    MALE("MALE"),
    @JsonProperty("FEMALE")
    FEMALE("FEMALE"),
    @JsonProperty("NON_BINARY")
    NON_BINARY("NON_BINARY");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    Gender() {
    }

    @JsonValue
    public String getName() {
        return name;
    }
}

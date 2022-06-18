package dealms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmploymentStatus {
    @JsonProperty("UNEMPLOYED")
    UNEMPLOYED("UNEMPLOYED"),
    @JsonProperty("EMPLOYED")
    EMPLOYED("EMPLOYED"),
    @JsonProperty("SELFEMPLOYED")
    SELFEMPLOYED("SELFEMPLOYED"),
    @JsonProperty("BUSINESSOWNER")
    BUSINESSOWNER("BUSINESSOWNER");

    private String name;

    EmploymentStatus(String name) {
        this.name = name;
    }

    EmploymentStatus() {
    }

    @JsonValue
    public String getName() {
        return name;
    }
}

package conveyorMC.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
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


    @JsonCreator
    public static EmploymentStatus fromText(String text) {
        for (EmploymentStatus r : EmploymentStatus.values()) {
            if (r.getName().equals(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return name;
    }
}

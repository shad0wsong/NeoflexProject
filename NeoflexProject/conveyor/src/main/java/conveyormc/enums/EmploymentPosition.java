package conveyormc.enums;


import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmploymentPosition {
    @JsonProperty("MIDDLE_MANAGER")
    MIDDLE_MANAGER("MIDDLE_MANAGER"),

    @JsonProperty("TOP_MANAGER")
    TOP_MANAGER("TOP_MANAGER"),

    @JsonProperty("OWNER")
    OWNER("OWNER"),

    @JsonProperty("WORKER")
    WORKER("WORKER");
    private String name;

    EmploymentPosition(String name) {
        this.name = name;
    }

    EmploymentPosition() {
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static EmploymentPosition fromText(String text) {
        for (EmploymentPosition r : EmploymentPosition.values()) {
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

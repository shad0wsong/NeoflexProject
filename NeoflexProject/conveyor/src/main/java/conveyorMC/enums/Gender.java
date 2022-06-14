package conveyormc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    @JsonProperty("MALE")
    MALE("MALE"),
    @JsonProperty("FEMALE")
    FEMALE("FEMALE"),
    @JsonProperty("NON_BINARY")
    NON_BINARY("NON_BINARY");

    String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    Gender() {
    }

    @JsonValue
    public String getGender() {
        return gender;
    }

    @JsonCreator
    public static Gender fromText(String text) {
        for (Gender r : Gender.values()) {
            if (r.getGender().equals(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return gender;
    }
}

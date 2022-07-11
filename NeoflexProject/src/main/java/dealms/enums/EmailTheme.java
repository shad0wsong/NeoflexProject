package dealms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EmailTheme {
    @JsonProperty("FINISH_REGISTRATION")
    FINISH_REGISTRATION,
    @JsonProperty("CREATE_DOCUMENTS")
    CREATE_DOCUMENTS,
    @JsonProperty("SEND_DOCUMENTS")
    SEND_DOCUMENTS,
    @JsonProperty("SEND_SES")
    SEND_SES,
    @JsonProperty("CREDIT_ISSUED")
    CREDIT_ISSUED,
    @JsonProperty("DOCUMENT_CREATED")
    DOCUMENT_CREATED,
    @JsonProperty("APPLICATION_DENIED")
    APPLICATION_DENIED,
}

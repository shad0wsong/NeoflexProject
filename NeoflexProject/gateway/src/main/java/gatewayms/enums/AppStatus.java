package gatewayms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AppStatus {
    @JsonProperty("PREAPPROVAL")
    PREAPPROVAL,
    @JsonProperty("APPROVED")
    APPROVED,
    @JsonProperty("CC_DENIED")
    CC_DENIED,
    @JsonProperty("CC_APPROVED")
    CC_APPROVED,
    @JsonProperty("PREPARE_DOCUMENTS")
    PREPARE_DOCUMENTS,
    @JsonProperty("DOCUMENT_CREATED")
    DOCUMENT_CREATED,
    @JsonProperty("CLIENT_DENIED")
    CLIENT_DENIED,
    @JsonProperty("DOCUMENT_SIGNED")
    DOCUMENT_SIGNED,
    @JsonProperty("CREDIT_ISSUED")
    CREDIT_ISSUED
}

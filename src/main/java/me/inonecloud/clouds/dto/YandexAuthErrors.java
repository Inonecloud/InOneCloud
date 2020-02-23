package me.inonecloud.clouds.dto;

public enum YandexAuthErrors {
    AUTHORIZATION_PENDING("authorization_pending"),
    BAD_VERIFICATION_CODE("bad_verification_code"),
    INVALID_CLIENT("invalid_client"),
    INVALID_GRANT("invalid_grant"),
    INVALID_REQUEST("invalid_request"),
    INVALID_SCOPE("invalid_scope"),
    UNAUTHORIZED_CLIENT("unauthorized_client"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),
    BASIC_AUTH_REQUIRED("Basic auth required"),
    MALFORMED_AUTHORIZATION_HEADER("Malformed Authorization header");

    private String errorCode;

    YandexAuthErrors(String errorCode) {
        this.errorCode = errorCode;
    }

}

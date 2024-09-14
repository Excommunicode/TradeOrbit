package kz.ozon.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String DATA_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ROLE_USER = "USER";
    public static final int ACCESS_TOKEN_EXPIRATION_MINUTES = 3;
    public static final int REFRESH_TOKEN_EXPIRATION_DAYS = 31;
}
package kz.ozon.dto;

import lombok.Data;

@Data
public class RefreshJwtRequest {
    private String refreshToken;
}

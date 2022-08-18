package com.iknow.stocktrackingbe.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class RefreshTokenRequest {
    private String token;
}

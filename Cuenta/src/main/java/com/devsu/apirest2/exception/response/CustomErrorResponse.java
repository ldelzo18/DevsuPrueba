package com.devsu.apirest2.exception.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private String error;
    private String message;
}

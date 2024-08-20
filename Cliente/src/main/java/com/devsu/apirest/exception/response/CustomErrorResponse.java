package com.devsu.apirest.exception.response;

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

package com.example.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseBodyApi<T> {
    private String responseCode;
    private String responseDescription;
    private T details;

}

package com.example.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int id;
    @Size(min = 3, max = 50)
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

}

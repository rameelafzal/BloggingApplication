package com.example.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{1,50}$",message = "Category title must be smaller than 50 and should not contain special characters")
    private String categoryTitle;
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,50}$",message = "Category Description must be smaller than 50 and should not contain special characters")
    private String categoryDescription;

}

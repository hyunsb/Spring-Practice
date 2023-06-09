package com.example.developermaker.dto;

import com.example.developermaker.exception.DeveloperMakerErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperMakerErrorResponse {

    private DeveloperMakerErrorCode developerMakerErrorCode;
    private String errorMessage;
}

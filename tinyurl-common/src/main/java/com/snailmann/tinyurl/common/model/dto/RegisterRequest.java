package com.snailmann.tinyurl.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwenjie
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String address;

    private Boolean allowRepeatRegistration = true;

    private Long ttl = 3600L;

}

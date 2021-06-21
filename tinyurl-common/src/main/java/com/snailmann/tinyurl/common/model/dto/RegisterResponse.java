package com.snailmann.tinyurl.common.model.dto;

import lombok.*;

/**
 * @author liwenjie
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private int code;

    private String tinyUrl;

    public static RegisterResponse ok(String tinyUrl) {
        return RegisterResponse.builder().code(Code.OK.getCode()).tinyUrl(tinyUrl).build();
    }

    public static RegisterResponse error() {
        return RegisterResponse.builder().code(Code.ERROR.getCode()).tinyUrl(null).build();
    }


    public enum Code {

        OK(10000, "OK"),
        ERROR(99999, "Error"),
        ;

        @Getter
        int code;
        @Getter
        String desc;

        Code(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}

package com.example.tushu.mode.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class loginvo {

    private String account;
    private String password;


}

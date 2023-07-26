package com.prog4.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModelUSer {
    private String username;
    private String password;
    private boolean enabled = true;
}

package com.hutech.demo;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Roles {
    ADMIN(1),
    EMPLOYEE(2),
    CUSTOMER(3);

    public final long value;
}
package com.mohai.one.springbootdatarest.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Auther: moerhai@qq.com
 * @Date: 2020/7/22 01:07
 */
@Entity
@Data
@RequiredArgsConstructor
public class AddressDTO {

    @GeneratedValue
    @Id
    private Long id;
    private final String street, zipCode, city, state;

    AddressDTO() {
        this.street = null;
        this.zipCode = null;
        this.city = null;
        this.state = null;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s", street, zipCode, city, state);
    }

}

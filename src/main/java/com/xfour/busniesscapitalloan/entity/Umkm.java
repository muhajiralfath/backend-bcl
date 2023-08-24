package com.xfour.busniesscapitalloan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_umkm")
public class Umkm extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "no_siup", unique = true)
    private String noSiup;
    @Column(name = "address")
    private String address;
    @Column(name = "capital")
    private Long capital;
    @Column(name = "umkm_type")
    private String umkmType;
    @Column(name = "bank_account")
    private String bankAccount;
}

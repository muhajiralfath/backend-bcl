package com.xfour.businesscapitalloan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_payment")
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "umkm_id")
    private Umkm umkm;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "bill_id", unique = true)
    private Bill bill;

    @Column(name = "amountpay_id")
    private Long amount;

    @Column(name = "snap_url")
    private String snapUrl;

    @Column(name = "snap_token")
    private String snapToken;
}

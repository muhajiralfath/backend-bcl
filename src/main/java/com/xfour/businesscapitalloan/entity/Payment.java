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

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "umkmpay_id")
    private Umkm umkm;

    @OneToOne
    @JoinColumn(name = "billpay_id")
    private Bill bill;

    @Column(name = "amountpay_id")
    private Long amount;

}

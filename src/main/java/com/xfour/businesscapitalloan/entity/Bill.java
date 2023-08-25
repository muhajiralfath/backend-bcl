package com.xfour.businesscapitalloan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_bill")
public class Bill extends BaseEntity {

    @Column(name = "amount")
    private Long amount;

    @Column(name = "interest")
    private Integer interest;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "provision_id")
    private com.xfour.businesscapitalloan.entity.Provision provision;

    @ManyToOne
    @JoinColumn(name = "umkm_id")
    private Umkm umkm;
}

package com.xfour.businesscapitalloan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_bill")
public class Bill extends BaseEntity {

    @Column(name = "debt")
    private Long debt;

    @Column(name = "interest")
    private Integer interest;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "is_verify")
    private Boolean isVerify;

    @ManyToOne
    @JoinColumn(name = "provision_id")
    private Provision provision;

    @ManyToOne
    @JoinColumn(name = "umkm_id")
    private Umkm umkm;
}

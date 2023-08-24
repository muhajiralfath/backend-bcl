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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_submission")
public class Submission extends BaseEntity{

    private String umkmId;

    @Column(name = "tenor")
    private Integer tenor;

    @Column(name = "loan_amount")
    private Long loanAmount;

    @Column(name = "is_approve")
    private Boolean isApprove;
}

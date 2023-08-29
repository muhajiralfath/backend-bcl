package com.xfour.businesscapitalloan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "t_submission")
public class Submission extends BaseEntity{

    @Column(name = "tenor")
    private Integer tenor;

    @Column(name = "loan_amount")
    private Long loanAmount;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @ManyToOne
    @JoinColumn(name = "umkm_id")
    private Umkm umkm;

    @JsonManagedReference
    @OneToOne(mappedBy = "submission", fetch = FetchType.LAZY)
    private Provision provision;
}

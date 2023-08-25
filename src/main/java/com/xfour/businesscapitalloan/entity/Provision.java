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
@Table(name = "t_provision")
public class Provision extends BaseEntity{

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "submission_id")
    private Submission submission;
}

package com.xfour.businesscapitalloan.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "m_admin")
public class Admin extends BaseEntity{
    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserCredential userCredential;
}

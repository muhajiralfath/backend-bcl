package com.xfour.businesscapitalloan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "m_umkm_document")
public class UmkmDocument extends File{

    @OneToOne
    @JoinColumn(name = "umkm_id", referencedColumnName = "id")
    private Umkm umkm;
}

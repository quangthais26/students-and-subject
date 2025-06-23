package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sinhvien")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column (length = 100)
        private  String ten;
        private String tuoi;
        @Column (length = 15)
        private String sodienthoai;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}

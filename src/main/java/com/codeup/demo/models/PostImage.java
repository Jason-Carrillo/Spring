//package com.codeup.demo.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "ad_images")
//public class PostImage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY);
//    private long id;
//
//    @Column(nullable = false)
//    private String path;
//
//    @OneToOne
//    @JoinColumn(nullable = false)
//    private Post post;
//
//    public PostImage(long id, String path, Post post){
//        this.id = id;
//        this.path = path;
//        this.post = post;
//    }
//
//    public AdImage(String path, Post post){
//        this.path = path;
//        this.post = post;
//    }
//}

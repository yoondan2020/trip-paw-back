package com.trippaw.place;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name="place"
        )
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id", unique = true)
    private String contentId;

    @Column(name = "content_type_id")
    private int contentTypeId;

    private String name;

    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "modified_time")
    private String modifiedTime;

    private String tel;

    private String homepage;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    private int areacode;
    private int sigungucode;

    private double mapx;
    private double mapy;

    private String overview;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

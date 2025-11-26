package com.dynii.doplyshop.item.entity;

import com.dynii.doplyshop.item.dto.ItemRead;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter // 모든 필드의 Getter를 자동으로 생성해주는 annotation
@Entity // JAP에 의해 관리되는 Entity임을 나타내는 annotation
@Table(name = "items") // 매핑된 데이터베이스 테이블(gallery 스키마의 items 테이블)을 지정
public class Item {

    @Id // 아이디에 해당하는 필드, 해당 필드가 기본키이며 테이블의 기본키 컬럼과 매핑됨을 나타냄
        // 기본키의 값을 데이터베이스가 자동으로 증가시켜 생성하도록 @GeneratedValue와
        // 기본키 생성 전략을 GenerationType.IDENTITY로 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String artist;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPer;

    // 생성일시 필드, null 허용 안함, 최초값 입력 후 수정 불가
    @Column(updatable = false, nullable = false)
    @CreationTimestamp // 데이터 삽입 시 값이 없다면 현재 시간을 입력
    private LocalDateTime created;

    // 상품 조회 DTO 변환
    // Entity 객체를 상품 조회 DTO로 변환하는 메서드
    // 빌드 활용하여 필드 값 간편 기입 및 초기화
    public ItemRead toRead() {
        return ItemRead.builder()
            .id(id)
            .name(name)
            .artist(artist)
            .description(description)
            .imgPath(imgPath)
            .price(price)
            .discountPer(discountPer).build();

    }
}

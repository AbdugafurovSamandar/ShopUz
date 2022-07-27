package com.company.entity;


import com.company.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long price;

    @Column
    private  Integer inStock;

    @Column
    private Integer discount;

    @Column
    private Long pay;

    @Column
    private String advertisement;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "category_id")
    private Integer categoryId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;


    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDateTime publishedDate;


}

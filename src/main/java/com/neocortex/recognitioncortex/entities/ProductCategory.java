package com.neocortex.recognitioncortex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "categoryName"
        })
})
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    @NotNull
    private String categoryName;

    @NaturalId
    @NotNull
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}

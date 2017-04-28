package com.focuson.webshop.bean.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Focuson created on 17-2-28.
 */

@Entity(name = "tbl_product_type")
public class ProductType implements Serializable {

    //JPA 注解要么全部放置在get方法上，要么全部放置在属性上，不可混合使用

    /**
     * 类别ID
     */
    private Integer typeId;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 备注， 用于google搜索页面描述
     */
    private String note;

    /**
     * 是否可见
     */
    private Boolean visible = true;

    private Set<ProductType> childTypes = new HashSet<>();

    private ProductType parent;

    public ProductType() {
    }

    public ProductType(String name, String note) {
        this.name = name;
        this.note = note;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Column(length = 36, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(nullable = false)
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "parent")
    public Set<ProductType> getChildTypes() {
        return childTypes;
    }

    public void setChildTypes(Set<ProductType> children) {
        this.childTypes = children;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "parent_id")
    public ProductType getParent() {
        return parent;
    }

    public void setParent(ProductType parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj) || getClass() != obj.getClass()) {
            return false;
        }

        ProductType that = (ProductType) obj;

        return typeId.equals(that.typeId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + (Objects.isNull(typeId) ? 0 : typeId.hashCode());
        return result;
    }
}

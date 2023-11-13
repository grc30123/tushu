package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */

@EqualsAndHashCode(callSuper = false)
public class Shopping implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "shopping_id", type = IdType.AUTO)
    private Integer shoppingId;
    @TableField(value = "ID_user")
    private String IDuser;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private Date orderDate;
    private String test;

    public Integer getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Integer shoppingId) {
        this.shoppingId = shoppingId;
    }

    public String getID_user() {
        return IDuser;
    }

    public void setID_user(String IDuser) {
        this.IDuser = IDuser;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}

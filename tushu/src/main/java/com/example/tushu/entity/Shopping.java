package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer orderId;
    private String bookId;
    private String flag;
    private Integer quantity;
    private BigDecimal price;
    private String orderDate;


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


}

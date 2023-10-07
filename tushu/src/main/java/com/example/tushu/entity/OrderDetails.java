package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author grc
 * @since 2023-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detail_id", type = IdType.AUTO)
    private Integer detailId;

    private Integer orderId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private String state;


}

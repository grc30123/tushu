package com.example.tushu.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;

    private String customerName;

    private BigDecimal paymentAmount;

    private Date paymentDate;


}

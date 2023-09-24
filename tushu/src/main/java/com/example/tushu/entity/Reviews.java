package com.example.tushu.entity;

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
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "review_id", type = IdType.AUTO)
    private Integer reviewId;

    private String customerName;

    private String productName;

    private Integer rating;

    private String reviewText;

    private Date reviewDate;


}

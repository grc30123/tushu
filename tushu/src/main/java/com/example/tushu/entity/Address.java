package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    private String customerName;

    private String streetAddress;

    private String city;

    private String state;

    private String postalCode;


}

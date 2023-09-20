package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author grc
 * @since 2023-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID_authority", type = IdType.AUTO)
    private Long idAuthority;

    @TableField("authorityName")
    private String authorityName;


}

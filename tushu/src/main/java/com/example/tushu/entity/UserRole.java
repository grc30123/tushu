package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("ID_user")
    private Integer ID;
    @TableField("ID_user")
    private Integer idUser;

    @TableField("ID_role")
    private Integer idRole;


}

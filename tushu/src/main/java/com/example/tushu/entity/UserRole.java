package com.example.tushu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID_user")
    private Integer idUser;

    @TableField("ID_role")
    private Integer idRole;


}

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
public class RoleAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID_role")
    private Integer idRole;

    @TableField("ID_authority")
    private Integer idAuthority;


}

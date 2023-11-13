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
 * @since 2023-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单栏id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 路由名
     */
    private String name;

    /**
     * 组件资源路径
     */
    private String component;

    /**
     * 菜单名
     */
    private String title;

    /**
     * 图片名
     */
    private String icon;

    /**
     * 是否隐藏
     */
    private Boolean hidden;


}

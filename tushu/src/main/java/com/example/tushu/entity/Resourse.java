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
 * @since 2023-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Resourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * resourse主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 资源名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createDate;


}

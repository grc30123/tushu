package com.example.tushu.controller;


import com.example.tushu.entity.RoleResourceRelation;
import com.example.tushu.service.RoleResourceRelationService;
import com.example.tushu.util.result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
@RestController
@RequestMapping("/role-resource-relation")
public class RoleResourceRelationController {
    @Autowired
    private RoleResourceRelationService roleResourceRelationService;

    @ApiOperation("批量删除")
    @PostMapping("/deleteList")
    public result deleteList(@RequestBody List<Long> byId) {
        boolean byIds = roleResourceRelationService.removeByIds(byId);
        return result.ok(byIds);
    }

    @ApiOperation("批量添加角色资源关系")
    @PostMapping("/addBatch")
    public result addBatch(@RequestBody List<RoleResourceRelation> list) {
        int i = roleResourceRelationService.addBatch(list);
        return result.ok(i);
    }

}

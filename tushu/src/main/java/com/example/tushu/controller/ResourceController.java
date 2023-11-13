package com.example.tushu.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.config.SecurityConfig.component.DynamicSecurityMetadataSource;
import com.example.tushu.entity.Resource;
import com.example.tushu.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-10-29
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("角色资源联查列表")
    @GetMapping("/list")
    public Object resourceList() {
        return resourceService.resourceList();
    }

    @ApiOperation("资源表列表")
    @GetMapping("/resourceList")
    public Object list() {
        return resourceService.list();
    }

    @ApiOperation("资源表分页列表")
    @GetMapping("/pageList")
    public Object pageList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String name) {
        Page<Resource> page = resourceService.page(new Page<>(current, size), new QueryWrapper<Resource>().like("name", name));
//        if (name == "") {
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("records", page);
//            map.put("total", page.getRecords().size());
//            return map;
//        }
        return page;
    }

    @ApiOperation("getById")
    @GetMapping("/getById")
    public Object getById(@RequestParam Integer id) {
        return resourceService.getById(id);
    }

    @ApiOperation("saveOrUpdate 主键ID存在更新 否则添加")
    @PostMapping("/saveOrUpdate")
    public Object save(@RequestBody Resource resource) {
        if (resource.getId() == 0) {
            resource.setCreateDate(DateUtil.now());
        }
        boolean saveOrUpdate = resourceService.saveOrUpdate(resource);
        dynamicSecurityMetadataSource.clearDataSource();
        return saveOrUpdate;
    }

    @ApiOperation("deleteById id删除")
    @PostMapping("/deleteById")
    public Object deleteById(long id) {
        boolean removeById = resourceService.removeById(id);
        dynamicSecurityMetadataSource.clearDataSource();
        return removeById;
    }

}

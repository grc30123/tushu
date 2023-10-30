package com.example.tushu.controller;


import com.example.tushu.entity.RoleAuthority;
import com.example.tushu.service.RoleAuthorityService;
import com.example.tushu.util.result;
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
 * @since 2023-09-10
 */
@RestController
@RequestMapping("/role-authority")
public class RoleAuthorityController {
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @PostMapping("/addRoleAuthorityBatch")
    public result addRoleAuthority(@RequestBody List<RoleAuthority> roleAuthorityList) {
        int i = roleAuthorityService.addBatch(roleAuthorityList);
        return result.ok(i);
    }
//        List<RoleAuthority> roleAuthorityList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            RoleAuthority roleAuthority = new RoleAuthority();
//            roleAuthority.setIdRole(1);
//            roleAuthority.setIdAuthority(i);
//            roleAuthorityList.add(roleAuthority);
//        }
//        roleAuthorityService.addBatch(roleAuthorityList);
//        return result.ok();
//    }

}

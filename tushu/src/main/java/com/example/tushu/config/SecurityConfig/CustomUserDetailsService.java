package com.example.tushu.config.SecurityConfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tushu.entity.Authority;
import com.example.tushu.entity.RoleAuthority;
import com.example.tushu.entity.User;
import com.example.tushu.mapper.RoleAuthorityMapper;
import com.example.tushu.mapper.RoleMapper;
import com.example.tushu.service.AuthorityService;
import com.example.tushu.service.UserRoleService;
import com.example.tushu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;//通过用户名查 用户ID
    @Autowired
    private UserRoleService userRoleService;//通过用户ID插角色ID
    @Autowired
//    private RoleService roleService;//通过角色ID查角色
    private RoleMapper roleMapper;
    @Autowired
//    private AuthorityService authorityService;//通过角色ID查权限
    private RoleAuthorityMapper roleAuthorityMapper;//通过角色ID查权限ID
    @Autowired
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper condition = new QueryWrapper();
        condition.eq("account", username);
        User user = userService.getOne(condition);
        Long ID_user = user.getIdUser();//用户ID
        Long phone = user.getPhone();
        Integer ID_role = userRoleService.getId(ID_user).getIdRole(); //角色ID
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在，请检查用户名是否输入正确");
        }
        //根据用户名查询用户角色和权限
        List<GrantedAuthority> authorities = new ArrayList<>();//权限
        //将角色名添加到权限集合中，注意要加上"ROLE_"前缀
        String roles = roleMapper.getbyid(ID_role).getRoleName();//角色
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles));
        authorities.add(new SimpleGrantedAuthority(roles));
        //将角色的权限添加到权限集合中
        List<RoleAuthority> authorityName = roleAuthorityMapper.getbyid(ID_role);

        for (int i = 0; i < authorityName.size(); i++) {
            Authority privilege = authorityService.getById(authorityName.get(i).getIdAuthority());
            authorities.add(new SimpleGrantedAuthority(privilege.getAuthorityName()));
        }
        return new com.example.tushu.mode.vo.User(user.getAccount(), user.getPassword(), authorities, ID_user, phone, roles);
//        return new com.example.tushu.mode.vo.User(user.getAccount(), user.getPassword(), authorities, ID_user, phone);

    }
}

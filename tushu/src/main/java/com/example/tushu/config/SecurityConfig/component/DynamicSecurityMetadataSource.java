package com.example.tushu.config.SecurityConfig.component;

import cn.hutool.core.util.URLUtil;
import com.example.tushu.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    //    private static Map<String, ConfigAttribute> configAttributeMap = null;
    private static MultiValueMap<String, ConfigAttribute> configAttributeMap = new LinkedMultiValueMap<>();

    @Autowired
    private ResourceService resourceService;

    @PostConstruct
    public void loadDataSource() {
        List<Map<String, String>> list = resourceService.resourceList();
//        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        MultiValueMap<String, ConfigAttribute> map = new LinkedMultiValueMap<>();
        for (Map<String, String> resource : list) {
//            map.put(resource.get("url"), new SecurityConfig(resource.get("roleName")));
            String url = resource.get("url");
            String roleName = resource.get("roleName");
            map.add(url, new SecurityConfig(roleName));
        }

        configAttributeMap = map;
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        clearDataSource();
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
//        Iterator<String> iterator
//        = configAttributeMap.keySet().iterator();//这里是key是string 表示的是roleName value是权限url
        Iterator<String> iterator = configAttributeMap.keySet().iterator();//这里是key是string 表示的是roleName value是权限url
        while (iterator.hasNext()) {//用迭代器遍历 key
            String pattern = iterator.next().trim();
            if (pathMatcher.match(pattern, path)) {  //匹配访问的路径
                for (ConfigAttribute configAttribute : configAttributeMap.get(pattern)) {
                    configAttributes.add(configAttribute);//需要的角色
                }
            }
        }
        return configAttributes;//返回访问的路径
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

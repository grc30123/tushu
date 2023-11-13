package com.example.tushu.mode.dto;

import com.example.tushu.entity.Menu;

import java.util.List;

public class MenuList extends Menu {
    private List<Menu> children;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}

package com.fanfan.pojo;

public class Menu {
    private Integer menuId;

    private String menuUrl;

    private String menuName;

    private Integer parentId;

    private String icon;

    private Menu[] children = new Menu[0];

    public Menu(Integer menuId, String menuUrl, String menuName, Integer parentId, String icon) {
        this.menuId = menuId;
        this.menuUrl = menuUrl;
        this.menuName = menuName;
        this.parentId = parentId;
        this.icon = icon;
    }

    public Menu() {
        super();
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Menu[] getChildren() {
        return children;
    }

    public void setChildren(Menu[] children) {
        this.children = children;
    }
}

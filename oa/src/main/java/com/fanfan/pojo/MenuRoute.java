package com.fanfan.pojo;

public class MenuRoute {
    private Integer routeId;

    private Integer menuId;

    private String routeName;

    private String routeUrl;

    public MenuRoute(Integer routeId, Integer menuId, String routeName, String routeUrl) {
        this.routeId = routeId;
        this.menuId = menuId;
        this.routeName = routeName;
        this.routeUrl = routeUrl;
    }

    public MenuRoute() {
        super();
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl == null ? null : routeUrl.trim();
    }
}
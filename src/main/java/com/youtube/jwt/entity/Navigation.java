package com.youtube.jwt.entity;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Navigation {
    private List<NavigationItem> compact;
    private List<NavigationItem> defaultItems;
    private List<NavigationItem> futuristic;
    private List<NavigationItem> horizontal;

    // getters and setters
}

@Data
@ToString
class NavigationItem {
    private String id;
    private String title;
    private Boolean exactMatch;
    private Boolean active;
    private Boolean disabled;
    private String tooltip;
    private String subtitle;
    private String type;
    private String icon;
    private List<NavigationItem> children;
    private List<String> roles;
    private String link;

    // getters and setters
}
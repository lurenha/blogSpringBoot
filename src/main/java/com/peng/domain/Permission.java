package com.peng.domain;

import java.io.Serializable;

public class Permission implements Serializable {

    Integer pe_id;
    String menu_code;
    String menu_name;
    String permission_code;
    String permission_name;
    boolean required_permission;

    public Integer getPe_id() {
        return pe_id;
    }

    public void setPe_id(Integer pe_id) {
        this.pe_id = pe_id;
    }

    public String getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(String menu_code) {
        this.menu_code = menu_code;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getPermission_code() {
        return permission_code;
    }

    public void setPermission_code(String permission_code) {
        this.permission_code = permission_code;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public boolean isRequired_permission() {
        return required_permission;
    }

    public void setRequired_permission(boolean required_permission) {
        this.required_permission = required_permission;
    }
}

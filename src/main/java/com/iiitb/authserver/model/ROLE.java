package com.iiitb.authserver.model;

public enum ROLE {
    STUDENT("student"),
    PROFESSOR("professor"),
    ADMIN("admin"),
    SUPERADMIN("superadmin");

    private final String roleName;

    ROLE(final String role) {
        this.roleName = role.toUpperCase();
    }

    @Override
    public String toString() {
        return roleName;
    }
}

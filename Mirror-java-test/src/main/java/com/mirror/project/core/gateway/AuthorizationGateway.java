package com.mirror.project.core.gateway;

public interface AuthorizationGateway {
    boolean isAuthenticated();
    boolean hasRole(String role);
    String getCurrentUserEmail();

}

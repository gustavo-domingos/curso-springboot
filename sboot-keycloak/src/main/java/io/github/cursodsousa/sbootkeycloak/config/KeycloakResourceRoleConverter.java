package io.github.cursodsousa.sbootkeycloak.config;

import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakResourceRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final  String clientId;

    public KeycloakResourceRoleConverter(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> resourceAcess = jwt.getClaimAsMap("resource_access");

        if(resourceAcess == null || !resourceAcess.containsKey(clientId)){
            return Collections.emptyList();
        }

        Map<String, Object> clientResource = (Map<String, Object>) resourceAcess.get(clientId);
        Collection<String> roles = (Collection<String>) clientResource.get("roles");

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}

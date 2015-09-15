package com.eklib.desktopviewer.security;

import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.security.model.APIKeyAuthenticationCredentials;
import com.eklib.desktopviewer.security.model.APIKeyAuthenticationPrincipal;
import com.eklib.desktopviewer.security.model.APIKeyAuthenticationToken;
import com.eklib.desktopviewer.services.security.UserServices;
import com.eklib.desktopviewer.util.ConvertBytesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by vadim on 23.10.2014.
 */
public class APIKeyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserServices userServices;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) {
        APIKeyAuthenticationToken token = (APIKeyAuthenticationToken) authentication;

        APIKeyAuthenticationPrincipal principal = (APIKeyAuthenticationPrincipal) token.getPrincipal();
        APIKeyAuthenticationCredentials credentials = (APIKeyAuthenticationCredentials) token.getCredentials();

        if (principal.getName() == null) {
            throw new AuthenticationServiceException("Principal must be set in order to perform authentication");
        }

        AuthenticableDTO auth = this.userServices.findAuthenticable(principal.getName());

        //Check that credential is valid
        String expected = null;

        List<GrantedAuthority> authorities = getGrantedAuthority(auth.getRoles(), principal.getName());

        try {
            expected = this.getExpectedHashedValue(auth.getPassphrase(), credentials.getUri(), credentials.getParameterMap());
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationServiceException("Unable to generate hash", e);
        }

        if (expected.equals(credentials.getSignature())) {
            return new APIKeyAuthenticationToken(principal, credentials, authorities);
        } else {
          throw new BadCredentialsException("Signature is invalid");
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.isAssignableFrom(APIKeyAuthenticationToken.class);
    }

    private String getExpectedHashedValue(String secretKey, String uri, Map parameterMap) throws NoSuchAlgorithmException {
        //First, adds salt and uri
        StringBuilder toHash = new StringBuilder();
        toHash.append(secretKey).append(":").append(uri);

        //Second, sorts parameters by name (asc)
        List<String> parameterNames = new ArrayList<String>();
        Iterator<String> names = parameterMap.keySet().iterator();
        while (names.hasNext()) {
            String name = (String) names.next();
            parameterNames.add(name);
        }
        Collections.sort(parameterNames);

        //Third, adds parameters values
        for (String name : parameterNames) {
            if (APIKeyAuthenticationProcessingFilter.PARAM_SIGNATURE.equals(name)) {
                continue;
            }
            toHash.append(":").append(name).append(":");
            String[] values = (String[]) parameterMap.get(name);

            //Sort values
            Arrays.sort(values);
            for (int i = 0; i < values.length; i++) {
                if (i > 0) {
                    toHash.append("/");
                }
                toHash.append(values[i]);
            }
        }
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return ConvertBytesUtil.byteArray2Hex(md.digest(toHash.toString().getBytes()));
    }

    private List<GrantedAuthority> getGrantedAuthority(Set<RoleDTO> roleDTOs, String name) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<RoleDTO> roles = roleDTOs;
        for(RoleDTO roleDTO : roles){
            authorities.add(new SimpleGrantedAuthority(roleDTO.getRoleName()));
        }

        if(authorities.isEmpty()){
            throw new AuthenticationServiceException("No role found for client " + name);
        }
        return authorities;
    }
}

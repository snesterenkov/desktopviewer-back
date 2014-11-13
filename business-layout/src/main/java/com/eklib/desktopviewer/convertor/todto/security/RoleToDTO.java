package com.eklib.desktopviewer.convertor.todto.security;

import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 12.11.2014.
 */
@Component
public class RoleToDTO implements Function<RoleEntity,RoleDTO> {

    @Override
    public RoleDTO apply(RoleEntity role) {
        if(role == null){
            return null;
        }
        return RoleDTO.valueOf(role.name());
    }
}

package com.eklib.desktopviewer.convertor.fromdto.security;

import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 12.11.2014.
 */
@Component
public class RoleFromDTO implements Function<RoleDTO, RoleEntity> {

    @Override
    public RoleEntity apply(RoleDTO roleDTO) {
        if(roleDTO == null){
            return null;
        }
        return RoleEntity.valueOf(roleDTO.name());
    }
}

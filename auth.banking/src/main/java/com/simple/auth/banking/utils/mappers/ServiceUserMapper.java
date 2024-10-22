package com.simple.auth.banking.utils.mappers;

import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceUserMapper {
    ServiceUserDto convertToDto(ServiceUser serviceUser);
    ServiceUser convertToEntity(ServiceUserDto serviceUserDto);
}

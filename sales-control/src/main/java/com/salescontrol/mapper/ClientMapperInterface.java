package com.salescontrol.mapper;

import com.salescontrol.dto.client.ClientPutDTO;
import com.salescontrol.model.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClientMapperInterface {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientPutDTO dto, @MappingTarget Client entity);

}

package com.salescontrol.mapper;

import com.salescontrol.dto.address.AddressDTO;
import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public abstract Address toAddress(AddressPostDTO addressPostDTO);

    public abstract AddressDTO toAddressDTO(Address address);

    public abstract AddressPostDTO toAddressPost(ResponseEntity<AddressPostDTO> addressPostDTOResponseEntity);

}

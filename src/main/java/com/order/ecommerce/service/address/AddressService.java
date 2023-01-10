package com.order.ecommerce.service.address;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.entity.Address;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.repository.IAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService {

    private final IAddressRepository addressRepository;
    private final OrderDetailsMapper orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);

     public Address buildAndLoadAddress(AddressDto addressDto) {
        Address addressEntity = orderDetailsMapper.toAddressEntity(addressDto);
        addressEntity.setAddressId(UUID.randomUUID().toString());
        addressEntity.setCreatedAt(LocalDate.now());
        log.info("Saving billing/shipping address for address id = {}", addressEntity.getAddressId());
        return addressRepository.save(addressEntity);
    }
}

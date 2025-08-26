package com.bob.db.mapper;

import com.bob.db.dto.RazorpayOrderDto;
import com.bob.db.entity.RazorpayOrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RazorpayOrderMapper {

    RazorpayOrderDto toDto(RazorpayOrderEntity entity);

    RazorpayOrderEntity toEntity(RazorpayOrderDto dto);

    List<RazorpayOrderDto> toDtoList(List<RazorpayOrderEntity> entities);

    List<RazorpayOrderEntity> toEntityList(List<RazorpayOrderDto> dtos);
}

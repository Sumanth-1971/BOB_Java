package com.bob.db.mapper;

import com.bob.db.dto.AuditTrailDto;
import com.bob.db.entity.AuditTrailEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditTrailMapper {

    AuditTrailDto toDto(AuditTrailEntity entity);

    AuditTrailEntity toEntity(AuditTrailDto dto);

    List<AuditTrailDto> toDtoList(List<AuditTrailEntity> entities);

    List<AuditTrailEntity> toEntityList(List<AuditTrailDto> dtos);
}

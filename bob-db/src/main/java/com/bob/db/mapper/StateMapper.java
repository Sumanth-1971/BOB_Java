package com.bob.db.mapper;

import com.bob.db.dto.StateDto;
import com.bob.db.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StateMapper {

    StateDto toDto(StateEntity stateEntity);

    List<StateDto> toDtoList(List<StateEntity> stateEntities);
}

package com.bob.db.mapper;

import com.bob.db.dto.CandidateApplicationDto;
import com.bob.db.entity.CandidateApplicationsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateApplicationMapper {

    CandidateApplicationDto toDto(CandidateApplicationsEntity entity);

    CandidateApplicationsEntity toEntity(CandidateApplicationDto dto);

    List<CandidateApplicationDto> toDtoList(List<CandidateApplicationsEntity> entities);

    List<CandidateApplicationsEntity> toEntityList(List<CandidateApplicationDto> dtos);
}

package com.example.bank.mapper;

import com.example.bank.dto.MovementDto;
import com.example.bank.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AccountMapper.class, ReferenceMapper.class})
public interface MovementMapper {

    MovementDto mapToDto(Movement movement);

    List<MovementDto> mapToDtos(List<Movement> movement);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "id", ignore = true)
    Movement mapToEntity(MovementDto movementDto);
}

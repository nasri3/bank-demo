package com.example.bank.mapper;

import com.example.bank.dto.MovementReadDto;
import com.example.bank.dto.MovementWriteDto;
import com.example.bank.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AccountMapper.class, ReferenceMapper.class})
public interface MovementMapper {

    MovementReadDto mapToDto(Movement movement);

    List<MovementReadDto> mapToDtos(List<Movement> movement);

    Movement mapToEntity(MovementWriteDto movementWriteDto);
}

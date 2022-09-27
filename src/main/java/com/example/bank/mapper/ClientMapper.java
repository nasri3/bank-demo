package com.example.bank.mapper;

import com.example.bank.dto.ClientDto;
import com.example.bank.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AccountMapper.class})
public interface ClientMapper {

    ClientDto mapToDto(Client client);

    List<ClientDto> mapToDtos(List<Client> clients);

    @Mapping(target = "id", ignore = true)
    Client mapToEntity(ClientDto clientDto);
}

package com.example.bank.mapper;

import com.example.bank.dto.ClientDto;
import com.example.bank.model.Client;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AccountMapper.class})
public interface ClientMapper {

    ClientDto mapToDto(Client client);

    List<ClientDto> mapToDtos(List<Client> clients);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    Client mapToEntity(ClientDto clientDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    void patchEntity(ClientDto clientDto, @MappingTarget Client client);
}

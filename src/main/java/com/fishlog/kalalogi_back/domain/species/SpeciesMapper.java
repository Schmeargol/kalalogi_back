package com.fishlog.kalalogi_back.domain.species;

import com.fishlog.kalalogi_back.fishlog.fish.SpeciesDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SpeciesMapper {
    Species toEntity(SpeciesDto speciesDto);

    SpeciesDto toDto(Species species);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Species partialUpdate(SpeciesDto speciesDto, @MappingTarget Species species);
}
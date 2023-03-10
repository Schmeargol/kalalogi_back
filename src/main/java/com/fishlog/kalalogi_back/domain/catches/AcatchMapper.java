package com.fishlog.kalalogi_back.domain.catches;

import com.fishlog.kalalogi_back.fishlog.Status;
import com.fishlog.kalalogi_back.fishlog.catches.CatchDto;
import com.fishlog.kalalogi_back.fishlog.catches.CatchViewDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AcatchMapper {

    @Mapping(constant = Status.ACTIVE, target = "status")
    @Mapping(source = "date", target = "date")
    Acatch toEntity(CatchDto catchDto);

    @Mapping(source = "id", target = "catchId")
    @Mapping(source = "date", target = "catchDate")
    @Mapping(source = "waterbody.id", target = "waterbodyId")
    @Mapping(source = "waterbody.name", target = "waterbodyName")
    @Mapping(source = "waterbody.latitude", target = "latitude")
    @Mapping(source = "waterbody.longitude", target = "longitude")
    CatchViewDto toDto(Acatch acatch);

    List<CatchViewDto> toDtos(List<Acatch> catches);

    @InheritConfiguration (name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCatch(@MappingTarget Acatch acatch, CatchDto catchDto);

}
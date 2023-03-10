package com.fishlog.kalalogi_back.fishlog.location;

import com.fishlog.kalalogi_back.domain.waterbody.Waterbody;
import com.fishlog.kalalogi_back.domain.waterbody.WaterbodyMapper;
import com.fishlog.kalalogi_back.domain.waterbody.WaterbodyRepository;
import com.fishlog.kalalogi_back.domain.waterbody.WaterbodyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Resource
    private WaterbodyService waterbodyService;
    @Resource
    private WaterbodyMapper waterbodyMapper;
    private final WaterbodyRepository waterbodyRepository;

    public LocationService(WaterbodyRepository waterbodyRepository) {
        this.waterbodyRepository = waterbodyRepository;
    }

    public List<LocationDto> getAllLocations() {
        List<Waterbody> waterbodies = waterbodyService.findAllWaterbodies();

        return waterbodyMapper.toDtos(waterbodies);

    }

    public void addWaterbody(LocationDto locationDto) {

        Waterbody waterbody = waterbodyMapper.toEntity(locationDto);

        waterbodyService.saveWaterbody(waterbody);

    }

}

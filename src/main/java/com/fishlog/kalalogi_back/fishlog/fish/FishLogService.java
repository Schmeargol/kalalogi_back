package com.fishlog.kalalogi_back.fishlog.fish;

import com.fishlog.kalalogi_back.domain.catches.Acatch;
import com.fishlog.kalalogi_back.domain.catches.AcatchService;
import com.fishlog.kalalogi_back.domain.fish.Fish;
import com.fishlog.kalalogi_back.domain.fish.FishMapper;
import com.fishlog.kalalogi_back.domain.fish.FishService;
import com.fishlog.kalalogi_back.domain.species.Species;
import com.fishlog.kalalogi_back.domain.species.SpeciesMapper;
import com.fishlog.kalalogi_back.domain.species.SpeciesService;
import com.fishlog.kalalogi_back.domain.user.User;
import com.fishlog.kalalogi_back.domain.user.UserService;
import com.fishlog.kalalogi_back.domain.waterbody.Waterbody;
import com.fishlog.kalalogi_back.domain.waterbody.WaterbodyService;
import com.fishlog.kalalogi_back.domain.catches.AcatchMapper;
import com.fishlog.kalalogi_back.fishlog.Status;
import com.fishlog.kalalogi_back.fishlog.catches.CatchDto;
import com.fishlog.kalalogi_back.fishlog.catches.CatchViewDto;
import com.fishlog.kalalogi_back.fishlog.fish.dto.ChartFishDto;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishLogService {
    public static final Integer ALL_WATERBODIES_ID = 0;
    public static final Integer ALL_SPECIES_ID = 0;
    @Resource
    private SpeciesService speciesService;
    @Resource
    private FishService fishService;
    @Resource
    private SpeciesMapper speciesMapper;
    @Resource
    private FishMapper fishMapper;

    @Resource
    private AcatchMapper acatchMapper;

    @Resource
    private UserService userService;

    @Resource
    private WaterbodyService waterbodyService;

    @Resource
    private AcatchService acatchService;


    public List<SpeciesDto> getAllSpecies() {
        List<Species> species = speciesService.findAllSpecies();
        return speciesMapper.toDtos(species);
    }

    public FishPageDto getFishies(Integer waterbodyId, Integer speciesId, Pageable pagination) {


        Page<Fish> fishies = fishService.getFish(waterbodyId, speciesId, pagination);
        FishPageDto fishPage = new FishPageDto();
        fishPage.setFishies(fishMapper.toDtos(fishies.getContent()));
        fishPage.setTotalPages(fishies.getTotalPages());

        return fishPage;
    }


    public FishPageDto getUserFish(Integer userId, Integer waterbodyId, Integer speciesId,Pageable pagination) {
        Page<Fish> fishies = fishService.getUserFish(userId, waterbodyId, speciesId, pagination);

        FishPageDto fishPage = new FishPageDto();
        fishPage.setFishies(fishMapper.toDtos(fishies.getContent()));
        fishPage.setTotalPages(fishies.getTotalPages());

        return fishPage;
    }

    public FishPageDto getCatchFish(Integer catchId, Integer waterbodyId, Integer speciesId, Pageable pagination) {

        Page<Fish> fishies = fishService.getCatchFish(catchId, waterbodyId, speciesId, pagination);
        FishPageDto fishPage = new FishPageDto();
        fishPage.setFishies(fishMapper.toDtos(fishies.getContent()));
        fishPage.setTotalPages(fishies.getTotalPages());
        return fishPage;
    }


    public void addCatch(CatchDto catchDto) {
        Acatch acatch = acatchMapper.toEntity(catchDto);

        User user = userService.findUserById(catchDto.getUserId());
        acatch.setUser(user);

        Waterbody waterbody = waterbodyService.findWaterbodyId(catchDto.getWaterbodyId());
        acatch.setWaterbody(waterbody);

        acatchService.saveAcatch(acatch);

    }

    public void addFish(FishDto fishDto) {
        Fish fish = fishMapper.toEntity(fishDto);

        Acatch acatch = acatchService.findByCatchId(fishDto.getCatchId());
        fish.setAcatch(acatch);

        Species species = speciesService.findBySpeciesId(fishDto.getSpeciesId());
        fish.setSpecies(species);

        fishService.saveFish(fish);

    }

    public List<CatchViewDto> getUserCatches(Integer userId) {
        List<Acatch> catches = acatchService.findCatchesByUser(userId);

        return acatchMapper.toDtos(catches);
    }

    public CatchViewDto getCatch(Integer catchId) {
        Acatch acatch = acatchService.findByCatchId(catchId);

        return acatchMapper.toDto(acatch);
    }

    public void editFish(Integer fishId, FishDto fishDto) {
        Fish fish = fishService.findFish(fishId);
        fishMapper.updateFish(fish, fishDto);
        fish.setSpecies(speciesService.findBySpeciesId(fishDto.getSpeciesId()));
        fishService.saveFish(fish);

    }

    public void editCatch(Integer catchId, CatchDto catchDto) {
        Acatch acatch = acatchService.findByCatchId(catchId);

        Waterbody waterbody = waterbodyService.findWaterbodyId(catchDto.getWaterbodyId());
        acatch.setWaterbody(waterbody);

        acatchMapper.updateCatch(acatch, catchDto);
        acatchService.saveAcatch(acatch);
    }


    public void deleteCatch(Integer catchId) {
        Acatch acatch = acatchService.findByCatchId(catchId);
        acatch.setStatus(Status.DEACTIVATED);
        acatchService.saveAcatch(acatch);
    }


    public void deleteFish(Integer fishId) {
        Fish fish = fishService.findFish(fishId);
        fish.setStatus(Status.DEACTIVATED);
        fishService.saveFish(fish);
    }

    public FishDto getFish(Integer fishId) {
        Fish fish = fishService.findFish(fishId);
        return fishMapper.toFishDto(fish);
    }

    public List<ChartFishDto> getFishChartInfo(Integer userId) {
        return fishService.getFishChartInfo(userId);
    }

}

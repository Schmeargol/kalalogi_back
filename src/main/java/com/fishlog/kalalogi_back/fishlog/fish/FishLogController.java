package com.fishlog.kalalogi_back.fishlog.fish;


import com.fishlog.kalalogi_back.fishlog.catches.CatchDto;
import com.fishlog.kalalogi_back.fishlog.catches.CatchViewDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FishLogController {

    @Resource
    private FishLogService fishLogService;


    @GetMapping("/user/fish")
    @Operation(summary = "Find all Users fish details from the database", description = "This is used to display fish caught by User")
    public List<FishViewDto> getUserFishTemp(@RequestParam Integer userId, @RequestParam Integer waterbodyId, @RequestParam Integer speciesId) {

        return fishLogService.getUserFishTemp(userId, waterbodyId, speciesId);
    }


    @GetMapping("/fish/species")
    @Operation(summary = "Find all species from the database", description = "This is used for the species dropdown")
    public List<SpeciesDto> getAllSpecies() {
        return fishLogService.getAllSpecies();
    }

    @GetMapping("/fishies")
    @Operation(summary = "Find all caught fish details from the database", description = "This is used to display fish caught")
    public List<FishViewDto> getAllFish(@RequestParam Integer waterbodyId, @RequestParam Integer speciesId) {

        return fishLogService.getAllFish(waterbodyId, speciesId);
    }

    @GetMapping("/fish")
    @Operation(summary = "Find all Users fish details from the database", description = "This is used to display fish caught by User")
    public FishDto getFish(@RequestParam Integer fishId) {

        return fishLogService.getFish(fishId);
    }


    @GetMapping("/catch/fish")
    @Operation(summary = "Find all Users fish details from the database", description = "This is used to display fish caught by User")
    public List<FishViewDto> getCatchFIsh(@RequestParam Integer catchId) {

        return fishLogService.getCatchFish(catchId);
    }


    @GetMapping("/catches")
    @Operation(summary = "Get user catches from DB", description = "????")
    public List<CatchViewDto> getUserCatches(@RequestParam Integer userId) {

        return fishLogService.getUserCatches(userId);
    }

    @GetMapping("/catch")
    @Operation(summary = "Get one catch info by Id", description = "Get one catch info for adding a fish to the catch")
    public CatchViewDto getCatch(@RequestParam Integer catchId) {

        return fishLogService.getCatch(catchId);
    }

    @PostMapping("/catch")
    @Operation(summary = "Add new catch to database", description = "????")
    public void addCatch(@RequestBody CatchDto catchDto) {
        fishLogService.addCatch(catchDto);
    }

    @PostMapping("/fish")
    @Operation(summary = "Add caught fish details to database", description = "???")
    public void addFish(@RequestBody FishDto fishDto) {
        fishLogService.addFish(fishDto);
    }


    @PutMapping("/catch")
    @Operation(summary = "Update catch information in database", description = "???????")
    public void editCatch(@RequestParam Integer catchId, @RequestBody CatchDto catchDto) {
        fishLogService.editCatch(catchId, catchDto);
    }


    @PutMapping("/fish")
    @Operation(summary = "Update fish information in database", description = "????")
    public void editFish(@RequestParam Integer fishId, @RequestBody FishDto fishDto) {
        fishLogService.editFish(fishId, fishDto);
    }

    @DeleteMapping("/catch")
    @Operation(summary = "Deactivates catch in the database", description = "It does.")
    public void deleteCatch(@RequestParam Integer catchId) {
        fishLogService.deleteCatch(catchId);
    }

    @DeleteMapping("/fish")
    @Operation(summary = "Deactivates inserted fish in the database.", description = "???")
    public void deleteFish(@RequestParam Integer fishId) {
        fishLogService.deleteFish(fishId);
    }


}



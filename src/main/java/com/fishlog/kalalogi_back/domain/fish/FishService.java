package com.fishlog.kalalogi_back.domain.fish;

import com.fishlog.kalalogi_back.fishlog.Status;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishService {
    @Resource
    private FishRepository fishRepository;


    public List<Fish> getAllFish() {
        return fishRepository.findPublicFish(true, Status.ACTIVE);
    }
}
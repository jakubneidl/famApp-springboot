package cz.neidl.farmapp.service;

import cz.neidl.farmapp.model.HarvestRequestDto;
import cz.neidl.farmapp.model.HarvestResponseDto;

import javax.transaction.Transactional;
import java.util.List;

public interface HarvestService {
    List<HarvestResponseDto> getAllHarvests();

    HarvestResponseDto getHarvest(Long id);

    @Transactional
    HarvestResponseDto createNewHarvest(HarvestRequestDto harvestRequestDto);

    @Transactional
    String deleteHarvest(Long id);
}

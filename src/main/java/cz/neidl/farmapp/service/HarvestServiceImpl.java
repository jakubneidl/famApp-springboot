package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.Harvest;
import cz.neidl.farmapp.domain.PlantedCrop;
import cz.neidl.farmapp.exception.HarvestNotFoundException;
import cz.neidl.farmapp.mapper.HarvestMapper;
import cz.neidl.farmapp.model.HarvestRequestDto;
import cz.neidl.farmapp.model.HarvestResponseDto;
import cz.neidl.farmapp.repository.HarvestRepository;
import cz.neidl.farmapp.repository.PlantedCropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {
    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;

    /**
     * Returns all harvests that have been saved
     * @return all harvests
     */
    @Override
    public List<HarvestResponseDto> getAllHarvests(){
        return harvestRepository.findAll()
                .stream()
                .map(harvestMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * returns specific harvest based on its id
     * @param id id of the harvest
     * @return returns harvestResponseDto
     */
    @Override
    public HarvestResponseDto getHarvest(Long id) {
        return harvestMapper.mapToDto(harvestRepository.findById(id).orElseThrow(() -> new HarvestNotFoundException("Couldn't find harvest with id: " + id)));
    }

    /**
     * Creates new harvest based on HarvestRequestDto
     * @param harvestRequestDto
     * @return returns harvestResponseDto
     */
    @Override
    @Transactional
    public HarvestResponseDto createNewHarvest(HarvestRequestDto harvestRequestDto) {
        Harvest save = harvestRepository.save(harvestMapper.mapToDomain(harvestRequestDto));
        return harvestMapper.mapToDto(save);
    }

    /**
     * Deletes harvest based on id
     * @param id harvest id
     * @return string with harvest id which has been deleted
     */
    @Override
    @Transactional
    public String deleteHarvest(Long id){
        harvestRepository.delete(harvestRepository.findById(id)
                .orElseThrow(()-> new HarvestNotFoundException("Harvest with " + id + " not found. Can't be deleted")));
        return "crop with " + id +"Deleted";
    }

}

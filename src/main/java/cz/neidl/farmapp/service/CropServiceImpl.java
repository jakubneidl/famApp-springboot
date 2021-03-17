package cz.neidl.farmapp.service;

import cz.neidl.farmapp.domain.Crop;
import cz.neidl.farmapp.domain.Seeder;
import cz.neidl.farmapp.exception.CropAlreadyExistsException;
import cz.neidl.farmapp.exception.CropNotFoundException;
import cz.neidl.farmapp.exception.SeederNotFoundException;
import cz.neidl.farmapp.mapper.CropMapper;
import cz.neidl.farmapp.model.CropRequestDto;
import cz.neidl.farmapp.model.CropResponseDto;
import cz.neidl.farmapp.repository.CropRepository;
import cz.neidl.farmapp.repository.SeederRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {
    private final CropRepository cropRepository;
    private final SeederRepository seederRepository;
    private final CropMapper cropMapper;

    @Override
    public List<CropResponseDto> getAllCrops() {
        return cropRepository.findAll()
                .stream().map(cropMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CropResponseDto getCropByName(String cropName) {
        Crop crop = cropRepository.findByName(cropName).get();
        return cropMapper.mapToDto(crop);
    }

    @Transactional
    @Override
    public CropResponseDto saveCrop(String name) {
        if (cropRepository.findByName(name).isEmpty()) {
            CropResponseDto save = cropMapper.mapToDto(cropRepository.save(new Crop(name)));
            return save;
        } else {
            throw new CropAlreadyExistsException("Cannot save multiple crops with same name");
        }
    }

    @Transactional
    @Override
    public CropResponseDto saveCrop(CropRequestDto cropRequestDto) {
        if (cropRepository.findByName(cropRequestDto.getName()).isEmpty()) {
            Seeder seeder = seederRepository.findByName(cropRequestDto.getSeeder().getName())
                    .orElseThrow(()-> new SeederNotFoundException("Cannot save crop without existing seeder"));

            Crop crop = cropMapper.mapToDomain(cropRequestDto);
            crop.setSeeder(seeder);

            return cropMapper.mapToDto(cropRepository.save(crop));
        } else {
            throw new CropAlreadyExistsException("Cannot save multiple crops with same name");
        }
    }

    @Override
    public List<CropResponseDto> getAllCrops(String cropName) {
        if (cropName == null || cropName.isEmpty()) {
            return cropRepository.findAll()
                    .stream()
                    .map(cropMapper::mapToDto)
                    .collect(Collectors.toList());
        }
        return cropRepository.searchByName(cropName)
                .stream()
                .map(cropMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteCropByName(String cropName) {
        Crop crop = cropRepository.findByName(cropName)
                .orElseThrow(() -> new CropNotFoundException("Cannot delete crop that doesn't exist"));
        cropRepository.delete(crop);
    }

    @Transactional
    @Override
    public void saveCropFromVaadin(String cropName, String info, String spacing, String seederName) {

        if (cropRepository.findByName(cropName).isEmpty()) {
            Seeder seeder = seederRepository.findByName(seederName)
                    .orElseThrow(() -> new SeederNotFoundException(seederName + " Seeder with this name doesn't exist"));

            Crop cropToSave = new Crop()
                    .setName(cropName)
                    .setInfo(info)
                    .setSpacing(spacing)
                    .setSeeder(seeder);
            cropRepository.save(cropToSave);
        }else {
            throw  new CropAlreadyExistsException("Cannot save crop that already exists");
        }

    }
}

package com.JJEP.JJEP.application.client.child;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChildService implements IChildService{
    private final IChildRepository childRepository;
    private final ModelMapper modelMapper;
    public ChildService(IChildRepository childRepository, ModelMapper modelMapper) {
        this.childRepository = childRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setPropertyCondition(ctx -> {
            // Skip mapping if the source property is null
            return ctx.getSource() != null;
        });
    }


    @Override
    public ChildResponseDTO findChildById(long id) {
        Optional<Child> child = childRepository.findById(id);
        if (child.isEmpty()) {
            throw new ChildNotFoundException("Child not found");
        }
        return modelMapper.map(child, ChildResponseDTO.class);
    }

    @Override
    public List<ChildResponseDTO> findAllChildren() {
        List<Child> children = childRepository.findAll();
        List<ChildResponseDTO> childResponseDTOS = new ArrayList<>();

        if (children.isEmpty()) {
            throw new ChildNotFoundException("No children found");
        }
        for (Child child: children) {
            childResponseDTOS.add(modelMapper.map(child, ChildResponseDTO.class));
        }
        return childResponseDTOS;
    }

    @Override
    public List<ChildResponseDTO> findAllChildrenByClientId(long clientId) {
        List<Child> children = childRepository.getAllByClientId(clientId);
        List<ChildResponseDTO> childResponseDTOS = new ArrayList<>();

        for (Child child: children) {
            childResponseDTOS.add(modelMapper.map(child, ChildResponseDTO.class));
        }
        return childResponseDTOS;
    }

    @Override
    public void updateChild(long id, ChildRequestDTO childRequestDTO) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ChildResponseDTO saveChild(ChildRequestDTO childRequestDTO) {
        Child child = modelMapper.map(childRequestDTO, Child.class);
        Child savedChild = childRepository.save(child);
        return modelMapper.map(savedChild, ChildResponseDTO.class);
    }

    @Override
    public List<ChildResponseDTO> saveAllChildren(List<ChildRequestDTO> clientRequestDTOs) {
        List<ChildResponseDTO> childResponseDTOS = new ArrayList<>();
        for (ChildRequestDTO childRequestDTO: clientRequestDTOs) {
            Child child = modelMapper.map(childRequestDTO, Child.class);
            ChildResponseDTO childResponseDTO = modelMapper.map(childRepository.save(child), ChildResponseDTO.class);
            childResponseDTOS.add(childResponseDTO);
        }
        return childResponseDTOS;
    }

    @Override
    public void deleteChild(long id) {
        Optional<Child> child = childRepository.findById(id);
        if (child.isEmpty()) {
            throw new ChildNotFoundException("Child not found");
        }
        childRepository.deleteById(id);
    }
}

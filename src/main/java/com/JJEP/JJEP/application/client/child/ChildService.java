package com.JJEP.JJEP.application.client.child;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// service annotation from spring framework to indicate that this class is a service, so it can be autowired
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
        // optional is a container object which may or may not contain a non-null value
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
    // @Transactional annotation from spring framework to make database operations consistent
    @Transactional
    public void updateChild(long id, ChildResponseDTO childResponseDTO) {
        Optional<Child> existingChildOptional = childRepository.findById(id);
        if (existingChildOptional.isEmpty()) {
            throw new ChildNotFoundException("Child not found");
        }
        Child existingChild = existingChildOptional.get();
        Child updatedChild = modelMapper.map(childResponseDTO, Child.class);

        // update existingChild with updated attributes of updatedClient
        modelMapper.map(updatedChild, existingChild);
        childRepository.updateById(id, existingChild);

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

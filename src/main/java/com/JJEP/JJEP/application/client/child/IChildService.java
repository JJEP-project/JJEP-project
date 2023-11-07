package com.JJEP.JJEP.application.client.child;


import java.util.List;

public interface IChildService {
    ChildResponseDTO findChildById(long id);
    List<ChildResponseDTO> findAllChildren();
    List<ChildResponseDTO> findAllChildrenByClientId(long clientId);
    void updateChild(long id, ChildRequestDTO childRequestDTO);
    ChildResponseDTO saveChild(ChildRequestDTO childRequestDTO);
    List<ChildResponseDTO> saveAllChildren(List<ChildRequestDTO> childRequestDTO);
    void deleteChild(long id);
}

package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Role;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface RoleService {
    public MessageResponse save(Role role);
    public MessageResponse update(Role role);
    public MessageResponse delete(Long id);
    public List<Role> findAll();
    public Role findById(Long id);
}

package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Employe_speciality;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface Employe_specialityService {
    public MessageResponse save(Employe_speciality employe_speciality);
    public MessageResponse update(Employe_speciality employe_speciality);
    public MessageResponse delete(Long id);
    public List<Employe_speciality> findAll();
    public Employe_speciality findById(Long id);
}

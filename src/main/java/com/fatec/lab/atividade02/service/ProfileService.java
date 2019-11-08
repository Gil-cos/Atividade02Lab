package com.fatec.lab.atividade02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.lab.atividade02.entity.Profile;
import com.fatec.lab.atividade02.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile getByName(String profile) {
		return profileRepository.findByName(profile);
	}
}

package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Roles;
import com.proyecto5.repository.RolesRepository;

@Service
public class RolesServiceImpl extends GenericServiceImpl<Roles, Integer> implements RolesService {
	@Autowired
    private RolesRepository rolesRepository;

    @Override
    public CrudRepository<Roles, Integer> getDao() {
        return rolesRepository;
    }

}

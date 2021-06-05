package com.carworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.carworld.dao.EngineRepository;
import com.carworld.model.Engine;
import com.carworld.model.Manufacturer;

//Bean creation defined in xml
public class EngineServiceImpl implements EngineService{
	
	EngineRepository engineRepository;
	ManufacturerService engineManufacturerService;
	
	//@Autowired -> Setter DI through annotation config
	@Autowired
	public void setEngineRepository(EngineRepository engineRepository) {
		this.engineRepository = engineRepository;
	}

	@Autowired
	public void setEngineManufacturerService(ManufacturerService engineManufacturerService) {
		this.engineManufacturerService = engineManufacturerService;
	}

	@Override
	public void addEngine(Engine engine) throws Exception {
		engineRepository.addEngine(engine);
	}

	@Override
	public List<Engine> getAllEngine() throws Exception {
		return engineRepository.getAllEngine();
	}

	@Override
	public Engine getEngine(Long engineId) throws Exception {
		return engineRepository.getEngine(engineId);
	}

	@Override
	public void deleteEngine(Long engineId) throws Exception {
		engineRepository.deleteEngine(engineId);
	}

	@Override
	public List<Manufacturer> getAllManufactures() throws Exception {
		return engineManufacturerService.getAllManufacturer();
	}
	
}

package com.carworld.controller.car;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.carworld.model.Car;
import com.carworld.service.CarService;
import com.carworld.service.EngineService;
import com.carworld.service.ManufacturerService;

/**
 * 
 */
public class AddCarController implements Controller {
	
    
	private CarService carService;
	private EngineService engineService;
	private ManufacturerService manufacturerService;
	
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	public void setEngineService(EngineService engineService) {
		this.engineService = engineService;
	}
	public void setManufacturerService(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equals("GET")){
			ModelAndView modelAndView = new ModelAndView("AddCar");
			modelAndView.addObject("engines", carService.getAllEngine());
			modelAndView.addObject("manufactures", carService.getAllManufacturer());
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("Cars");
		Car car = new Car();
		car.setModel(request.getParameter("model"));
		car.setColor(request.getParameter("color"));
		car.setPrice(Double.valueOf(request.getParameter("price")));
		car.setEngine(engineService.getEngine(Long.valueOf(request.getParameter("engine"))));
		car.setManufacturer(manufacturerService.getManufacturer(Long.valueOf(request.getParameter("manufacturer"))));
		carService.addCar(car);
		modelAndView.addObject("cars", carService.getAllCar());
		return modelAndView;
	}

}
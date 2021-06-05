package com.carworld.controller.car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.carworld.service.CarService;

/**
 * Controller implementation class CarDetailController
 * 
 */

public class CarDetailController implements Controller {
    
	private CarService carService;
	
	//Autowire DI byName XML config
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("CarDetails");
		modelAndView.addObject("car", carService.getCar(Long.valueOf(request.getParameter("carId"))));
		return modelAndView;
	}
}

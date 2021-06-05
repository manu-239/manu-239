package com.carworld.controller.car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.carworld.service.CarService;

/**
 * 
 */

public class DeleteCarController implements Controller {
       
	
	private CarService carService;
	
	//Autowire DI by constructor XML config
	DeleteCarController(CarService carService){
		this.carService = carService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		carService.deleteCar(Long.valueOf(request.getParameter("carId")));
		ModelAndView modelAndView = new ModelAndView("Cars");
		modelAndView.addObject("cars", carService.getAllCar());
		return modelAndView;
		
	}

}

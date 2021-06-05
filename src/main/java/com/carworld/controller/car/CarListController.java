package com.carworld.controller.car;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.carworld.service.CarService;

/**
 * @author: mmathew@manh.com
 */

public class CarListController implements Controller {

	
	private BasicDataSource dataSource;
	private CarService carService;
	
	//Constructor DI through XML config
	CarListController(BasicDataSource dataSource){
		this.dataSource = dataSource;
	}
	
	//Setter DI through XML config
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("Cars");
		modelAndView.addObject("cars", carService.getAllCar());
		return modelAndView;
	}
	
	public void dbinit() {
		String path = "dbscripts.sql";
		try (InputStream is=this.getClass().getClassLoader().getResourceAsStream(path);
				BufferedReader reader= new BufferedReader(new InputStreamReader(is));){
			List<String> queries= new ArrayList<String>();
			String line=reader.readLine();
			String query="";
			while(line!=null){
				query+=line;
				if(query.endsWith(";")){
					queries.add(query);
					//System.out.println(query);
					query="";
				}
				line=reader.readLine();
			}
			Connection connection=dataSource.getConnection();
			Statement statmement=connection.createStatement();
			for(String sql:queries){
				statmement.executeUpdate(sql);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
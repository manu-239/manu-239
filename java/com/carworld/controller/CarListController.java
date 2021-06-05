package com.carworld.controller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.carworld.dao.DbConnectionFactory;
import com.carworld.service.AllService;
import com.carworld.service.CarService;

/**
 * @author: mmathew@manh.com
 */

public class CarListController implements Controller {
	private static final long serialVersionUID = 1L;
   
	public void dbinit() {
		try {
			String path = "dbscripts.sql";
			InputStream is=this.getClass().getClassLoader().getResourceAsStream(path);
			BufferedReader reader= new BufferedReader(new InputStreamReader(is));
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
			Connection connection=DbConnectionFactory.getDataSource().getConnection();
			Statement statmement=connection.createStatement();
			for(String sql:queries){
				statmement.executeUpdate(sql);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



	

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		dbinit();
		ModelAndView modelAndView = new ModelAndView("Cars.jsp");
		modelAndView.addObject("cars", carService().getAllCar());
		return modelAndView;
	}
	
	private CarService carService(){		
		return AllService.getCarService();
	}
}
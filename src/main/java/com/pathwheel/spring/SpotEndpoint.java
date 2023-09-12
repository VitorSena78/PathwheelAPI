package com.pathwheel.spring;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.pathwheel.dao.SpotDAO;
import com.pathwheel.io.Logger;
import com.pathwheel.jdbc.JdbcDataAccessObjectListener;
import com.pathwheel.jdbc.JdbcSpotDAO;
import com.pathwheel.model.SpotReportType;
import com.pathwheel.request.RegisterSpotRequest;
import com.pathwheel.request.ReportSpotRequest;
import com.pathwheel.response.FetchSpotResponse;
import com.pathwheel.response.RegisterSpotResponse;
import com.pathwheel.response.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/spot")
public class SpotEndpoint implements JdbcDataAccessObjectListener {

	

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RegisterSpotResponse register(@RequestBody String jsonRequest) {
		Logger.info("==[/v1/spot/register]=");
		RegisterSpotResponse response = new RegisterSpotResponse();
		try {
			RegisterSpotRequest request = new ObjectMapper().readValue(jsonRequest.getBytes("UTF-8"), RegisterSpotRequest.class);
			Logger.info(request.toString());
			
			SpotDAO dao = new JdbcSpotDAO(this);
			response.setSpot(dao.register(request.getSpot()));
			
			response.setCode(200);
			response.setDescription("Success");
								
		} catch (Exception e) {
			response.setCode(500);
			response.setDescription(e.getMessage());
		}
		Logger.info("<< "+response.toString());
		return response;
	}

	@GetMapping(value = "/fetch/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public FetchSpotResponse fetch(@PathVariable Long id) {
		FetchSpotResponse response = new FetchSpotResponse();
		try {
			Logger.info("==[/v1/spot/fetch/"+id+"]=");
							
			SpotDAO dao = new JdbcSpotDAO(this);
			response.setSpot(dao.fetch(id));
			
			response.setCode(200);
			response.setDescription("Success");
								
		} catch (Exception e) {
			response.setCode(500);
			response.setDescription(e.getMessage());
		}
		Logger.info("<< "+response.toString());
		return response;
	}
	

	@PostMapping(value = "/report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response report(@RequestBody String jsonRequest) {
		Logger.info("==[/v1/spot/report]=");
		Response response = new Response();
		try {
			ReportSpotRequest request = new ObjectMapper().readValue(jsonRequest.getBytes("UTF-8"), ReportSpotRequest.class);
			Logger.info(request.toString());
			
			SpotDAO dao = new JdbcSpotDAO(this);
			
			if(dao.hasReport(request.getSpotId(), request.getUserId(), request.getSpotReportTypeId())) {
				response.setCode(401);
				response.setDescription("User already reported");	
			}
			else {
				dao.report(request.getSpotId(), request.getUserId(), request.getSpotReportTypeId());
				if(request.getSpotReportTypeId() == SpotReportType.NOT_THERE && (
						dao.byUser(request.getSpotId(), request.getUserId()) ||
						 dao.countReports(request.getSpotId(), SpotReportType.NOT_THERE) > 2)) {
					dao.remove(request.getSpotId());
				}
				response.setCode(200);
				response.setDescription("Success");
			}
								
		} catch (Exception e) {
			response.setCode(500);
			response.setDescription(e.getMessage());
		}
		Logger.info("<< "+response.toString());
		return response;
	}

	@Override
	public void onErroJdbcDataAccessObject(Object sender, Exception e) {
		Logger.info(e.getMessage());
	}

	@Override
	public void onLogJdbcDataAccessObject(Object sender, String msg) {
		Logger.info(msg);
	}
}

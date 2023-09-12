/*
//talvez essa classe não seja usada jv
package com.pathwheel.filter;

*/
/*
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
*//*


import com.pathwheel.io.Logger;
import com.pathwheel.jdbc.JdbcDataAccessObjectListener;
import com.pathwheel.security.ApiKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RestrictedOperationsRequestFilter extends OncePerRequestFilter implements JdbcDataAccessObjectListener {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {

			Logger.info("key: "+request.getParameter("key"));

			//checking the API key
			if(!ApiKey.validateApiKey(request.getParameter("key")))
				throw new Exception("API key is incorrect");

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			Logger.info("<< filter exception: "+e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write(new com.pathwheel.response.Response(404, e.getMessage()).toString());
		}
	}

	@Override
	public void onErroJdbcDataAccessObject(Object sender, Exception e) {

	}

	@Override
	public void onLogJdbcDataAccessObject(Object sender, String msg) {
		Logger.info(msg);
	}
}
*/

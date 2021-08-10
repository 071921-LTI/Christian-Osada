package com.revature.delegates;

import java.io.IOException; 
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.PokemonNotFoundException;
import com.revature.models.Pokemon;
import com.revature.models.User;
import com.revature.services.PokemonService;
import com.revature.services.PokemonServiceImpl;

public class PokemonDelegate implements Delegatable{
	
	PokemonService ps = new PokemonServiceImpl();

	@Override
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// Retrieve GET, POST, PUT, DELETE...
		String method = rq.getMethod();

		switch (method) {
		case "GET":
			handleGet(rq, rs);
			break;
		case "POST":
			handlePost(rq, rs);
			break;
		case "PUT":
			handlePut(rq, rs);
			break;
		case "DELETE":
			handleDelete(rq, rs);
			break;
		default:
			rs.sendError(405);
		}
	}

	@Override
	public void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		/*
		 * TODO: 
		 * 		- if a path param is provided( /users/{id}) return user by an id
		 * 			- return 404 status code if not found
		 * 		- if no path param is provided( /users ) return all users
		 */
		// String passed through the request if any
		String pathNext = (String) rq.getAttribute("pathNext");

		if (pathNext != null) {
			try {
				Pokemon pokemon = ps.getPokemonById(Integer.valueOf(pathNext));
				try (PrintWriter pw = rs.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(pokemon));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			List<Pokemon> pokemons = ps.getPokemon();
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(pokemons));
			}
		}
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		Pokemon pokemon = new ObjectMapper().readValue(request, Pokemon.class);
		
		if (!ps.addPokemon(pokemon)) {
			rs.sendError(400, "Unable to add pokemon.");
		} else {
			rs.setStatus(201);
		}
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

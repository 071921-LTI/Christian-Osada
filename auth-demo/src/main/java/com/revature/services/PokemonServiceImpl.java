package com.revature.services;

import java.util.List;

import com.revature.daos.PokemonDao;
import com.revature.daos.PokemonPostgres;
import com.revature.models.Pokemon;

public class PokemonServiceImpl implements PokemonService {
	
	PokemonDao pd = new PokemonPostgres();
	
	public boolean addPokemon(Pokemon pokemon) {
		if(pd.addPokemon(pokemon) == -1) {
		return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Pokemon> getPokemon() {
		return pd.getPokemons();
	}

	@Override
	public Pokemon getPokemonById(int id) {
		Pokemon pokemon = null;
		pokemon = pd.getPokemonById(id);
		return pokemon;
	}

	@Override
	public List<Pokemon> getPokemonsByUserId(int user_id) {
		List<Pokemon>  pokemons = null;
		pokemons = pd.getPokemonsByUserId(user_id);
		return pokemons;
	}
}

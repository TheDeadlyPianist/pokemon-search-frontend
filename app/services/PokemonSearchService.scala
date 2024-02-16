package services

import connectors.PokemonSearchConnector
import models.{ErrorModel, PokemonSearchModel}

import javax.inject.Inject
import scala.concurrent.Future

class PokemonSearchService @Inject()(connector: PokemonSearchConnector) {

  def searchPokemon(pokemonName: String): Future[Either[ErrorModel, PokemonSearchModel]] = {
    connector.searchPokemon(pokemonName)
  }
  
}

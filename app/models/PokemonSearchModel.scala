package models

import play.api.libs.json.{Json, Reads}

case class PokemonSearchModel(name: String, frontSpriteUrl: String, typing: Seq[String])

object PokemonSearchModel {
  implicit val reads: Reads[PokemonSearchModel] = Json.reads[PokemonSearchModel]
}

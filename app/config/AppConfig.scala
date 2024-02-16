package config

import com.google.inject.ImplementedBy
import play.api.Configuration

import javax.inject.Inject

class AppConfigImpl @Inject()(configuration: Configuration) extends AppConfig {
  override val pokemonSearchUrl: String = configuration.get[String]("urls.pokemonSearch.pokemon")
}

@ImplementedBy(classOf[AppConfigImpl])
trait AppConfig {
  val pokemonSearchUrl: String
}

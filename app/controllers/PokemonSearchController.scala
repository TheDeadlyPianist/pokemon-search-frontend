package controllers

import play.api.mvc.{Action, AnyContent, MessagesBaseController, MessagesControllerComponents}
import services.PokemonSearchService
import views.html.index

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class PokemonSearchController @Inject()(
                                         val controllerComponents: MessagesControllerComponents,
                                         view: index,
                                         service: PokemonSearchService
                                       )(implicit ec: ExecutionContext) extends MessagesBaseController {
  
  def show(): Action[AnyContent] = Action { implicit request =>
    Ok(view())
  }
  
  def search(): Action[AnyContent] = Action.async { implicit request =>
    val formData = request.body.asFormUrlEncoded.getOrElse(Map())
    val pokemonForSearch = formData("pokemon-name").head.toLowerCase
    
    service.searchPokemon(pokemonForSearch).map {
      case Right(data) =>
        val modifiedData = data.copy(
          name = data.name.capitalize
        )
        Ok(view(Some(modifiedData)))
      case Left(errorModel) =>
        errorModel.status match {
          case NOT_FOUND => NotFound(view(
            pokemonName = Some(pokemonForSearch),
            notFound = true
          ))
          case _ => InternalServerError(errorModel.message)
        }
    }
  }

}

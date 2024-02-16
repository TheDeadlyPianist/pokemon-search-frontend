package models

import play.api.libs.json.{Json, Reads}

case class ErrorModel(status: Int, message: String)

object ErrorModel {
  implicit val reads: Reads[ErrorModel] = Json.reads[ErrorModel]
}

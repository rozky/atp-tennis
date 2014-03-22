package com.gambling.websites.atptennis.domain

import play.api.libs.json.Json

case class PlayerNameAutoComplete(fn: String, ln: String, pid: String)
object PlayerNameAutoComplete {
    implicit val json = Json.format[PlayerNameAutoComplete]
}

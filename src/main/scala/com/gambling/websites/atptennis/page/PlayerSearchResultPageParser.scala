package com.gambling.websites.atptennis.page

import org.jsoup.nodes.Element
import scala.collection.JavaConversions._
import com.gambling.websites.atptennis.page.PlayerSearchResultPageParser.Player

class PlayerSearchResultPageParser {

    def getPlayers(page: Element): List[Player] = {
        page.select("#searchResults .searchResult h3 a")
            .map(e => Player(e.text(), e.attr("href")))
            .toList
    }
}

object PlayerSearchResultPageParser {
    case class Player(name: String, profileUri: String)
}

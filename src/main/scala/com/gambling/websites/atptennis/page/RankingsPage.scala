package com.gambling.websites.atptennis.page

import org.jsoup.nodes.Element
import scala.collection.JavaConversions._
import com.rozky.common.web.extraction.html.HtmlUtils.removeNoBreakingSpaces

// parser page fragment
object RankingsPage {
    def parseRankings(page: Element): List[PlayerRanking] = {
        val players = page.select(".rankingsContent tbody tr")
            .filterNot(_.hasClass("bioTableHead"))
            .map(parsePlayerRow).toList

        players.foreach(println(_))
        players
    }

    def parsePlayerRow(html: Element): PlayerRanking = {
        val cells = html.select("td").toList

        new PlayerRanking(
            removeNoBreakingSpaces(cells(0).select("a").text()),
            removeNoBreakingSpaces(cells(0).select(".rank").text()),
            removeNoBreakingSpaces(cells(1).text()))
    }

    case class PlayerRanking(name: String, rank: String, points: String)
}

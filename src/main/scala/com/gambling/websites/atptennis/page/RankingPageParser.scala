package com.gambling.websites.atptennis.page

import org.jsoup.nodes.Element
import scala.collection.JavaConversions._
import com.rozky.common.web.extraction.html.HtmlUtils.removeNoBreakingSpaces
import com.gambling.websites.atptennis.domain.PlayerRanking
import java.util.regex.{Matcher, Pattern}

object RankingPageParser {
    /**
     * Format of the player name: [last_name], [first_name]
     */
    private val PLAYER_NAME_PATTERN = Pattern.compile("(.*),(.*)")

    def getPlayerRankings(page: Element): List[PlayerRanking] = {
        val players = page.select(".rankingsContent tbody tr")
            .filterNot(_.hasClass("bioTableHead"))
            .map(parsePlayerRow).toList

        players
    }

    def getLatestReleaseDate(page: Element): String = {
        page.select("#singlesDates option:nth-child(1)").text()
    }

    def parsePlayerRow(html: Element): PlayerRanking = {
        val cells = html.select("td").toList

        new PlayerRanking(
            asFirstNameLastName(removeNoBreakingSpaces(cells(0).select("a").text())),
            removeNoBreakingSpaces(cells(0).select(".rank").text()).toInt,
            removeNoBreakingSpaces(cells(1).text()),
            removeNoBreakingSpaces(cells(0).select("a").attr("href")))
    }

    private def asFirstNameLastName(name: String): String = {
        val matcher: Matcher = PLAYER_NAME_PATTERN.matcher(name)
        if (matcher.matches()) {
            matcher.group(2) + " " + matcher.group(1)
        } else {
            name
        }
    }
}

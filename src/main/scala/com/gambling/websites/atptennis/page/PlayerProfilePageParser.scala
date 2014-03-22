package com.gambling.websites.atptennis.page

import org.jsoup.nodes.Element
import java.util.regex.{Matcher, Pattern}
import PlayerProfilePageParser._

class PlayerProfilePageParser {
    def getPlayers(page: Element): PlayerDetails = {
        PlayerDetails(rank(page), birthDate(page))
    }

    private def rank(page: Element): Int = {
        page.select("#playerBioInfoRank span").text().toInt
    }

    private def birthDate(page: Element): String = {
        val birthDateItem: String = page.select("#playerBioInfoList li:nth-child(2)").text()
        val birthDateMatcher: Matcher = aroundBracketsPattern.matcher(birthDateItem)
        if (birthDateMatcher.find()) {
            birthDateMatcher.group(1)
        } else {
            ""
        }
    }
}

object PlayerProfilePageParser {
    val aroundBracketsPattern = Pattern.compile("\\((.*)\\)")
    case class PlayerDetails(rank: Int, birthDate: String)
}

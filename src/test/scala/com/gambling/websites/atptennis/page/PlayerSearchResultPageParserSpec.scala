package com.gambling.websites.atptennis.page

import org.scalatest.{Matchers, FlatSpec}
import com.gambling.websites.BaseSpec
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import com.gambling.websites.atptennis.page.PlayerSearchResultPageParser.Player

class PlayerSearchResultPageParserSpec extends BaseSpec {

    val parser = new PlayerSearchResultPageParser

    val pageFragment = """<div id="searchResults" class="clear">
                         |    <div class="searchResult  searchResultWithImage">
                         |        <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx"><img class="previewImg" src="#"/></a>
                         |        <p class="resultType">Player</p>
                         |        <h3><a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx">Rafael Nadal</a> (ESP)</h3>
                         |        <p>
                         |            Age: 27<br />
                         |            ATP Rank: 1
                         |        </p>
                         |        <p class="resultRelatedTags">
                         |            <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=pa">Playing Activity</a> |
                         |            <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=mr">Career Match Record</a> |
                         |            <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=tf">Career Titles/Finals</a>
                         |            <br /><a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=mf">YTD  ATP MatchFacts</a> |
                         |            <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=rh">Rankings History</a>
                         |            | <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=rb">Rankings Breakdown</a>
                         |        </p>
                         |    </div>
                         |    <div class="searchResult  searchResultWithImage">
                         |        <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx"><img class="previewImg" src="#" width="80" height="80" /></a>
                         |        <p class="resultType">Player</p>
                         |        <h3><a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx">Novak Djokovic</a> (SRB)</h3>
                         |        <p>
                         |            Age: 26<br />
                         |            ATP Rank: 2
                         |        </p>
                         |        <p class="resultRelatedTags">
                         |            <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx?t=pa">Playing Activity</a> |
                         |            <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx?t=mr">Career Match Record</a> |
                         |            <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx?t=tf">Career Titles/Finals</a>
                         |            <br /><a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx?t=mf">YTD  ATP MatchFacts</a> |
                         |            <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx?t=rh">Rankings History</a>
                         |            | <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx?t=rb">Rankings Breakdown</a>
                         |        </p>
                         |    </div>
                         |</div>"""

    it should "extract player names from the search result" in {
        // given
        val page: Document = Jsoup.parse(pageFragment)

        // when
        val players: List[Player] = parser.getPlayers(page)

        // then
        players should be(List(
            Player("Rafael Nadal", "/Tennis/Players/Top-Players/Rafael-Nadal.aspx"),
            Player("Novak Djokovic", "/Tennis/Players/Top-Players/Novak-Djokovic.aspx")))
    }
}

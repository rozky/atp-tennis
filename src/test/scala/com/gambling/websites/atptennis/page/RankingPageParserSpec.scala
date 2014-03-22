package com.gambling.websites.atptennis.page

import com.gambling.websites.BaseSpec
import org.jsoup.Jsoup
import com.gambling.websites.atptennis.domain.PlayerRanking

class RankingPageParserSpec extends BaseSpec {

    "parseRankings" should "parse player rankings" in {
        val htmlFragment =
            """
              |<table class="bioTableAlt stripeMe rankingsContent">
              |    <tbody>
              |    <tr class="bioTableHead oddRow">
              |        <td class="first">Rank, Name & Nationality</td>
              |        <td>Points</td>
              |        <td>Week Change</td>
              |        <td class="last">Tourn Played</td>
              |    </tr>
              |    <tr>
              |        <td class="first">
              |            <span class="rank">1</span>
              |            <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx">Nadal,&nbsp;Rafael</a>&nbsp;(ESP)</td>
              |        <td><a href="#">13,030</a></td>
              |        <td>0</td>
              |        <td class="last"><a href="#">20</a></td>
              |    </tr>
              |    <tr class="oddRow">
              |        <td class="first">
              |            <span class="rank">2</span>
              |            <a href="/Tennis/Players/Top-Players/Novak-Djokovic.aspx">Djokovic,&nbsp;Novak</a>&nbsp;(SRB)</td>
              |        <td><a href="#">12,260</a></td>
              |        <td>0</td>
              |        <td class="last"><a href="#">18</a></td>
              |    </tr>
              |</table>
            """.stripMargin

        // when
        val rankings = RankingPageParser.getPlayerRankings(Jsoup.parse(htmlFragment))

        // then
        rankings should be(List(
            PlayerRanking("Rafael Nadal", 1, "13,030", "/Tennis/Players/Top-Players/Rafael-Nadal.aspx"),
            PlayerRanking("Novak Djokovic", 2, "12,260", "/Tennis/Players/Top-Players/Novak-Djokovic.aspx")))
    }

    "parsePlayerRow" should "parse player ranking" in {
        // given
        val htmlFragment =
            """
              |<table>
              |<tr>
              |    <td class="first">
              |        <span class="rank">1</span>
              |        <a href="/Tennis/Players/Top-Players/Rafael-Nadal.aspx">Nadal,&nbsp;Rafael</a>&nbsp;(SLO)</td>
              |    <td><a href="#">13,030</a></td>
              |    <td>0</td>
              |    <td class="last"><a href="#">23</a></td>
              |</tr>
              |</table>
            """.stripMargin

        // when
        val playerRanking = RankingPageParser.parsePlayerRow(Jsoup.parse(htmlFragment))

        // then
        playerRanking should be(new PlayerRanking("Rafael Nadal", 1, "13,030", "/Tennis/Players/Top-Players/Rafael-Nadal.aspx"))
    }

    "getLatestReleaseDate" should "get latest ranking release date" in {
        // given
        val htmlFragment =
            """
              |<select name="d" id="singlesDates">
              |    <option value="17.03.2014">17.03.2014</option>
              |    <option value="03.03.2014">03.03.2014</option>
              |    <option value="24.02.2014">24.02.2014</option>
              |</select>
            """.stripMargin

        // when
        val latestReleaseDate = RankingPageParser.getLatestReleaseDate(Jsoup.parse(htmlFragment))

        // then
        latestReleaseDate should be("17.03.2014")
    }
}

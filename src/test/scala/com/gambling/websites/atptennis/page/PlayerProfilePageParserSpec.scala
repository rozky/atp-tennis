package com.gambling.websites.atptennis.page

import com.gambling.websites.BaseSpec
import org.jsoup.nodes.Document
import org.jsoup.Jsoup
import com.gambling.websites.atptennis.page.PlayerProfilePageParser.PlayerDetails

class PlayerProfilePageParserSpec extends BaseSpec {
    val parser = new PlayerProfilePageParser

    val pageFragment =
        """
          |<div id="playerBioInfoCardMain">
          |    <ul id="playerBioInfoList">
          |        <li><span>Pronounced:</span> ra-FAY-el nah-DAHL</li>
          |        <li><span>Age:</span> 27 (03.06.1986)</li>
          |
          |        <li><span>Birthplace:</span> Manacor, Mallorca, Spain</li>
          |        <li><span>Residence:</span> Manacor, Mallorca, Spain</li>
          |        <li><span>Height:</span> 6'1" (185 cm)</li>
          |        <li><span>Weight:</span> 188 lbs (85 kg)</li>
          |        <li><span>Plays:</span> Left-handed</li>
          |        <li><span>Turned Pro:</span> 2001</li>
          |        <li><span>Coach:</span> Toni Nadal</li>
          |        <li><span>Website:</span> <a href="http://www.rafaelnadal.com" target="_blank">http://www.rafaelnadal.com</a></li>
          |    </ul>
          |
          |    <div id="playerBioInfoExtra">
          |        <div id="playerBioInfoRank">
          |            <span>1</span>
          |            Singles Ranking
          |        </div>
          |
          |        <div id="playerBioInfoFlag">
          |            <img src="#" width="48" height="48"  title="Spain" alt="Spain" />
          |            <p>Spain</p>
          |        </div>
          |    </div>
          |</div>
        """.stripMargin

    it should "extract player details" in {
        // given
        val page: Document = Jsoup.parse(pageFragment)

        // when
        val players: PlayerDetails = parser.getPlayers(page)

        // then
        players should be(PlayerDetails(1, "03.06.1986"))
    }
}

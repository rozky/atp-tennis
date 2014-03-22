package it.com.gambling.websites.atptennis

import com.gambling.websites.atptennis.AtpTennisApi
import com.gambling.websites.BaseSpec
import org.apache.http.client.utils.URLEncodedUtils
import java.net.URLEncoder
import com.gambling.websites.atptennis.domain.PlayerRanking

class AtpTennisApiSpec extends BaseSpec {
    val api = new AtpTennisApi()

    it should "get player id" in {
        api.getPlayerId("Rafael Nadal") should be(Some("N409"))
    }

    it should "get matches played by the player in a selected year" in {
        api.getPlayerMatches("Rafael-Nadal")
    }

    it should "get player rankings" in {
        // when
        val ranking: List[PlayerRanking] = api.rankingApi.singlesRanking(200)

        // then
//        ranking.foreach(println(_))
        ranking.size should be(200)
    }
}

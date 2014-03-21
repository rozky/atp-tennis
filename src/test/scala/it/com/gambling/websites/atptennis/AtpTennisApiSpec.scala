package it.com.gambling.websites.atptennis

import com.gambling.websites.atptennis.AtpTennisApi
import com.gambling.websites.BaseSpec

class AtpTennisApiSpec extends BaseSpec {
    val api = new AtpTennisApi()

    it should "get player id" in {
        api.getPlayerId("Rafael Nadal") should be("N409")
    }

    it should "get matches played by the player in a selected year" in {
        api.getPlayerMatches("Rafael-Nadal")
    }

    it should "get player rankings" in {
        api.getSingleRankings
    }
}

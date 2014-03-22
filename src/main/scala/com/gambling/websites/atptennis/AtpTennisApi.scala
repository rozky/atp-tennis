package com.gambling.websites.atptennis

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import com.gambling.websites.atptennis.page.{SitePaths, RankingPageParser, PlayerActivityPage}
import com.gambling.websites.atptennis.page.PlayerActivityPage.MatchResult
import com.rozky.common.http.client.{HttpClient, JsonHttpClient}
import java.net.URLEncoder
import com.gambling.websites.atptennis.domain.{PlayerRanking, PlayerNameAutoComplete}

class AtpTennisApi {
    
    val httpClient: HttpClient = new JsonHttpClient
    val rankingApi = new RankingApi

    def getPlayerMatches(name: String): List[MatchResult] = {
        val playerActivitiesPage = Jsoup.connect(SitePaths.TOP_PLAYER_PROFILE(name)).get()
        val tournaments = playerActivitiesPage
            .select(".commonProfileContainer")
            .filter(PlayerActivityPage.isTournamentFragment)

        tournaments.map(PlayerActivityPage.parseTournamentTable).map(t => {
            t.matches.filterNot(_.isBey).reverse
        }).flatten.toList
    }

    def getPlayerMatches() {
        val playerName = "Rafael-Nadal"
        //        val playerName = "Roger-Federer"
        //        val playerName = "Novak-Djokovic"
        //        val playerName = "John-Isner"
        //        val playerName = "Ryan-Harrison"
        val playerActivitiesPage = Jsoup.connect(SitePaths.TOP_PLAYER_PROFILE(playerName)).get()
        val tournaments = playerActivitiesPage
            .select(".commonProfileContainer")
            .filter(PlayerActivityPage.isTournamentFragment)

        var count = 0
        tournaments.map(PlayerActivityPage.parseTournamentTable).map(t => {
            val string: String = t.matches.map(m => {
                if (!m.isBey) {
                    count += 1
                }
                if (m.isWin) {
                    if (m.isFinalWin) {
                        "WW"
                    } else {
                        "W"
                    }
                } else {
                    "L"
                }
            }).mkString("-")

            t.info.points + "-" + {string}
        }).foreach(e => println(e + ",1"))

        println("count = " + count)
    }

    def getSinglesRanking: List[PlayerRanking] = {
        val rankPage = Jsoup.connect(SitePaths.RANKING_SINGLES).get()
        RankingPageParser.getPlayerRankings(rankPage)
    }

    def getPlayerId(name: String): Option[String] = {
        val encodedName: String = URLEncoder.encode(name, "utf-8")
        val response = httpClient.get[List[PlayerNameAutoComplete]](SitePaths.AUTO_COMPLETE(encodedName))

        if (!response.isEmpty) {
            if (response.size == 1) {
                return Some(response.head.pid)
            } else {
                throw new IllegalArgumentException(s"expecting max 1 result but found ${response.size}")
            }
        }

        None
    }

    def getPlayerRanking(name: String): Option[Int] = {
        None
    }


}


package com.gambling.websites.atptennis

import com.gambling.websites.atptennis.domain.PlayerRanking
import org.jsoup.Jsoup
import com.gambling.websites.atptennis.page.{RankingPageParser, SitePaths}

// todo - inject JSOP so this class can be tested
class RankingApi {
    private val recordsPerPage = 100

    /**
     * Get the release date for the current ranking
     * @return the current ranking release date
     */
    def lastReleaseDate: String = {
        val page = Jsoup.connect(SitePaths.RANKING_SINGLES).get()
        RankingPageParser.getLatestReleaseDate(page)
    }

    /**
     * Gets singles ranking up to the supplied limit (rounded up to hundreds, for example for limit 202 it returns 300)
     * @param limit max rank to get
     * @return the singles ranking
     */
    def singlesRanking(limit: Int): List[PlayerRanking] = {
        getSinglesRanking(1, limit)
    }

    private def getSinglesRanking(from: Int, limit: Int): List[PlayerRanking] = {
        val rankingsPage = Jsoup.connect(SitePaths.RANKING_SINGLES(from)).get()
        val rankings: List[PlayerRanking] = RankingPageParser.getPlayerRankings(rankingsPage)
        val nextPageStartRank = from + recordsPerPage
        if (nextPageStartRank <= limit) {
            rankings ::: getSinglesRanking(nextPageStartRank, limit)
        } else {
            rankings
        }
    }
}

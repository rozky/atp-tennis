package com.gambling.websites.atptennis.page

object SitePaths {
    val BASE_URL: String = "http://www.atpworldtour.com"
    val RANKING_SINGLES: String = s"$BASE_URL/Rankings/Singles.aspx"
    
    def TOP_PLAYER_PROFILE(playerName: String): String = s"$BASE_URL/Tennis/Players/Top-Players/$playerName.aspx?t=pa"
}

package com.gambling.websites.atptennis.page

object SitePaths {
    val BASE_URL: String = "http://www.atpworldtour.com"
    val RANKING_SINGLES: String = s"$BASE_URL/Rankings/Singles.aspx"
    def RANKING_SINGLES(from: Int): String = s"$RANKING_SINGLES?r=$from"

    def TOP_PLAYER_PROFILE(playerName: String): String = s"$BASE_URL/Tennis/Players/Top-Players/$playerName.aspx?t=pa"
    def AUTO_COMPLETE(value: String) = s"$BASE_URL/Handlers/AutoComplete.aspx?q=$value"
    def PLAYER_SEARCH(text: String) = s"$BASE_URL/Search/Players.aspx?q=$text"
}

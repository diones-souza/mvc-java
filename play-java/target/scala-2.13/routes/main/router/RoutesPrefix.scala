// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Documents/GitHub/mvc-java/play-java/conf/routes
// @DATE:Tue Feb 04 20:25:27 VET 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
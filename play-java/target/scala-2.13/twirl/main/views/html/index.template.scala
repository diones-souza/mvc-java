
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Http.Request,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(implicit request: Http.Request):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.34*/("""

"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
    """),format.raw/*4.5*/("""<script type='text/javascript'></script>

    <ul id="persons"></ul>

    <form method="POST" action=""""),_display_(/*8.34*/routes/*8.40*/.PersonController.addPerson()),format.raw/*8.69*/("""" class="form-inline">
        """),_display_(/*9.10*/helper/*9.16*/.CSRF.formField),format.raw/*9.31*/("""
            """),format.raw/*10.13*/("""<div class="form-group mb-2">
                <input type="text" class="form-control" name="name" placeholder="Nome">
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <input type="number" class="form-control" name="age" placeholder="Idade">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Salvar</button>
    </form>
""")))}))
      }
    }
  }

  def render(request:Http.Request): play.twirl.api.HtmlFormat.Appendable = apply(request)

  def f:((Http.Request) => play.twirl.api.HtmlFormat.Appendable) = (request) => apply(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-05T01:49:41.624681800
                  SOURCE: D:/Documents/GitHub/mvc-java/play-java/app/views/index.scala.html
                  HASH: 6e426d4824bc0bd992bc2296bd804e648330d781
                  MATRIX: 914->1|1041->33|1071->38|1102->61|1141->63|1173->69|1306->176|1320->182|1369->211|1428->244|1442->250|1477->265|1519->279
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|39->8|39->8|39->8|40->9|40->9|40->9|41->10
                  -- GENERATED --
              */
          
package vrhack

import com.softwaremill.macwire._
import com.typesafe.config.Config
import play.api.ApplicationLoader.Context
import play.api.mvc.{ControllerComponents, DefaultControllerComponents, EssentialFilter}
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, _}
import play.filters.cors.CORSComponents

import scala.concurrent.ExecutionContext
import router.Routes

class VrHackApp extends ApplicationLoader {

  def load(context: Context): Application = {
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
    }

    val components = new BuiltInComponentsFromContext(context) with VrHackComponents
    val application = components.application

    application
  }

}

trait VrHackComponents extends BuiltInComponents with CORSComponents {

  implicit lazy val executor: ExecutionContext = actorSystem.dispatcher

  private lazy val controllerComponents: ControllerComponents = DefaultControllerComponents(defaultActionBuilder, playBodyParsers, messagesApi, langs, fileMimeTypes, executionContext)

  implicit lazy val config: Config = configuration.underlying

  lazy val videoController = new VideoController(controllerComponents)

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

  override lazy val httpFilters: Seq[EssentialFilter] = Seq(corsFilter)

}

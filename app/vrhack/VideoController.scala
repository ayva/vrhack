package vrhack

import java.io.File

import play.api.mvc.{BaseController, ControllerComponents}

import scala.concurrent.ExecutionContext

class VideoController(override val controllerComponents: ControllerComponents)(implicit executionContext: ExecutionContext) extends BaseController {

  def get(name: String) = Action {
    val file = new File("./video/" + name)
    if (file.exists()) {
      Ok.sendFile(file)
    } else {
      NotFound
    }
  }

}

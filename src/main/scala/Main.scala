import zio._
import zio.http._
import zio.http.Middleware.{CorsConfig, cors}
import zio.http.Header.{AccessControlAllowOrigin, Origin}
import zio.json._
import zio.schema.{Schema, DeriveSchema}
import zio.schema.codec.JsonCodec.schemaBasedBinaryCodec
import scala.io.Source
import scala.util.Random

case class NoResponse(reason: String)

object NoResponse {
  given Schema[NoResponse] = DeriveSchema.gen[NoResponse]
}

object Service extends ZIOAppDefault {
  val HOST = "0.0.0.0"
  val PORT = 3000

  val reasons = Source
    .fromFile("src/main/resources/reasons.json")
    .mkString
    .fromJson[List[String]]
    .left
    .map(new Exception(_))
    .toTry()
    .get

  val noRoute =
    Method.GET / "no" -> handler(
      Response(body =
        Body.from(NoResponse(reasons(Random.nextInt(reasons.length))))
      )
    )

  val config: CorsConfig = CorsConfig(
    allowedOrigin = _ => Some(AccessControlAllowOrigin.All)
  )

  val service = Routes(noRoute) @@ cors(config)

  override val run =
    Server
      .serve(service)
      .provide(Server.defaultWith(_.binding(HOST, PORT)))
}

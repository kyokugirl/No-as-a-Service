val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    name := "No-as-a-Service",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
      "dev.zio" %% "zio-http" % "3.2.0",
      "dev.zio" %% "zio-json" % "0.7.42"
    ),
    graalVMNativeImageOptions ++= Seq(
      "--no-fallback",
      "--enable-http",
      "--enable-url-protocols=http,https",
      "--install-exit-handlers",
      "-Djdk.http.auth.tunneling.disabledSchemes=",
      "--initialize-at-run-time=io.netty.channel.DefaultFileRegion",
      "--initialize-at-run-time=io.netty.channel.epoll.Native",
      "--initialize-at-run-time=io.netty.channel.epoll.Epoll",
      "--initialize-at-run-time=io.netty.channel.epoll.EpollEventLoop",
      "--initialize-at-run-time=io.netty.channel.epoll.EpollEventArray",
      "--initialize-at-run-time=io.netty.channel.kqueue.KQueue",
      "--initialize-at-run-time=io.netty.channel.kqueue.KQueueEventLoop",
      "--initialize-at-run-time=io.netty.channel.kqueue.KQueueEventArray",
      "--initialize-at-run-time=io.netty.channel.kqueue.Native",
      "--initialize-at-run-time=io.netty.channel.unix.Limits",
      "--initialize-at-run-time=io.netty.channel.unix.Errors",
      "--initialize-at-run-time=io.netty.channel.unix.IovArray",
      "--initialize-at-run-time=io.netty.handler.ssl.BouncyCastleAlpnSslUtils",
      "--initialize-at-run-time=io.netty.handler.codec.compression.ZstdOptions",
      "--initialize-at-run-time=io.netty.incubator.channel.uring.Native",
      "--initialize-at-run-time=io.netty.incubator.channel.uring.IOUring",
      "--initialize-at-run-time=io.netty.incubator.channel.uring.IOUringEventLoopGroup"
    )
  )

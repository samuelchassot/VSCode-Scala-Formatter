
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ch.chassot",
      scalaVersion := "2.12.7",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "vs-scala-formatter",
    
    resolvers += "repo-for-lsp4j" at "https://repo.maven.apache.org/maven2/org/eclipse/lsp4j/",
    libraryDependencies += "com.geirsson" % "scalafmt-core_2.12" % "SNAPSHOT",
    libraryDependencies += "org.scalameta" % "scalameta-experimental_2.12" % "SNAPSHOT",
    libraryDependencies += "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.6.0"
  )
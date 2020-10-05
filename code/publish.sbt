publishMavenStyle := true

publishTo := {
  val jfrog = "https://lcdp.jfrog.io/artifactory/"
  if (isSnapshot.value)
    Some("Artifactory Realm" at jfrog + "sbt-dev")
  else
    Some("Artifactory Realm"  at jfrog + "sbt-release")
}
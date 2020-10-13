publishMavenStyle := true

publishTo := {
    val jfrog = "https://lcdp.jfrog.io/artifactory/"
  if (isSnapshot.value)
    Some("snapshots" at jfrog + "sbt-dev")
  else
    Some("releases"  at jfrog + "sbt-release")
}

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

homepage := Some(url("http://deadbolt.ws"))

licenses := Seq("Apache 2" -> url("http://opensource.org/licenses/Apache-2.0"))

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:schaloner/deadbolt-2-java.git</url>
    <connection>scm:git:git@github.com:schaloner/deadbolt-2-java.git</connection>
  </scm>
    <developers>
      <developer>
        <id>schaloner</id>
        <name>Steve Chaloner</name>
        <url>http://objectify.be</url>
      </developer>
    </developers>)

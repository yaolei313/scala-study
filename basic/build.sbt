organization:="com.yao.app"
version:="1.0.0"
scalaVersion:="2.11.7"

name := "scala-basic"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
EclipseKeys.withSource := true
EclipseKeys.withBundledScalaContainers := false

libraryDependencies ++= Seq(
	"junit" % "junit" % "4.12" % "test",
	"com.google.guava" % "guava" % "19.0",
	"org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

// "Local Maven Repository" at "file:///"+Path.userHome+"/.m2/repository",
resolvers ++= Seq(
	"Maven Repository" at "http://repo.maven.apache.org/maven2",
	"Apache Repository" at "https://repository.apache.org/content/repositories/releases",  
	"JBoss Repository" at "https://repository.jboss.org/nexus/content/repositories/releases/",  
	"MQTT Repository" at "https://repo.eclipse.org/content/repositories/paho-releases/",  
	"Cloudera Repository" at "http://repository.cloudera.com/artifactory/cloudera-repos/", 
	Resolver.mavenLocal
)

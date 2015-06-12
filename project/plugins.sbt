addSbtPlugin("org.scalariform" % "sbt-scalariform"       % "1.5.0")
addSbtPlugin("org.scalastyle"  % "scalastyle-sbt-plugin" % "0.7.0")
addSbtPlugin("org.scoverage"   % "sbt-scoverage"         % "1.3.1")

// Temporary workaround until https://github.com/scoverage/sbt-scoverage/issues/125 is fixed:
resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

version := "0.1"

ThisBuild / envFileName := "build.env"

Test / envFileName := "test.env"

fork in test := true

TaskKey[Unit]("checkGlobal") :=  {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String = IO.read(lastLog)
  val contains = last.contains(".env file not found (fileName=build.env), no .env environment configured.")
  if (!contains)
    sys.error("expected log message")
}

TaskKey[Unit]("checkTest") :=  {
  val lastLog: File = BuiltinCommands.lastLogFile(state.value).get
  val last: String = IO.read(lastLog)
  val contains = last.contains(".env detected (fileName=test.env)")
  if (!contains)
    sys.error("expected log message")

}
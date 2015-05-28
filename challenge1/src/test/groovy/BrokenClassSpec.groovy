class BrokenClassSpec extends spock.lang.Specification {
  def buf
  def newOut
  def savedOut
  def LS = System.getProperty('line.separator')

  def setup() {
    buf = new ByteArrayOutputStream()
    newOut = new PrintStream(buf)
    savedOut = System.out
    System.out = newOut
  }

  def cleanup() {
    System.out = savedOut
    newOut.close()
  }

  def "Test BrokenClass definition"() {
    when: "We try to instantiate a BrokenClass object"
      new BrokenClass()
    then: "Nothing blows up"
  }

  def "Test calling main method"() {
    when: "We try to call the main method"
      BrokenClass.main()
    then: "It should exist"
  }

  def "Test printed output from main method"() {
    when: "We try to capture standard out from main method"
      BrokenClass.main()
    then: "Output should be what we expect with OS-specific line separator"
      buf.toString() == "Hello world!${LS}"
  }
}  
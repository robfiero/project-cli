# project-cli
This is a Java implementation of a command line interface (CLI) that will be generic and can be shared across projects.

## The intent on this project was to create a Java based CLI that I could then use with various projects as a front end.  I'd eventually like to add UI support, which in my mind will require a client-server structure with REST interfaces on the back end that can implement any needed queries or CRUD operations.

## The prompt I used to start generating the project:
"I would like to create java source files that implement a command line interface. I would like it to be able to do the following: Once launched, it should read user input from the command line, execute the requested command, and display any return values. This should be a generic command line interface that can be reused with future projects. A feature should be tab completion. If the user hits tab, available commands should be listed. If any user input exists, that should be used to narrow the choices available. If an example program is necessary, please generate one that can be used to demonstrate the command line interface."

It is worth noting that at the time I (in hindsight, foolishly) generated these files inside ChatGPT in my browser, which meant that I had to copy the code and save the files myself.  This was, as you'd guess, really awkward.  It wasn't long after that I integrated ChatGPT Codex into my VS Code IDE, signed into my ChatGPT paid account, and then found projects far easier to manage.

## After the initial version was created, I needed to do some refactoring, including:
- Confirm the suggestion that integrating the **JLine** third party library was a good idea, and reworked the code to integrate that library's capabilities.
- I wanted to repackage some files and found that doing so was tedious given that I'd already saved the first version in my filesystem.  (See note about how in hindsight integrating Codex was a good idea!)
- Set up the build process using Maven.
- Found a JLine API error and had the AI fix it.  (The error was: The method readLine(Character) in the type LineReader is not applicable for the arguments (AttributedString))
- Fixed a few runtime errors.
- Added convenience scripts including build.sh, run.sh, clean.sh
- Played around with some checkstyle warnings.
- Added JUnit tests, which required several fixes including to the POM.xml file.
- Added a test.sh script.
- Then refactored the code so that I could build a reusable JAR file that didn't include tests, etc.  This is where I finally caught on to install Codex!  That made life SOOOOO much easier!  Rather than a lot of manual steps and code changes, which I didn't do correctly and I made a mess with, Codex did the refactor in seconds.

I definitely learned a number of things from this exercise, not the least of which was how dumb it was to copy source files from the browser into either the IDE or file system manually!  I also think in hindsight I could have been quicker had I included the requirement up front to be able to produce a JAR file and unit tests.

All in all this probject was a very good learning experience, and I'm sure if I were to do it again I could produce a working product in less time.  I think this too me most of a day, which actually isn't bad seeing as I haven't written a CLI type project in a couple of decades.

## Here is the output from my code metrics tool, redacted for security.

```Root: /<redacted>/Coding Projects/project-cli/project-cli
Profile: all
Files counted: 30
Total size: 33.9 KB
Text files skipped (binary/unreadable): 0
Tool files excluded: 2

Line counts (heuristic):
  Total:   1217
  Code:    903
  Comment: 98
  Blank:   216

By extension:
  .java      files=    21  lines=      927  code=      666  cmt=       90  blank=      171
  .xml       files=     4  lines=      207  code=      181  cmt=        0  blank=       26
  .sh        files=     4  lines=       81  code=       54  cmt=        8  blank=       19
  .md        files=     1  lines=        2  code=        2  cmt=        0  blank=        0

Top 10 largest files:
     5.6 KB  cli-framework/src/main/java/cli/CliEngine.java
     2.7 KB  checkstyle.xml
     2.0 KB  pom.xml
     2.0 KB  cli-framework/src/main/java/cli/TerminalUi.java
     1.9 KB  cli-framework/src/main/java/cli/Tokenizer.java
     1.6 KB  cli-framework/src/main/java/cli/completion/CommandCompleter.java
     1.4 KB  cli-demo/src/main/java/cli/commands/GreetCommand.java
     1.4 KB  cli-framework/src/test/java/cli/commands/TestGreetCommand.java
     1.2 KB  cli-framework/src/test/java/cli/CliEngineDispatchTest.java
     1.2 KB  cli-demo/src/main/java/cli/commands/AddCommand.java

Top 10 longest files (by total lines):
        157 lines  cli-framework/src/main/java/cli/CliEngine.java
         79 lines  checkstyle.xml
         67 lines  pom.xml
         65 lines  cli-framework/src/main/java/cli/TerminalUi.java
         64 lines  cli-framework/src/main/java/cli/Tokenizer.java
         57 lines  cli-demo/src/main/java/cli/commands/GreetCommand.java
         55 lines  cli-framework/src/test/java/cli/commands/TestGreetCommand.java
         52 lines  cli-framework/src/main/java/cli/completion/CommandCompleter.java
         45 lines  cli-demo/src/main/java/cli/commands/AddCommand.java
         44 lines  cli-framework/src/test/java/cli/commands/TestSumCommand.java
```


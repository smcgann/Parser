import java.util.ArrayList;

public class Parser{
  private Token currentToken;
  Scanner scanner;

  private void accept(byte expectedKind) {
    if (currentToken.kind == expectedKind)
      currentToken = scanner.scan();
    else
      new Error("Syntax error: " + currentToken.spelling + " is not expected.",
                currentToken.line);
  }

  private void acceptIt() {
    currentToken = scanner.scan();
  }

  public void parse() {
    SourceFile sourceFile = new SourceFile();
    scanner = new Scanner(sourceFile.openFile());
    ArrayList<Token> fileAsStack = new ArrayList<Token>();
    currentToken = scanner.scan();
	fileAsStack.add(currentToken);
    while (currentToken.kind != 13) {
    	currentToken = scanner.scan();
    	fileAsStack.add(currentToken);
    }
    //needs to check each token, this code only checks one
    //parseProgram();
    //if (currentToken.kind != Token.EOT)
    	//new Error("Syntax error: Redundant characters at the end of program.",currentToken.line);
  }

  //Program" --> "("Sequence State")".
  private void parseProgram() {
	  //check for first "(" if found call parseSequence()
	  if (currentToken.kind == 9){
		  parseSequence();
	  }
	  else {
		  //something
	  }
  }

  //Sequence --> "("Statements")".
  private void parseSequence(){
	//check for first "(" if found call parseStatements(), or parseState()? idk what how to check
	  if (currentToken.kind == 9){
		  parseSequence();
	  }
	  else {
		  //something
	  }
  }

  //Statements --> Stmt*
  private void parseStatements(){
	//check for Stmt if found call parseStmt()
  }

  //Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
  private void parseStmt(){
	//check if "skip", "asign", "conditional", "loop", "block", if found call parseSequence()
  }

  //State -->  "("Pairs")".
  private void parseState(){
  }

  //Pairs --> Pair*.
  private void parsePairs(){
  }

  //Pair --> "("Identifier Literal")".
  private void parsePair(){
  }

  //NullStatement --> skip.
  private void parseNullStatement(){
  }

  //Assignment --> "assign" Identifier Expression.
  private void parseAssignment(){
  }

  //Conditional --> "conditional" Expression Stmt Stmt.
  private void parseConditional(){
  }

  //Loop --> "loop" Expression Stmt.
  private void parseLoop(){
  }

  //Block --> "block" Statements.
  private void parseBlock(){
  }

  //Expression --> Identifier | Literal | "("Operation Expression Expression")".
  private void parseExpression(){
  }

  //Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".
  private void parseOperation(){
  }
}

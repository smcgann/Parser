import java.io.*;

public class Scanner{
  private char currentChar;
  private byte currentKind;
  private StringBuffer currentSpelling;
  private BufferedReader inFile;
  private static int line = 1;

  public Scanner(BufferedReader inFile){
    this.inFile = inFile;
    try{
      int i = this.inFile.read();
      if(i == -1) //end of file
        currentChar = '\u0000';
      else
        currentChar = (char)i;
    }
    catch(IOException e){
        System.out.println(e);
    }
  }

  private void takeIt(){
    currentSpelling.append(currentChar);
    try{
        int i = inFile.read();
        if(i == -1) //end of file
          currentChar = '\u0000';
        else
          currentChar = (char)i;
      }
      catch(IOException e){
          System.out.println(e);
      }
  }

  private void discard(){
    try{
      int i = inFile.read();
      if(i == -1) //end of file
        currentChar = '\u0000';
      else
        currentChar = (char)i;
    }
    catch(IOException e){
        System.out.println(e);
    }
  }

  private byte scanToken(){
	  
	  switch(currentChar) {
	  
	  	case '(':
	  		takeIt();
	  		return 9;
	  		
	  	case ')':
	  		takeIt();
	  		return 10;
	  		
	  	case '+':case '-':case '*':case '/':case '=':
	  		takeIt();
	  		return 11;
	  		
	  	case '>':case '<':case '!':
	  		char saveLast = currentChar;
	  		takeIt();
	  		if (currentChar != '=' && saveLast == '!') {
	  			new Error("Wrong Token = " + currentSpelling, line);
	  		}
	  		else if(currentChar == ' ') {
	  			return 11;
	  		}
	  		else {
	  			takeIt();
	  			return 11;
	  		}
	  		
	  	default:
	  		if(isDigit(currentChar) == true) {
	  			while(currentChar != ' ' && currentChar != '\r' && isDigit(currentChar) != false) {
	  				takeIt();
	  			}
	  			return 1;
	  		}
	  		
	  		else if(isLetter(currentChar) == true) {
	  			while(currentChar != ' ' && currentChar != '\r' && isLetter(currentChar) != false) {
	  				takeIt();
	  			}
	  			switch(currentSpelling.toString()){
  				case "assign":
  					return 2;
  				case "conditional":
  					return 3;
  				case "loop":
  					return 4;
  				case "block":
  					return 5;
  				case "skip":
  					return 6;
  				case "and":
  					return 7;
  				case "or":
  					return 8;			  		
  				case "???":
  					return 12;
  				default:
  					return 0;
	  			}	  			
	  			}  	
	  		
	  		else {
	  			return 13;
	  		}
	  }
	  		
	  		
	  }
	  
  private void scanSeparator(){
    switch(currentChar){
      case ' ': case '\n': case '\r': case '\t':
        if(currentChar == '\n')
          line++;
        discard();
    }
  }

  public Token scan(){
    currentSpelling = new StringBuffer("");
    while(currentChar == ' ' || currentChar == '\n' || currentChar == '\r') 
      scanSeparator();
    currentKind = scanToken();
    System.out.println("Line: " + line + ", current token is [" + currentSpelling + "], current kind is " + currentKind);
    return new Token(currentKind, currentSpelling.toString(), line);
  }

  private boolean isGraphic(char c){
    return c == '\t' ||(' ' <= c && c <= '~');
  }

  private boolean isDigit(char c){
    return '0' <= c && c <= '9';
  }

  private boolean isLetter(char c){
    return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
  }
}

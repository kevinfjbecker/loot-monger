package fluxx;

import java.io.* ;

public class MenuMachine {
    
    public static int solicitMenuChoice(String[] options) throws IOException {
    	int response = 0 ;
    	boolean numberFormatExceptionDetected = false ;
    	
	BufferedReader br ;
	br = new BufferedReader( new InputStreamReader( System.in ) ) ;
	
	do {
	    System.out.println( "<--- Menu Options --->" ) ;
	    
	    showMenu( options ) ;
	    
	    System.out.print( "enter menu selection(0-" +
			      (options.length - 1) + "): " ) ;
	    
	    try {
		
		response = Integer.parseInt( br.readLine( ) ) ;
		numberFormatExceptionDetected = false ;
		
	    } catch ( NumberFormatException e ) {
		System.out.println( "that didn't seem like an " +
				    "integer let's try again." ) ;
		numberFormatExceptionDetected = true ;
	    }
	    
	    if( response < 0 || response >= options.length ) {
		System.out.println( "oops. that seems to be out " + 
				    "of range. let's try again." ) ;
		    response = solicitMenuChoice( options ) ;
			}
	} while( numberFormatExceptionDetected ) ;
	
	System.out.println( ) ;
	
	return( response ) ;	
    }
    
    private static void showMenu( String[ ] options ) {
	for( int i = 0 ; i < options.length ; i++ )
	    System.out.println( i + ": " + options[ i ] ) ;
    }
}

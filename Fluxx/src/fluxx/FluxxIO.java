package fluxx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FluxxIO {

	private BufferedReader _reader;
	
	public FluxxIO(){
		_reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String readLine() {
		try {
			return _reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null; // unreachable
	}
}

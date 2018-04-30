import java.io.FileWriter;
import java.io.PrintWriter;
import enums.*;

public class CodeGeneration extends Visitor{

	private PrintWriter writer;
	public CodeGeneration() {
		writer = new PrintWriter(new FileWriter());
	}
}

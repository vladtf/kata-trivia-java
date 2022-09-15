package trivia.cr;

import java.util.Arrays;

public class PopSubjectCR extends SubjectCR {

	public PopSubjectCR() {
		super();
		this.name = ConstantsCR.POP;
		this.subjectPositions = Arrays.asList(0, 4, 8);
		//#CR
//		this.getPropertiesFile();
	}
}

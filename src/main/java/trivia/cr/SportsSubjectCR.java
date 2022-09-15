package trivia.cr;

import java.util.Arrays;

public class SportsSubjectCR extends SubjectCR {

	public SportsSubjectCR() {
		super();
		this.name = ConstantsCR.SPORTS;
		this.subjectPositions = Arrays.asList(2, 6, 10);
		//#CR
//		this.getPropertiesFile();
	}
}

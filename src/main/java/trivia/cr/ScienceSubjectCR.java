package trivia.cr;

import java.util.Arrays;

public class ScienceSubjectCR extends SubjectCR {

	public ScienceSubjectCR() {
		super();
		this.name = ConstantsCR.SCIENCE;
		this.subjectPositions = Arrays.asList(1, 5, 9);
		//#CR
//		this.getPropertiesFile();
	}
}

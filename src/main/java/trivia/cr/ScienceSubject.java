package trivia.cr;

import java.util.Arrays;

public class ScienceSubject extends Subject {

	public ScienceSubject() {
		super();
		this.name = Constants.SCIENCE;
		this.subjectPositions = Arrays.asList(1, 5, 9);
		//#CR
//		this.getPropertiesFile();
	}
}

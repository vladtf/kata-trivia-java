package trivia.cr;

import trivia.cr.SubjectCR;

import java.util.Arrays;

public class GeographySubjectCR extends SubjectCR {
	
	public GeographySubjectCR() {
		super();
		this.name = GEOGRAPHY;
		this.subjectPositions = Arrays.asList(11);
		this.getPropertiesFile();
	}

}

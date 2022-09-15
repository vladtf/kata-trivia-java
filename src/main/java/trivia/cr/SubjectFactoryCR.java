package trivia.cr;


public class SubjectFactoryCR implements ConstantsCR {

	public SubjectCR createSubject(String subjectType) {
		
		if (subjectType == null) {
			return null;
		}
		
		if (subjectType.equalsIgnoreCase(POP)) {
			return new PopSubjectCR();
		} else if (subjectType.equalsIgnoreCase(SCIENCE)) {
			return new ScienceSubjectCR();
		} else if (subjectType.equalsIgnoreCase(SPORTS)) {
			return new SportsSubjectCR();
		} else if (subjectType.equalsIgnoreCase(ROCK)) {
			return new RockSubjectCR();
		} else if (subjectType.equalsIgnoreCase(GEOGRAPHY)) {
			return new GeographySubjectCR();
		}
		
		return null;
	}
}

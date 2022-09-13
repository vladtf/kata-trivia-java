package trivia;

public enum Category {
    Pop{
        @Override
        public String buildQuestion(int index) {
            return "Pop Question " + index;
        }
    }, Rock {
        @Override
        public String buildQuestion(int index) {
            return "Rock Question " + index;
        }
    }, Science {
        @Override
        public String buildQuestion(int index) {
            return "Science Question " + index;
        }
    }, Sports {
        @Override
        public String buildQuestion(int index) {
            return "Sports Question " + index;
        }
    };


    public abstract String buildQuestion(int index);


    public static Category fromPlaceToCategory(int place) {
        return switch (place) {
            case 0, 4, 8 -> Pop;
            case 1, 5, 9 -> Science;
            case 2, 6, 10 -> Sports;
            default -> Rock;
        };
    }
}

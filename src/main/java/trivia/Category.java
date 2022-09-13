package trivia;

public enum Category {
    Pop, Science, Sports, Rock;


    public static Category fromPlaceToCategory(int place) {
        return switch (place) {
            case 0, 4, 8 -> Pop;
            case 1, 5, 9 -> Science;
            case 2, 6, 10 -> Sports;
            default -> Rock;
        };
    }
}

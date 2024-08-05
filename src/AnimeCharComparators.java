import java.util.Comparator;

public class AnimeCharComparators {
    public static final Comparator<AnimeChar> BY_NAME = Comparator.comparing(AnimeChar::getName);
    public static final Comparator<AnimeChar> BY_SHOW = Comparator.comparing(AnimeChar::getShow)
                                                                              .thenComparing(AnimeChar::getName);
}

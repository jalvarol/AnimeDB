import java.util.Comparator;

public class AnimeCharComparators {
    public static final Comparator<AnimeChar> BY_NAME = Comparator.comparing(AnimeChar::getName, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<AnimeChar> BY_SHOW = Comparator.comparing(AnimeChar::getShow, String.CASE_INSENSITIVE_ORDER)
                                                                              .thenComparing(AnimeChar::getName, String.CASE_INSENSITIVE_ORDER);
}

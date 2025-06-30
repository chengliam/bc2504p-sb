import java.util.List;

// ! reflection

@Getter
public class ShoppingMall {
  private String name;
  private Long area;
  private Cinema cinema;
  @JsonProperty(value = "shopCategory")
  private List<String> shopCategories;
  // ! List is same as Array, in Serialization perspective

  @Getter
  public class Cinema {
    private String name;
    private String openedDate;
    private List<Film> releasedFilm;

    @Getter
    public class Film {
      private String name;
      private String releaseDate;
    }
  }
}

package menu.domain;

import java.util.ArrayList;
import java.util.List;

public final class CategoryRecommender {

  private final List<Category> recommendedCategories = new ArrayList<>();

  private final RandomCategoryPicker randomCategoryPicker;

  public CategoryRecommender(final RandomCategoryPicker randomCategoryPicker) {
    this.randomCategoryPicker = randomCategoryPicker;
  }

  // 다음 추천 카테고리를 만듦
  public Category recommend() {
    final Category pickedCategory = randomCategoryPicker.pick();
    // 같은 카테고리는 최대 2개까지 추천 가능
    // 만약 동일한 카테고리가 이미 2개 존재하면 재추천 결과를 반환
    if (hasMoreThanOneCategories(pickedCategory)) {
      return recommend();
    }
    recommendedCategories.add(pickedCategory);
    return pickedCategory;
  }

  private boolean hasMoreThanOneCategories(final Category category) {
    return recommendedCategories.stream()
        .filter(category::equals)
        .count() > 1;
  }

  public boolean canRecommendMore() {
    return recommendedCategories.size() < 5;
  }

  public List<Category> getRecommendedCategories() {
    return recommendedCategories;
  }
}

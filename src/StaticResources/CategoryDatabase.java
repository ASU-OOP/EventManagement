package StaticResources;

import MiscObjects.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDatabase {
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public CategoryDatabase() {
        Category category = new Category("defaultCategory");
        addCategory(category);
        category = new Category("notdefaultCategory");
        addCategory(category);
        category = new Category("notnotdefaultCategory");
        addCategory(category);
    }
}

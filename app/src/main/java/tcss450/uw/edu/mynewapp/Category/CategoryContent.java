package tcss450.uw.edu.mynewapp.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CategoryContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<CategoryItem> ITEMS = new ArrayList<CategoryItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CategoryItem> ITEM_MAP = new HashMap<String, CategoryItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createCategoryItem(i));
//        }
        addItem(createCategoryItem(1, "Cell Phones"));
        addItem(createCategoryItem(1, "Vehicles"));
        addItem(createCategoryItem(1, "Books"));
        addItem(createCategoryItem(1, "Tables"));
        addItem(createCategoryItem(1, "Computers"));
    }

    private static void addItem(CategoryItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static CategoryItem createCategoryItem(int position, String text) {
        return new CategoryItem(String.valueOf(position), text, "grdytgs");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class CategoryItem {
        public final String id;
        public final String content;
        public final String details;

        public CategoryItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}

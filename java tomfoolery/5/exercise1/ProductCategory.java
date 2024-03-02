package isp.lab4.exercise1;

/**
 * @author Radu Miron
 */
public enum ProductCategory {
    ELECTRONICS("Electronics & Gadgets"),
    HOME_AND_GARDEN("Home and gardening"),
    BEAUTY("Beauty products"),
    TOYS("Toys for kids duh"),

    FASHION("Fashion");
    // todo: add the rest of the constants



    private String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}


package org.openapitools.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MenuItemTest {

    private MenuItem menuItem;
    private UUID sampleUUID;
    private String sampleName = "Sample Name";
    private String sampleImage = "sample-image.jpg";
    private String sampleDescription = "Sample description";
    private BigDecimal sampleCost = new BigDecimal("19.99");
    private FoodProvider sampleFoodProvider;

    @BeforeEach
    public void setUp() {
        sampleUUID = UUID.randomUUID();
        sampleFoodProvider = new FoodProvider(); // 可以根据实际情况初始化 FoodProvider
        menuItem = new MenuItem()
                .menuItemID(sampleUUID)
                .name(sampleName)
                .image(sampleImage)
                .description(sampleDescription)
                .cost(sampleCost);
        menuItem.setFoodProvider(sampleFoodProvider);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(sampleUUID, menuItem.getMenuItemID());
        assertEquals(sampleName, menuItem.getName());
        assertEquals(sampleImage, menuItem.getImage());
        assertEquals(sampleDescription, menuItem.getDescription());
        assertEquals(sampleCost, menuItem.getCost());
        assertEquals(sampleFoodProvider, menuItem.getFoodProvider());
    }

    @Test
    public void testEquals() {
        MenuItem anotherMenuItem = new MenuItem()
                .menuItemID(sampleUUID)
                .name(sampleName)
                .image(sampleImage)
                .description(sampleDescription)
                .cost(sampleCost);
        anotherMenuItem.setFoodProvider(sampleFoodProvider);

        assertThat(menuItem).isEqualTo(anotherMenuItem);
    }

    @Test
    public void testNotEquals() {
        MenuItem differentMenuItem = new MenuItem()
                .menuItemID(UUID.randomUUID())
                .name("Different Name")
                .image("different-image.jpg")
                .description("Different description")
                .cost(new BigDecimal("29.99"));
        differentMenuItem.setFoodProvider(new FoodProvider());

        assertNotEquals(menuItem, differentMenuItem);
    }

    @Test
    public void testHashCode() {
        MenuItem anotherMenuItem = new MenuItem()
                .menuItemID(sampleUUID)
                .name(sampleName)
                .image(sampleImage)
                .description(sampleDescription)
                .cost(sampleCost);
        anotherMenuItem.setFoodProvider(sampleFoodProvider);

        assertEquals(menuItem.hashCode(), anotherMenuItem.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "class MenuItem {\n" +
                "    menuItemID: " + sampleUUID + "\n" +
                "    name: " + sampleName + "\n" +
                "    image: " + sampleImage + "\n" +
                "    description: " + sampleDescription + "\n" +
                "    cost: " + sampleCost + "\n" +
                "}";

        assertEquals(expectedString, menuItem.toString());
    }

    @Test
    public void testMenuItemChainedMethods() {
        MenuItem chainedMenuItem = new MenuItem()
                .menuItemID(sampleUUID)
                .name(sampleName)
                .image(sampleImage)
                .description(sampleDescription)
                .cost(sampleCost);

        assertEquals(sampleUUID, chainedMenuItem.getMenuItemID());
        assertEquals(sampleName, chainedMenuItem.getName());
        assertEquals(sampleImage, chainedMenuItem.getImage());
        assertEquals(sampleDescription, chainedMenuItem.getDescription());
        assertEquals(sampleCost, chainedMenuItem.getCost());
    }
}

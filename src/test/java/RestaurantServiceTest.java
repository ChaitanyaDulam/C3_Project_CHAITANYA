import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        addMockRestaurantWithMenu();
        Restaurant outputRestaurant = service.findRestaurantByName( "Amelie's cafe" );
        assertEquals( restaurant, outputRestaurant );
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        addMockRestaurantWithMenu();
        assertThrows( restaurantNotFoundException.class, () -> service.findRestaurantByName( "Pantry d'or" ) );
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        addMockRestaurantWithMenu();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant( "Amelie's cafe" );
        assertEquals( initialNumberOfRestaurants - 1, service.getRestaurants().size() );
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        addMockRestaurantWithMenu();

        assertThrows( restaurantNotFoundException.class, () -> service.removeRestaurant( "Pantry d'or" ) );
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        addMockRestaurantWithMenu();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant( "Pumpkin Tales", "Chennai", LocalTime.parse( "12:00:00" ), LocalTime.parse( "23:00:00" ) );
        assertEquals( initialNumberOfRestaurants + 1, service.getRestaurants().size() );
    }

    private void addMockRestaurantWithMenu() {
        LocalTime openingTime = LocalTime.parse( "10:30:00" );
        LocalTime closingTime = LocalTime.parse( "22:00:00" );
        restaurant = service.addRestaurant( "Amelie's cafe", "Chennai", openingTime, closingTime );
        restaurant.addToMenu( "Sweet corn soup", 119 );
        restaurant.addToMenu( "Vegetable lasagne", 269 );
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}
package techproed.stepdefs.db_stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import techproed.stepdefs.ui_stepdefs.UIMedunnaStepdefs;
import techproed.utilities.ConfigReader;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class DBRoomStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    public static int roomId;

    @Given("Admin Connect to the Database")
    public void adminConnectToTheDatabase() throws SQLException {
        connection = DriverManager.getConnection(
                ConfigReader.getProperty("dbUrl"),
                ConfigReader.getProperty("dbUser"),
                ConfigReader.getProperty("dbPassword")
        );
    }

    @When("send query for created room")
    public void sendQueryForCreatedRoom() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from room where room_number ="+ UIMedunnaStepdefs.roomNumber);
    }

    @Then("validates created room from resultset")
    public void validatesCreatedRoomFromResultset() throws SQLException {
        resultSet.next();

        int expectedRoomNumber = UIMedunnaStepdefs.roomNumber;
        String expectedDescription = UIMedunnaStepdefs.expectedDescription;
        String expectedPrice = UIMedunnaStepdefs.expectedPrice;

        int actualRoomNumber = resultSet.getInt("room_number");
        String actualDescription = resultSet.getString("description");
        String actualPrice = resultSet.getString("price");

        assertEquals(expectedRoomNumber,actualRoomNumber);
        assertEquals(expectedDescription,actualDescription);
        assertEquals(expectedPrice,actualPrice);


        roomId = resultSet.getInt("id");

    }


}

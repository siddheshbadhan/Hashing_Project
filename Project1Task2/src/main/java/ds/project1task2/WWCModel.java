/**
 * Author: sbadhan Siddhesh Badhan
 * Last Modified: 02/10/2023
 *
 * This Java file acts as the Model for this program.
 * It defines six functions to scrape various information of the countries selected by the user.
 * The six functions are:
 * getNickName() - scrapes the nickname of the country
 * getTopScorer() - scrapes the top scorers of the country
 * getCapital() - scrapes the capital city of a country
 * getFlag() - scrapes the flag image of the country
 * getFlagEmoji() - scrapes the emoji of flag of a country
 */

package ds.project1task2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WWCModel {

    private String fetch(String urlString) {
        // Initialize a StringBuilder to store the response from the server
        StringBuilder response = new StringBuilder();

        try {
            // Create a URL object from the URL string
            URL url = new URL(urlString);

            //Create an HttpURLConnection for setting header and for getting the path of the resource
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;
            // Read each line of "in" until done, adding each to "response"
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            // Print an error message if an exception occurs while fetching the data
            System.err.println("An exception occurred while fetching the data: " + e.getMessage());
        }

        // Return the response as a string
        return response.toString();
    }


    /**
     * This function is used to get the nickname of a country in soccer.
     * The function uses Jsoup to connect to a URL that contains the list of nicknames
     * of countries in soccer and scrapes the data to get the desired nickname.
     *
     * @param userCountry The country for which the nickname is to be obtained
     * @return The nickname of the country in soccer
     */
    public String getNickName(String userCountry) {

        // Stores the result of the Jsoup scrapped URL for country nicknames
        Document dNickNames;
        try {
            // Using Jsoup, connect to the URL, scrape it and store its data
            dNickNames = Jsoup.connect("https://www.topendsports.com/sport/soccer/team-nicknames-women.htm").get();
        }
        // Handles IO exceptions and throws a Runtime exception along with its details
        catch (IOException e) {
            throw new RuntimeException("An error occurred while retrieving the country nicknames: " + e.getMessage(), e);
        }
        // Using Jsoup selectors, scrape the Document and store the potential elements that contain
        // nickname of the US State
        Elements nicknames = dNickNames.select("tbody tr td");
        String countryNickname = nicknames.stream()
                .filter(element -> element.text().equals(userCountry))
                .map(element -> element.nextElementSibling().text())
                .findFirst()
                .orElse("Not found");
        return countryNickname;

    }

    /**
     * Method to retrieve the top scorer and their number of goals for the specified country from ESPN's FIFA World Cup website.
     * @param userCountry The name of the country for which to retrieve the top scorer information.
     * @return A string representation of the top scorer and their number of goals in the format: "scorer_name, x goals".
     * Returns an empty string if the country is not found in the data.
     */
    public String getTopScorer(String userCountry) {

        // A Document variable to store the result of scraping the URL for top scorers
        Document dTopScorers;
        // String variables to store the top scorer and the number of goals scored
        String topScorer = "";
        String goals = "";
        // Connect to the URL using Jsoup and store the scraped data in the dTopScorers document
        try {
            dTopScorers = Jsoup.connect("https://www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring").get();
        }
        // Handle exceptions and throw a runtime exception with the details of the error
        catch (IOException e) {
            throw new RuntimeException("An error occurred while retrieving the country Top Scorer: " + e.getMessage(), e);
        }
        // Use Jsoup selectors to scrape the dTopScorers Document and store the elements that contain the country and its top scorer
        Elements eTopScorers = dTopScorers.select("tbody tr td");

        // Loop through the elements to find the desired country and its top scorer
        for (int i = 0; i < eTopScorers.size(); i++) {
            // If the desired country is found in the current element
            if (eTopScorers.get(i).text().equals(userCountry)) {
                // Store the top scorer and number of goals in the variables
                topScorer = eTopScorers.get(i - 1).text();
                goals = eTopScorers.get(i + 1).text();
                break;
            }
        }

        // Return the top scorer and number of goals, or N/A if the information is not found
        return topScorer.isEmpty() || goals.isEmpty() ? "N/A" : topScorer + ", " + goals + " goals";
    }


    /**
     * Method to return the capital city of a country based on the user-entered country name
     * @param userCountry - the name of the country entered by the user
     * @return capitalCity - the capital city of the country
     */
    public String getCapital(String userCountry) {
        //initializing variable to store the result
        String capitalCity = "";
        //variable to store JSON response
        String json;
        Gson gson = new Gson();
        if(userCountry.equals("England")) {
            //fetching JSON response for "Great Britain"
            json = fetch("https://restcountries.com/v3.1/name/Great%20Britain");
        } else {
            //fetching JSON response by replacing spaces with %20 in country name
            json = fetch("https://restcountries.com/v3.1/name/"+userCountry.replaceAll(" ","%20"));
        }
        //checking if JSON response is not empty
        if(json.length()>0) {
            //parsing JSON response as JsonArray
            JsonArray temp = gson.fromJson(json, JsonArray.class);
            //iterating through the array
            for (int i = 0; i < temp.size(); i++) {
                //getting the i-th element as JsonObject
                JsonObject cObject = temp.get(i).getAsJsonObject();
                //getting the "name" field as JsonObject
                JsonObject cName = cObject.get("name").getAsJsonObject();
                //checking if the common name matches the input country name (ignoring case)
                if (cName.get("common").getAsString().equalsIgnoreCase(userCountry)||cName.get("common").getAsString().equalsIgnoreCase("United Kingdom")) {
                    //getting the "capital" field as JsonArray
                    JsonArray capitalArray = cObject.getAsJsonArray("capital");
                    //initializing List to store capital(s)
                    List<String> capitalCities;
                    capitalCities = new ArrayList<>();
                    //iterating through the "capital" array
                    for (JsonElement capital : capitalArray) {
                        //adding each capital to the list
                        capitalCities.add(capital.getAsString());
                    }
                    //concatenating capital(s) with ", " separator
                    capitalCity = String.join(", ", capitalCities);
                    //breaking the loop if capital(s) found
                    break;
                }
            }
        }
        //returning the result
        return capitalCity;
    }


    /**
     * Method to retrieve the URL of a country's flag image.
     * @param userCountry The name of the country to retrieve the flag image URL for.
     * @return The URL of the country's flag image.
     *
     */
    public String getFlag(String userCountry)  {
        // Document object to store HTML document
        Document dFlags;
        // String to store the flag image URL
        String flagImage = "";
        // String to store the URL of the flag image
        String url;

        try {
            // Check if the country is England
            if(userCountry.equals("England")) {
                url = "https://www.cia.gov/the-world-factbook/countries/united-kingdom/flag";
            } else {
                url = "https://www.cia.gov/the-world-factbook/countries/" + userCountry.toLowerCase().replaceAll(" ", "-") + "/flag";
            }
            // Connect to the URL and get the HTML document
            dFlags = Jsoup.connect(url).get();
            // Select the image elements in the document
            Elements eFlags = dFlags.select("img");
            // Get the source URL of the first image
            flagImage = "https://www.cia.gov"+eFlags.attr("srcset").split(" ")[0];
        } catch (IOException e) {
            // Throw a runtime exception if there is an error while retrieving the country flag image
            throw new RuntimeException("An error occurred while retrieving the country Flag Image: " + e.getMessage(), e);
        }
        // Return the URL of the flag image
        return flagImage;
    }


    /**
     * This method retrieves the flag emoji of the country specified by the user.
     * @param userCountry The name of the country whose flag emoji is to be retrieved.
     * @return The flag emoji of the specified country.
     */
    public String getFlagEmoji(String userCountry) {
        // URL for the JSON file containing country flag emojis
        String url = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/index.json";
        // Fetch the JSON file
        String json = fetch(url);
        // Create a Gson object to parse the JSON file
        Gson gson = new Gson();
        // Parse the JSON file into a JsonArray
        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        // String to store the flag emoji
        String Emoji = "";

        // Loop through each country in the JsonArray
        for (JsonElement countryElement : jsonArray) {
            // Get the current country as a JsonObject
            JsonObject cObject = countryElement.getAsJsonObject();
            // Get the name of the current country
            String cName = cObject.get("name").getAsString();
            // Check if the name of the current country is the same as the userCountry parameter (ignoring case)
            if (cName.equalsIgnoreCase(userCountry)) {
                // If so, get the flag emoji of the current country
                Emoji = cObject.get("image").getAsString();
                // Break out of the loop
                break;
            }
        }
        // Return the flag emoji
        return Emoji;
    }

}
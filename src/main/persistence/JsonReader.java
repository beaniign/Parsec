package persistence;

import model.Trip;
import model.TripLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TripLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTripLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private TripLog parseTripLog(JSONObject jsonObject) {
        TripLog tl = new TripLog();
        addTrips(tl, jsonObject);
        return tl;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTrips(TripLog tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("trips");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTrip(tl, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addTrip(TripLog tl, JSONObject jsonObject) {
        int duration = jsonObject.getInt("duration");
        String location = jsonObject.getString("location");
        String note = jsonObject.getString("note");
        Trip myTrip = new Trip(duration, location, note);
        tl.addTrip(myTrip);
    }
}

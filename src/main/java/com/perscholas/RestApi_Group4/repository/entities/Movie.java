package com.perscholas.RestApi_Group4.repository.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("Rated")
	private String rated;
	@JsonProperty("Released")
	private String released;
	@JsonProperty("Runtime")
	private String runtime;
	@JsonProperty("Genre")
	private String genre;
	@JsonProperty("Director")
	private String director;
	@JsonProperty("Writer")
	private String writer;
	@JsonProperty("Actors")
	private String actors;
	@JsonProperty("Plot")
	private String plot;
	@JsonProperty("Language")
	private String language;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Awards")
	private String awards;
	@JsonProperty("Poster")
	private String poster;
	@JsonProperty("Ratings")
	@JsonDeserialize(using = MovieRatingDeserializer.class)
	private Map<String, String> ratings;

	@Override
	public String toString() {
		return "Movie{" +
				"title='" + title + '\'' +
				", year='" + year + '\'' +
				", rated='" + rated + '\'' +
				", released='" + released + '\'' +
				", runtime='" + runtime + '\'' +
				", genre='" + genre + '\'' +
				", director='" + director + '\'' +
				", writer='" + writer + '\'' +
				", actors=" + actors +
				", plot='" + plot + '\'' +
				", language='" + language + '\'' +
				", country='" + country + '\'' +
				", awards='" + awards + '\'' +
				", poster='" + poster + '\'' +
				", ratings=" + ratings.toString() +
				'}';
	}

	private static class MovieRatingDeserializer extends JsonDeserializer<Map<String, String>> {
		@Override
		public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
			Map<String, String> ratings = new HashMap<>();
			JsonNode ratingArrayNode = jsonParser.getCodec().readTree(jsonParser);

			if(!ratingArrayNode.isArray()){
				throw new IOException("Expected field ratings to be an array");
			}

			for (JsonNode node : ratingArrayNode) {
				ratings.put(node.get("Source").asText(), node.get("Value").asText());
			}

			return ratings;
		}
	}
}

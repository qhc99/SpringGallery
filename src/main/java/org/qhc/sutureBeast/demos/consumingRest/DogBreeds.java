package org.qhc.sutureBeast.demos.consumingRest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DogBreeds(Object message, String status) {

}

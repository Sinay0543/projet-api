package com.course;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MovieRating {
    G,
    PG,
    @JsonProperty("PG-13")
    PG_13,
    R
}

package com.zeta.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Campus enum contains all the possible campuses to choose from
 */
public enum Campus {
    @JsonProperty("BURNABY")
    BURNABY,
    @JsonProperty("SURREY")
    SURREY,
    @JsonProperty("VANCOUVER")
    VANCOUVER
}
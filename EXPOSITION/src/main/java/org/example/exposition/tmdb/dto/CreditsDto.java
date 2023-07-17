package org.example.exposition.tmdb.dto;

import java.util.ArrayList;

public class CreditsDto {
    public ArrayList<CastDto> cast;
    public ArrayList<CrewDto> crew;

    public CreditsDto() {
    }

    public CreditsDto(ArrayList<CastDto> cast, ArrayList<CrewDto> crew) {
        this.cast = cast;
        this.crew = crew;
    }

    public ArrayList<CastDto> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CastDto> cast) {
        this.cast = cast;
    }

    public ArrayList<CrewDto> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewDto> crew) {
        this.crew = crew;
    }
}

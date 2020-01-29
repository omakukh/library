package com.lits.makukh.library.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchBookResponse {
    private List<Hit> hits;

    @Data
    public static class Hit {
        private String author;
        private String description;
        private String name;
        private String publisher;
        private HighlightResult _highlightResult;
    }

    @Data
    public static class HighlightResult {
        private MatchedWord author;
        private MatchedWord description;
        private MatchedWord isbn;
        private MatchedWord name;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MatchedWord {
        private String value;
        private String[] matchedWords;
    }


}

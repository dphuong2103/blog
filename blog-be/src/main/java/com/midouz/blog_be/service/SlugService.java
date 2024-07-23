package com.midouz.blog_be.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class SlugService {
    private final Map<String, String> SPECIAL_CHAR_MAP = new HashMap<>();

    {
        SPECIAL_CHAR_MAP.put("#", "sharp");
        SPECIAL_CHAR_MAP.put("$", "dollar");
        SPECIAL_CHAR_MAP.put("%", "percent");
        SPECIAL_CHAR_MAP.put("&", "and");
        SPECIAL_CHAR_MAP.put("@", "at");
        SPECIAL_CHAR_MAP.put("!", "exclamation");
        SPECIAL_CHAR_MAP.put("*", "asterisk");
        SPECIAL_CHAR_MAP.put("+", "plus");
        SPECIAL_CHAR_MAP.put("-", "minus");
        SPECIAL_CHAR_MAP.put("/", "slash");
        SPECIAL_CHAR_MAP.put("=", "equal");
        SPECIAL_CHAR_MAP.put("?", "question");
        SPECIAL_CHAR_MAP.put(":", "colon");
        SPECIAL_CHAR_MAP.put(";", "semicolon");
        SPECIAL_CHAR_MAP.put("~", "tilde");
        SPECIAL_CHAR_MAP.put("_", "underscore");
        SPECIAL_CHAR_MAP.put("<", "less_than");
        SPECIAL_CHAR_MAP.put(">", "greater_than");
        SPECIAL_CHAR_MAP.put("|", "pipe");
        SPECIAL_CHAR_MAP.put("^", "caret");
        SPECIAL_CHAR_MAP.put("`", "backtick");
        SPECIAL_CHAR_MAP.put("{", "left_brace");
        SPECIAL_CHAR_MAP.put("}", "right_brace");
        SPECIAL_CHAR_MAP.put("[", "left_bracket");
        SPECIAL_CHAR_MAP.put("]", "right_bracket");
        SPECIAL_CHAR_MAP.put("(", "left_parenthesis");
        SPECIAL_CHAR_MAP.put(")", "right_parenthesis");
        SPECIAL_CHAR_MAP.put("\"", "double_quote");
        SPECIAL_CHAR_MAP.put("'", "single_quote");
        SPECIAL_CHAR_MAP.put("\\", "backslash");
        SPECIAL_CHAR_MAP.put(",", "comma");
        SPECIAL_CHAR_MAP.put(".", "dot");
    }

    public String replaceSpecialChar(String input) {
        for (Map.Entry<String, String> entry : SPECIAL_CHAR_MAP.entrySet()) {
            String key = Pattern.quote(entry.getKey()); // Escape special characters
            input = input.replaceAll(key, entry.getValue());
        }
        return input;
    }
}

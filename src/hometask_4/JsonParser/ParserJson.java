package hometask_4.JsonParser;

import java.util.*;

public class ParserJson {
    private static final String strValueRegex = "^\"[^\"]+\"\s*:\s*\"[^\"]*\"\s*(.|\n)*";
    private static final String numberValueRegex = "^\".+\"\s*:\s*-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?\s*(.|\n)*";

    private static final Statistics jsonStatistics = new Statistics();

    private static class Statistics {
        private int objectsNum;
        private int arraysNum;

        public int getObjectsNum() {
            return objectsNum;
        }

        public int getArraysNum() {
            return arraysNum;
        }
    }

    private static class Pair<T, Q> {
        T first;
        Q second;

        public Pair(T first, Q second) {
            this.first = first;
            this.second = second;
        }
    }

    private boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private Pair<Number, Integer> getNumberValue(String part, int start) throws InvalidJsonException {
        // skip spaces
        while (part.charAt(start) == ' ' || part.charAt(start) == ':') start++;

        var numberSb = new StringBuilder();
        boolean isDouble = false;

        while (isNumber(part.charAt(start)) || part.charAt(start) == '.' || part.charAt(start) == '-') {
            if (part.charAt(start) == '.')
                isDouble = true;
            numberSb.append(part.charAt(start));
            start++;
        }

        try {
            return isDouble ? new Pair<>(Double.parseDouble(numberSb.toString()), start) :
                    new Pair<>(Integer.parseInt(numberSb.toString()), start);
        } catch (Exception e) {
            throw new InvalidJsonException("JSON object isn't correct");
        }
    }

    private Pair<String, Integer> getStringValue(String json, int index) {
        int leftQuote = json.indexOf('\"', index);
        int rightQuote = json.indexOf('\"', leftQuote + 1);
        return new Pair<>(json.substring(leftQuote + 1, rightQuote), rightQuote);
    }

    private int putKeyValue(String json, int from, Map<String, Object> map) throws InvalidJsonException {
        int i1 = json.indexOf('\"', from);
        int i2 = json.indexOf('\"', i1 + 1);

        int i3 = json.indexOf(',', i2 + 1);
        int i4 = json.indexOf('\"', i2 + 1);

        String key = json.substring(i1 + 1, i2);
        Object value;

        // number
        if ((i3 < i4 && i3 != -1) || i4 == -1) {
            // skip spaces and ':'
            from = i2 + 1;
            while (!isNumber(json.charAt(from))) from++;

            var numberValue = getNumberValue(json, from);
            value = numberValue.first;
            from = numberValue.second;
        }
        // string
        else {
            var stringValue = getStringValue(json, i4);
            value = stringValue.first;
            from = stringValue.second;
        }

        map.put(key, value);
        return from;
    }

    private int keyValue(String json, int from, Map<String, Object> map, List<Object> array)
            throws InvalidJsonException
    {
        // quotes between which is key value
        int leftQuote = -1;
        int rightQuote = -1;

        // двоеточие
        boolean colonOccurred = false;
        char ch = 0;
        while (from < json.length() && (ch = json.charAt(from)) != '}') {
            switch (ch) {
                case '\"' -> {
                    String subJson = json.substring(from);
                    if (subJson.matches(strValueRegex) || subJson.matches(numberValueRegex))
                        from = putKeyValue(json, from, map);
                    else if (leftQuote != -1 && rightQuote != -1)
                        throw new InvalidJsonException("JSON object isn't correct");
                    else if (leftQuote == -1)
                        leftQuote = from;
                    else
                        rightQuote = from;
                }
                case '{', '[' -> {
                    if (leftQuote == -1 || rightQuote == -1 || !colonOccurred)
                        throw new InvalidJsonException("JSON object isn't correct");

                    String key = json.substring(leftQuote + 1, rightQuote);
                    leftQuote = rightQuote = -1;
                    colonOccurred = false;

                    if (ch == '{')
                        from = object(json, from, key, map, array, false);
                    else
                        from = array(json, from, key, map);
                }
                case ':' -> {
                    if (leftQuote != -1 && rightQuote != -1)
                        colonOccurred = true;
                }
            }
            from++;
        }

        if (ch != '}')
            throw new InvalidJsonException("JSON object isn't correct");

        return from;
    }

    private boolean isNumberCollection(String json, int from, int endOfArray) {
        int quoteIndex = json.indexOf('\"', from);
        return quoteIndex == -1 || quoteIndex > endOfArray;
    }

    private String[] splitAsNumberData(String plainData) {
        return plainData.split("\"\s*,\s*\"");
    }

    private String[] splitAsStringData(String plainData) {
        String[] split = plainData.split("\"[\s\n]*,[\s\n]*\"");
        int length = split.length;
        switch (length) {
            case 0 -> {
                return split;
            }
            case 1 -> {
                split[0] = split[0].substring(1, split[0].length() - 1);
                return split;
            }
            default -> {
                split[0] = split[0].substring(1);
                String lastWords = split[split.length - 1];
                split[split.length - 1] = lastWords.substring(0, lastWords.indexOf("\""));
                return split;
            }
        }
    }

    private int plainArrayData(String json, int from, List<Object> array) throws InvalidJsonException {
        int endOfArray = json.indexOf(']', from);
        if (endOfArray == -1)
            throw new InvalidJsonException("JSON object isn't correct");

        if (isNumberCollection(json, from, endOfArray)) {
            String subJson = json.substring(from, endOfArray);
            try {
                Arrays.stream(splitAsNumberData(subJson))
                      .forEach(s -> array.add(Double.parseDouble(s)));
            } catch (Exception e) {
                throw new InvalidJsonException("JSON object isn't correct");
            }
        }
        else {
            String subJson = json.substring(from, endOfArray + 1); // in order to include ']'

            if (subJson.matches("(\"[^\"]+\"[\s|\n]*[,|\\]][\s|\n]*)*"))
                array.addAll(Arrays.asList(splitAsStringData(subJson)));
            else
                throw new InvalidJsonException("JSON object isn't correct");
        }
        return endOfArray;
    }

    private int array(String json, int from, String key, Map<String, Object> map)
            throws InvalidJsonException
    {
        // statistics
        jsonStatistics.arraysNum++;

        var array = new ArrayList<>();
        map.put(key, array);

        char ch;
        while (from < json.length() && (ch = json.charAt(from)) != ']') {
            if (ch == '{')
                from = object(json, from, null, null, array, false);
            else if (ch == '\"' || isNumber(ch))
                from = plainArrayData(json, from, array);
            else
                from++;
        }

        // json object can't end with character ']'
        if (from == json.length())
            throw new InvalidJsonException("JSON object isn't correct");

        return from;
    }

    private int object(String json, int from, String key, Map<String, Object> map, List<Object> array, boolean init)
            throws InvalidJsonException
    {
        // statistics
        jsonStatistics.objectsNum++;

        if (!init && (key != null || array != null)) {
            var innerMap = new HashMap<String, Object>();
            if (key != null)
                map.put(key, innerMap);
            else
                array.add(innerMap);
            map = innerMap;
        }

        char ch = 0;
        while (from < json.length() && (ch = json.charAt(from)) != '}') {
            if (ch == '\"')
                from = keyValue(json, from, map, array);
            else if (ch == ' ' || ch == '\n' || ch == '{')
                from++;
            else
                throw new InvalidJsonException("JSON object isn't correct");
        }

        if (ch != '}')
            throw new InvalidJsonException("JSON object isn't correct");

        return from;
    }

    public static Map<String, Object> parseJson(String json) throws InvalidJsonException {
        var parser = new ParserJson();
        var map = new HashMap<String, Object>();
        parser.object(json, 0, null, map, null, true);
        return map;
    }

    public static boolean jsonIsValid(String json) {
        try {
            parseJson(json);
            return true;
        } catch (InvalidJsonException e) {
            return false;
        }
    }

    public static Statistics getJsonStatistics(String json) throws InvalidJsonException {
        parseJson(json);
        return jsonStatistics;
    }

    // TEST
    public static void main(String[] args) throws InvalidJsonException {
        String json =
                """
                {
                  "squadName": "Super hero squad",
                  "homeTown": "Metro City",
                  "formed": 2016,
                  "secretBase": "Super tower",
                  "members": [
                    {
                      "name": "Molecule Man",
                      "age": 29,
                      "secretIdentity": "Dan Jukes",
                      "powers": [
                        "Radiation resistance",
                        "Turning tiny",
                        "Radiation blast"
                      ]
                    },
                    {
                      "name": "Madame Uppercut",
                      "age": 39,
                      "secretIdentity": "Jane Wilson",
                      "powers": [
                        "Million tonne punch",
                        "Damage resistance",
                        "Superhuman reflexes"
                      ]
                    },
                    {
                      "name": "Eternal Flame",
                      "age": 1000000,
                      "secretIdentity": "Unknown",
                      "powers": [
                        "Immortality",
                        "Heat Immunity",
                        "Inferno",
                        "Teleportation",
                        "Interdimensional travel"
                      ]
                    }
                  ]
                }
                """;
        Map<String, Object> parsedJson = parseJson(json);
    }
}

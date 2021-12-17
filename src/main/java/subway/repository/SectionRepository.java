package subway.repository;

import subway.domain.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectionRepository {
    private static final List<Section> sections = new ArrayList<>();

    public static List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    public static void addSection(Section section) {
        sections.add(section);
    }

    public static void deleteAll() {
        sections.clear();
    }

    public static Section findByNames(String edgeSrc, String edgeDest) {
        return sections.stream()
                .filter(section -> section.getSource().getName().equals(edgeSrc))
                .filter(section -> section.getDestination().getName().equals(edgeDest))
                .findAny()
                .orElse(null);
    }
}


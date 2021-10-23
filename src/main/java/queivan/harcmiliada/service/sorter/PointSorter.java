package queivan.harcmiliada.service.sorter;

import queivan.harcmiliada.domain.Answer;

import java.util.Comparator;

public class PointSorter implements Comparator<Answer> {
    @Override
    public int compare(Answer o1, Answer o2) {
        return o2.getPoints().compareTo(o1.getPoints());
    }
}

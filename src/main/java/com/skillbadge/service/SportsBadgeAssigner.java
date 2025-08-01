package  com.skillbadge.service;

import com.skillbadge.exception.InvalidRankException;
import com.skillbadge.model.Student;

public class SportsBadgeAssigner extends BaseBadgeAssigner {

    @Override
    public void assignBadge(Student s) {
        try {
            int stars = calculateStars(s.getSportRank());
            addBadgeToStudent(s, "Sports", stars);
        } catch (InvalidRankException e) {
            System.out.println(" Error assigning Sports badge: " + e.getMessage());
        }
    }
}

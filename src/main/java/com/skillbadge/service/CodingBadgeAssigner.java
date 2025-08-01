package  com.skillbadge.service;

import com.skillbadge.exception.InvalidRankException;
import com.skillbadge.model.Student;

public class CodingBadgeAssigner extends BaseBadgeAssigner {

    @Override
    public void assignBadge(Student s) {
        try {
            int stars = calculateStars(s.getCodingRank());
            addBadgeToStudent(s, "Coding", stars);
        } catch (InvalidRankException e) {
            System.out.println(" Error assigning Coding badge: " + e.getMessage());
        }
    }
}

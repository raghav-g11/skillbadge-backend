package  com.skillbadge.service;

import com.skillbadge.model.SBadge;
import com.skillbadge.model.Student;
import com.skillbadge.exception.InvalidRankException;

public abstract class BaseBadgeAssigner implements BadgeAssigner {

    protected int calculateStars(int rank) throws InvalidRankException {
        if (rank <= 0 || rank > 100) {
            throw new InvalidRankException("Invalid rank: " + rank);
        }
        if (rank <= 3) return 5;
        else if (rank <= 10) return 4;
        else if (rank <= 15) return 3;
        else if (rank <= 25) return 2;
        else return 1;
    }

    protected void addBadgeToStudent(Student s, String type, int stars) throws InvalidRankException {
        SBadge badge = new SBadge(type, stars);
        s.addBadge(badge);
    }
}

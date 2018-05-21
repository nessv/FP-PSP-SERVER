package py.org.fundacionparaguaya.pspserver.surveys.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity_;

/**
 * @author mcespedes
 *
 */
public class SnapshotEconomicSpecification {

    private static final String SHORT_DATE_FORMAT = "dd/MM/yyyy";

    private static final long MONTH_AGO = 12;

    private SnapshotEconomicSpecification() {
        // not called
    }

    public static Specification<SnapshotEconomicEntity> byApplication(Long applicationId) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (applicationId != null) {
                    predicates.add(cb.equal(root.join("family").join("application").get("id"), applicationId));

                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byOrganizations(List<Long> organizations) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (organizations != null) {
                    predicates.add(root.join("family").join("organization").get("id").in(organizations));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> createdAtLess2Months() {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {

                LocalDateTime limit = LocalDateTime.now();
                limit = limit.minusMonths(MONTH_AGO).withDayOfMonth(1);

                return cb.and(cb.greaterThan(root.<LocalDateTime>get(SnapshotEconomicEntity_.getCreatedAt()), limit));

            }
        };
    }

    public static Specification<SnapshotEconomicEntity> createdAtBetween2Dates(String dateFrom, String dateTo) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (dateFrom != null && dateTo != null) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT);

                    predicates.add(cb.greaterThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                            LocalDate.parse(dateFrom, formatter).atStartOfDay()));
                    predicates.add(cb.lessThan(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                            LocalDate.parse(dateTo, formatter).plusDays(1).atStartOfDay()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> forFamily(Long familyId) {
        return (root, query, cb) -> {
            if (familyId == null) {
                return null;
            }
            return cb.equal(root.join("family").get("familyId"), familyId);
        };
    }

    public static Specification<SnapshotEconomicEntity> forSurvey(Long surveyId) {
        return (root, query, cb) -> {
            if (surveyId == null) {
                return null;
            }
            return cb.equal(root.join("surveyDefinition").get("id"), surveyId);
        };
    }
}

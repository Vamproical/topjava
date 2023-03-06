package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id,
               @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id = :userId")
    List<Meal> findAll(@Param("userId") int userId, Sort sort);

    @Query("""
                SELECT m FROM Meal m 
                WHERE m.user.id=:userId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime ORDER BY m.dateTime DESC
            """)
    List<Meal> findAllBetweenInclusive(@Param("userId") int userId,
                                       @Param("startDateTime") LocalDateTime startDateTime,
                                       @Param("endDateTime") LocalDateTime endDateTime);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = :id and m.user.id = :userId")
    Meal getWithUser(@Param("id") int id,
                     @Param("userId") int userId);
}

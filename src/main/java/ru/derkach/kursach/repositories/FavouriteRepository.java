package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
}

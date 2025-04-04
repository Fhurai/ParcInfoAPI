package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository pour l'entité {@link Ordinateur}.
 * <p>
 * Cette interface étend {@link CrudRepository} afin de fournir les opérations CRUD de base
 * (création, lecture, mise à jour et suppression) pour l'entité {@link Ordinateur}.
 * </p>
 * <p>
 * Vous pouvez définir des méthodes personnalisées en plus des méthodes héritées de {@code CrudRepository}.
 * Actuellement, aucune méthode supplémentaire n'est définie.
 * </p>
 */
public interface OrdinateurRepository extends CrudRepository<Ordinateur, Long> {
    // Aucune méthode personnalisée n'est nécessaire pour le moment.
}

package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrdinateurRepository extends CrudRepository<Ordinateur, Integer> {
    Optional<Ordinateur> findByOrdinateur(String ordinateur);
}

//But de la méthode :
//Cette méthode permet de rechercher un objet Appareil en fonction de son champ libelle.
//
//Fonctionnement :
//
//Le nom de la méthode getAppareilByLibelle suit la convention de nommage de Spring Data. Cela permet à Spring d'interpréter automatiquement le nom et de générer la requête correspondante sans que vous ayez à l'écrire explicitement.
//
//Elle prend en paramètre une String libelle qui représente le libellé recherché.
//
//Le retour est de type Optional<Appareil>, ce qui signifie que le résultat peut contenir un objet Appareil s'il est trouvé, ou être vide (absence de résultat) si aucun appareil ne correspond au critère.



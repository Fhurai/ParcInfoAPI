package fr.afpa.pompey.cda17.parcInfoAPI.models;

/**
 * Enum representing different types of peripheral devices.
 * Each type is associated with a unique integer value.
 */
public enum TypePeripherique {
    // List of peripheral types with their associated integer values.
    CAMERA_WEB(0),
    CARTE_CAPTURE(1),
    CASQUE(2),
    CASQUE_MICROPHONE(3),
    CASQUE_VR(4),
    CLAVIER(5),
    DISQUE_EXTERNE(6),
    ECRAN(7),
    ENCEINTES(8),
    HUB_USB(9),
    IMPRIMANTE(10),
    IMPRIMANTE_SCANNER(11),
    MICROPHONE(12),
    PHOTOCOPIEUR(13),
    SCANNER(14),
    SOURIS(15),
    TABLETTE_GRAPHIQUE(16);

    // The integer value associated with the peripheral type.
    private final int value;

    /**
     * Constructor for the enum.
     * 
     * @param value The integer value associated with the peripheral type.
     */
    TypePeripherique(int value) {
        this.value = value;
    }

    /**
     * Gets the integer value associated with the peripheral type.
     * 
     * @return The integer value of the peripheral type.
     */
    public int getValue() {
        return value;
    }
}
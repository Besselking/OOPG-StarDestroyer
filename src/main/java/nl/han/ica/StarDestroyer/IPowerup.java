package nl.han.ica.StarDestroyer;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public interface IPowerup {
    /**
     * all powerups use this function to call the right funcion in the player object
     *
     * @param player reference to the player object
     */
    void apply(Player player);
}

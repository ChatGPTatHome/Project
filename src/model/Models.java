package model;

import java.util.Hashtable;

/**
 * Holds a collection of Classes from the model package.
 * Used for synchronization of data/models between Classes.
 *
 * @author Hai Duong
 */
public class Models {

    /**
     * Hashtable used to store all model objects paired with their
     * Class object keys.
     */
    private Hashtable<Class<?>, Object> models = new Hashtable<Class<?>, Object>();

    /**
     * Gets the desired model class. If model does not yet exist,
     * creates a new instance.
     *
     * @param modelClass The key used to fetch a model class.
     *                   Is a Class object.
     * @return the model Object based on the key
     * passed in.
     *
     * @author Hai Duong
     */
    public <T> T getModel(Class<T> modelClass) {
        if (!this.models.containsKey(modelClass)) {
            try {
                this.models.put(modelClass, modelClass.getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error thrown on model instantiate");
            };
        }
        
        return modelClass.cast(this.models.get(modelClass));
    }
}

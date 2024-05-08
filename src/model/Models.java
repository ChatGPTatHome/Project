package model;

import java.util.Hashtable;

public class Models {
    private Hashtable<Class<?>, Object> models = new Hashtable<Class<?>, Object>();

    /**
     * Gets the desired model class.
     */
    public <T> T getModel(Class<T> modelClass) {
        if (!this.models.containsKey(modelClass)) {
            try {
                this.models.put(modelClass, modelClass.newInstance());
            } catch (Exception e) {
                System.out.println("Error thrown on model instantiate");
            };
        }
        
        return modelClass.cast(this.models.get(modelClass));
    }
}

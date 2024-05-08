package model;

import java.util.Hashtable;

public class CardModel {
    Hashtable<Class, Object> models = new Hashtable<Class, Object>();

    /**
     * Gets the desired model class.
     */
    public Object getModel(Class modelClass) {
        System.out.println("C");
        if (!this.models.contains(modelClass)) {
            try {
                this.models.put(modelClass, modelClass.newInstance());
            } catch (Exception e) {
                System.out.println("Error thrown on model instantiate");
            };
        }
        
        return this.models.get(modelClass);
    }
}

package app.tools;

import app.models.Inventory;

public class Sales {
    
    private final Inventory model = new Inventory();

    public Double quotePrice(String item, int quantity) {
        var info = model.store().get(item);
        if(info != null) {
            double discount = quantity < 10 ? 0 : 0.05;
            return quantity * info.unitPrice() * (1 - discount);
        }
        return null;
    }
}

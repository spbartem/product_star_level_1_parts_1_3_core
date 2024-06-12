package _3_java_core.collection.lesson_4.workshop.warehouse;

import java.util.*;
import java.util.stream.Collectors;

public class BasicAnalytics implements Analytics {
    private Storage storage;

    public BasicAnalytics(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Set<String> getCategories() {
            Set<String> categories = new HashSet<>();
            for (Wheel wheel : storage.getAllItems().values()){
                categories.add(wheel.category());
            }
            return categories;

//            return storage.getAllItems().values().stream().map(item -> item.category()).collect(Collectors.toSet());
        }

        @Override
        public Map<CategoryAndPlace, Integer> getAggregationByCategoryAndPlace() {
            Map<CategoryAndPlace, Integer> aggregations = new HashMap<>();
            Map<String, Wheel> storedItems = storage.getAllItems();
            for (Wheel wheel : storedItems.values()) {
                CategoryAndPlace categoryAndPlace = new CategoryAndPlace(wheel.category(), wheel.place());
                Integer aggregation = aggregations.getOrDefault(categoryAndPlace, 0);
            aggregation += wheel.quantity();
            aggregations.put(categoryAndPlace, aggregation);
        }
        return aggregations;
    }

    @Override
    public Integer getAggregationByCategoryAndPlace(CategoryAndPlace categoryAndPlace) {
        Integer quantity = 0;
        Map<String, Wheel> storedItems = storage.getAllItems();
        for (Wheel wheel : storedItems.values()) {
            if (wheel.category().equals(categoryAndPlace.category()) && wheel.place().equals(categoryAndPlace.place())) {
                quantity += wheel.quantity();
            }
        }
        return quantity;
    }

    @Override
    public Integer getTotalCount() {
        return storage.getAllItems().values().stream().map(wheel -> wheel.quantity()).mapToInt(Integer::intValue).sum();
    }
}

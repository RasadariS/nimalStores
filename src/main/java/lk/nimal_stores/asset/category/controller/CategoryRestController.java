package lk.nimal_stores.asset.category.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lk.nimal_stores.asset.category.entity.Category;
import lk.nimal_stores.asset.category.service.CategoryService;
import lk.nimal_stores.asset.item.entity.enums.MainCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/getCategory/{mainCategory}")
    public MappingJacksonValue getCategoryByMainCategory(@PathVariable String mainCategory) {

        Category category = new Category();

        category.setMainCategory(MainCategory.valueOf(mainCategory));

        //MappingJacksonValue
        List<Category> categories = new ArrayList<>();;
        categoryService.search(category).forEach(x->{
            x.setBrands(categoryService.findById(x.getId()).getBrands());
            categories.add(x);
        });

        //Create new mapping jackson value and set it to which was need to filter
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(categories);

        SimpleBeanPropertyFilter simpleBeanPropertyFilterCategory = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name","brands");

        SimpleBeanPropertyFilter simpleBeanPropertyFilterBrand = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name");



        FilterProvider fitter = new SimpleFilterProvider()
                .addFilter("Category", simpleBeanPropertyFilterCategory)
                .addFilter("Brand", simpleBeanPropertyFilterBrand);

        mappingJacksonValue.setFilters(fitter);
        return mappingJacksonValue;
    }
}


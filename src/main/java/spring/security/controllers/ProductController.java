package spring.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.security.converters.ProductConverter;
import spring.security.dto.ProductDto;
import spring.security.entities.Product;
import spring.security.services.ProductService;
import spring.security.validators.ProductValidator;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductConverter productConverter;
    private final ProductService productService;
    private final ProductValidator productValidator;
    /*
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }*/

    @GetMapping("")
    public String getProducts(Model model) {
        model.addAttribute("allProducts", productService.findAll().stream().map(product -> productConverter.entityToDto(product)));
        return "Products_page";
    }

    @PostMapping("/new")
    public String saveNewProductDto(Model model, @RequestParam String title, @RequestParam Integer price) {
        ProductDto productDto = new ProductDto(null, title, price);
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        productService.save(product);
        model.addAttribute("allProducts", productService.findAll().stream().map(p -> productConverter.entityToDto(p)));
        return "Products_page";
    }

    @PostMapping("/update")
    public String updateProductDto(Model model, @RequestParam Integer id, @RequestParam String title, @RequestParam Integer price) {
        ProductDto productDto = new ProductDto(id, title, price);
        productValidator.validate(productDto);
        productService.update(productDto);
        model.addAttribute("allProducts", productService.findAll().stream().map(p -> productConverter.entityToDto(p)));
        return "Products_page";
    }

    @PostMapping("/delete")
    public String deleteProductById(Model model, @RequestParam Integer id) {
        productService.deleteProductById(id);
        model.addAttribute("allProducts", productService.findAll().stream().map(p -> productConverter.entityToDto(p)));
        return "Products_page";
    }
}           // ниже к ДЗ не относится, оставлено на память )
/*
    @GetMapping("/{id}")        // запрос Json продукта в адресной строке (устарело, теперь передаем Dto)
    public ProductDto getProductById(@PathVariable Integer id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден в базе, id: " + id));
        return productConverter.entityToDto(product);
    }

    @GetMapping("/save/{id}/{title}/{price}")        //добавление продукта через Dto
    public ProductDto saveNewProductDto(@PathVariable Integer id, @PathVariable String title, @PathVariable Integer price) {
        ProductDto productDto = new ProductDto(id, title, price);
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @GetMapping("/update/{title}/{price}")     //обновление продукта через Dto
    public ProductDto updateProductDto(@PathVariable String title, @PathVariable Integer price) {
        ProductDto productDto = new ProductDto(null, title, price);
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }*/

    // добавлено для ДЗ 10
/*
    @GetMapping("/to_cart/{Id}/{Title}/{Price}")
    public void addProductDtoToCart(@PathVariable Integer Id, @PathVariable String Title, @PathVariable Integer Price) {
        ProductDto productDto = new ProductDto(Id, Title, Price);
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        productService.addToCart(product);
    }
    @DeleteMapping("/delete_from_cart/{id}")
    public void deleteProductFromCart(@PathVariable Integer id) {
        productService.deleteProductByIdFromCart(id);
    }
    @GetMapping("/load_cart")
    public List<ProductDto> getAllCart() {
        return productService.getCart().stream().map(productConverter::entityToDto).toList();
    }
}*/

    /*
    @GetMapping("/allJSON")
    @ResponseBody
    public List<Product> allProductsJSON() {
        return  productService.getAll();
    }
    @GetMapping("/new")                  // вывод формы для добавления
    public String showFormPage() {
        return "addProductForm";
    }
    @GetMapping("/priceRange")
    @ResponseBody
    public List<Product> allProductsPriceRange(@RequestParam(defaultValue = "0") Integer min, @RequestParam (defaultValue = "2000000") Integer max) {
        return productService.getAllPriceRange(min, max);
    }
    @GetMapping("/minPrice/{price}")
    public String allProductsOverPrice(Model model, @PathVariable Integer price) {
        model.addAttribute("allProducts", productService.getAllOverPrice(price));
        return "Products_page";
    }
    @GetMapping("/maxPrice/{price}")
    public String allProductsBelowPrice(Model model, @PathVariable Integer price ) {
        model.addAttribute("allProducts", productService.getAllBelowPrice(price));
        return "Products_page";
    }
    @GetMapping("/priceRange/{min}/{max}")
    public String allProductsPriceRange(Model model, @PathVariable Integer min, @PathVariable Integer max) {
        model.addAttribute("allProducts", productService.getAllPriceRange(min, max));
        return "Products_page";
    }*/

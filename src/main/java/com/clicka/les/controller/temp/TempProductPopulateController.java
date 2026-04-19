package com.clicka.les.controller.temp;

import com.clicka.les.entity.product.Product;
import com.clicka.les.entity.product.ProductDetail;
import com.clicka.les.entity.product.Tag;
import com.clicka.les.repository.product.ProductRepository;
import com.clicka.les.repository.product.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempProductPopulateController {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    @PostMapping("/populate-product")
    public String populate() {
        if (productRepository.count() > 0) {
            return "Produtos já foram populados anteriormente!";
        }

        // =========================
        // FONES
        // =========================

        createProduct(
                "Fone Gamer Basic",
                "HEADSET",
                "Imagem-Fone-Basic",
                new BigDecimal("99.90"),
                null,
                "Fone gamer básico com som estéreo",
                List.of(
                        detail("Peso", "350g"),
                        detail("Conexão", "P2"),
                        detail("Som", "Estéreo")
                ),
                Set.of("gamer", "headset", "economico")
        );

        createProduct(
                "Fone Gamer Pro",
                "HEADSET",
                "Imagem-Fone-Pro",
                new BigDecimal("199.90"),
                new BigDecimal("169.90"),
                "Fone gamer com som surround e RGB",
                List.of(
                        detail("Peso", "320g"),
                        detail("Conexão", "USB"),
                        detail("Som", "7.1 Surround")
                ),
                Set.of("gamer", "headset", "padrao")
        );

        createProduct(
                "Fone Gamer Elite",
                "HEADSET",
                "Imagem-Fone-Elite",
                new BigDecimal("399.90"),
                new BigDecimal("349.90"),
                "Fone topo de linha com cancelamento de ruído",
                List.of(
                        detail("Peso", "290g"),
                        detail("Conexão", "USB + Wireless"),
                        detail("Som", "7.1 Premium")
                ),
                Set.of("gamer", "headset", "topo")
        );

        // =========================
        // TECLADOS
        // =========================

        createProduct(
                "Teclado Gamer Basic",
                "KEYBOARD",
                "Imagem-Teclado-Basic",
                new BigDecimal("129.90"),
                null,
                "Teclado gamer de membrana",
                List.of(
                        detail("Switch", "Membrana"),
                        detail("RGB", "Não"),
                        detail("Conexão", "USB")
                ),
                Set.of("gamer", "teclado", "economico")
        );

        createProduct(
                "Teclado Gamer Pro",
                "KEYBOARD",
                "Imagem-Teclado-Pro",
                new BigDecimal("249.90"),
                new BigDecimal("219.90"),
                "Teclado mecânico RGB",
                List.of(
                        detail("Switch", "Blue"),
                        detail("RGB", "Sim"),
                        detail("Conexão", "USB")
                ),
                Set.of("gamer", "teclado", "padrao")
        );

        createProduct(
                "Teclado Gamer Elite",
                "KEYBOARD",
                "Imagem-Teclado-Elite",
                new BigDecimal("499.90"),
                new BigDecimal("459.90"),
                "Teclado mecânico premium com hot swap",
                List.of(
                        detail("Switch", "Hot Swap"),
                        detail("RGB", "ARGB"),
                        detail("Conexão", "USB + Wireless")
                ),
                Set.of("gamer", "teclado", "topo")
        );

        // =========================
        // MOUSES
        // =========================

        createProduct(
                "Mouse Gamer Basic",
                "MOUSE",
                "Imagem-Mouse-Basic",
                new BigDecimal("59.90"),
                null,
                "Mouse gamer básico",
                List.of(
                        detail("DPI", "3200"),
                        detail("Conexão", "USB"),
                        detail("Peso", "120g")
                ),
                Set.of("gamer", "mouse", "economico")
        );

        createProduct(
                "Mouse Gamer Pro",
                "MOUSE",
                "Imagem-Mouse-Pro",
                new BigDecimal("149.90"),
                new BigDecimal("129.90"),
                "Mouse gamer com RGB",
                List.of(
                        detail("DPI", "8000"),
                        detail("Conexão", "USB"),
                        detail("Peso", "95g")
                ),
                Set.of("gamer", "mouse", "padrao")
        );

        createProduct(
                "Mouse Gamer Elite",
                "MOUSE",
                "Imagem-Mouse-Elite",
                new BigDecimal("299.90"),
                new BigDecimal("269.90"),
                "Mouse ultraleve sem fio",
                List.of(
                        detail("DPI", "16000"),
                        detail("Conexão", "Wireless"),
                        detail("Peso", "65g")
                ),
                Set.of("gamer", "mouse", "topo")
        );

        return "Produtos criados!";
    }

    private ProductDetail detail(String attr, String value) {
        ProductDetail d = new ProductDetail();
        d.setAttribute(attr);
        d.setValue(value);
        return d;
    }

    private void createProduct(
            String name,
            String type,
            String image,
            BigDecimal price,
            BigDecimal promo,
            String description,
            List<ProductDetail> details,
            Set<String> tagNames
    ) {
        Product product = new Product();
        product.setName(name);
        product.setType(type);
        product.setImageUrl(image);
        product.setDefaultPrice(price);
        product.setPromotionalPrice(promo);
        product.setDescription(description);

        details.forEach(d -> d.setProduct(product));
        product.setDetails(details);

        Set<Tag> tags = new HashSet<>();
        tagNames.forEach(t -> tags.add(getOrCreateTag(t)));

        product.setTags(tags);

        productRepository.save(product);
    }

    private Tag getOrCreateTag(String name) { return tagRepository.findByNameIgnoreCase(name) .orElseGet(() -> tagRepository.save( Tag.builder().name(name).build() )); }
}
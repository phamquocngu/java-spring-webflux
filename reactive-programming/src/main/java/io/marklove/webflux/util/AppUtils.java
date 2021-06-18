package io.marklove.webflux.util;

import io.marklove.webflux.document.Product;
import io.marklove.webflux.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static Flux<ProductDto> streamProducts() {
        List<ProductDto> prods = new ArrayList<>();
        for(int i=0; i<1000; i++) {
            prods.add(new ProductDto(String.valueOf(i),"product " + i, i, i));
        }
        return Flux.fromIterable(prods)
                .delayUntil(d -> Mono.delay(Duration.ofMillis(500)));
    }
}

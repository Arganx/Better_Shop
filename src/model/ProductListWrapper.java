package model;

/**
 * Created by qwerty on 26-Mar-17.
 */
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
public class ProductListWrapper {



        private List<Product> products;

        @XmlElement(name = "product")
        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
}

interface Product {
    void use();
}

class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductA");
    }
}

class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductB");
    }
}

class GenericFactory<T extends Product> {
    private Class<T> productClass;

    public GenericFactory(Class<T> productClass) {
        this.productClass = productClass;
    }

    public T createProduct() {
        try {
            return productClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create product", e);
        }
    }
}

class Factory {
    public static void main(String[] args) {
        // Create factory for ConcreteProductA
        GenericFactory<ConcreteProductA> factoryA = new GenericFactory<>(ConcreteProductA.class); 
        
        // ConcreteProductA.class. It returns the Class object that represents the specified class name. This is used if you need to get the Class object.
        // This roughly corresponds to .getClass() which returns the Class object that corresponds to the object instance. 
        // You use someclassname.class when you want to work with the Class object and don't have an object instance.
        
        Product productA = factoryA.createProduct();
        productA.use();

        // Create factory for ConcreteProductB
        GenericFactory<ConcreteProductB> factoryB = new GenericFactory<>(ConcreteProductB.class);
        Product productB = factoryB.createProduct();
        productB.use();
    }           
}
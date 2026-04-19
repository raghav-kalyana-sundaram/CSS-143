public class Component {
    
    private String name;
    private String category; 
    private String manufacturer; 
    private double price; 
    private double specification; 
    private String specLabel; 

    /*
    I wanted the component class to be a template for the different type of 
    components that can be used in a PC build. Thus, I wanted to make the class have 
    the different values that all PC components have. I followed what was on the 
    css 143.toobusyforai.com/chase website and picked name, category, manufacturer, 
    price, specification, and specLabel. The overload I chose were for the discounts. When 
    I've gone to PC stores before, they will either have a discount on the price like 20% off
    or a discount like $50 off. I wanted the class to be able to handle both of the types, which is 
    why I chose to overload the setDiscount method.
    */

    /*
    I structured toString() as one line of labeled fields (name, category, manufacturer,
    price, specification, and specLabel) so printing a component reads like a spec sheet so 
    it's easy to read for the user. For the equals method, I needed to check to make sure the other 
    object is not null and is an instance of the Component class. I then checked to make sure 
    the name, category, price, specification, and specLabel were the same. I didn't check the manufacturer because
    especially with PC components, the manufacturer doesn't matter for the component as much as the specs do. 
    */


    public Component(String name, String category, String manufacturer, double price, double specification, String specLabel)
    {
        setName(name);
        setCategory(category);
        setManufacturer(manufacturer);
        setPrice(price);
        setSpecification(specification);
        setSpecLabel(specLabel);
    }

    public Component(String name, String category, String manufacturer, double price)
    {
        setName(name);
        setCategory(category);
        setManufacturer(manufacturer);
        setPrice(price);
        setSpecification(0.0);
        setSpecLabel("");
    }
    

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public double getSpecification() {
        return specification;
    }

    public String getSpecLabel() {
        return specLabel;
    }

    private void setName(String name) 
    {
        if(name != null && name.length() > 0)
        {
            this.name = name; 
        }
        else
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setCategory(String category)
    {
        if(category != null && category.length() > 0)
        {
            this.category = category;
        }
        else
        {
            throw new IllegalArgumentException("Category cannot be empty");
        }
    }

    private void setManufacturer(String manufacturer)
    {
        if(manufacturer != null && manufacturer.length() > 0)
        {
            this.manufacturer = manufacturer;
        }
        else
        {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
    }

    private void setPrice(double price)
    {
        if(price > 0)
        {
            this.price = price;
        }
        else
        {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    private void setSpecification(double specification)
    {
        if(specification >= 0)
        {
            this.specification = specification;
        }
        else
        {
            throw new IllegalArgumentException("Specification cannot be negative");
        }
    }


    private void setSpecLabel(String specLabel)
    {
        if(specLabel != null)
        {
            this.specLabel = specLabel;
        }
        else
        {
            throw new IllegalArgumentException("Specification label cannot be null");
        }
    }

    public void setDiscount(double percentage)
    {
        if(percentage > 0 && percentage <= 100)
        {
            this.price = this.price * (1 - percentage / 100);
        }
        else
        {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
    }

    public void setDiscount(double amount, boolean isPercentage)
    {
        if(isPercentage)
        {
            setDiscount(amount);
        }
        else
        {
            if(amount <= 0)
            {
                throw new IllegalArgumentException("Discount amount must be greater than 0");
            }
            if(this.price - amount <= 0)
            {
                throw new IllegalArgumentException("Discount cannot reduce price to zero or below");
            }
            this.price = this.price - amount;
        }
    }

    @Override
    public String toString()
    {
        return "Component Name: " + name + ", Category: " + category + ", Manufacturer: " + manufacturer + ", Price: " + price + ", Specification: " + specification + " " + specLabel;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(obj instanceof Component)
        {
            Component other = (Component) obj;
            return this.name.equals(other.name) && this.category.equals(other.category) && this.price == other.price && this.specification == other.specification && this.specLabel.equals(other.specLabel);
        }
        return false;
    }

    public int compareTo(Component other)
    {
        return Double.compare(this.price, other.price);
    }
}

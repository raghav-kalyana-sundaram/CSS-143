public class PCBuild {
    
    private String name; 
    private Component cpu; 
    private Component gpu; 
    private Component ram; 
    private Component storage; 
    private String tier;


    /*
    I wanted PCBuild to represent a full system like the builds on the Chaise site, where you pick
    a CPU, GPU, RAM, and storage and give the whole thing a name and tier. So this class holds
    those four Component objects plus a name and tier string, which matches the roster cards on
    the css 143.toobusyforai.com/chase website. I overloaded the constructors because sometimes you already
    know the tier you want, and sometimes you just want a default until you decide. I also overloaded
    compareTo so I can compare two builds by total price, or pick one slot and compare just that
    part against another build, like when you're only looking at GPUs.
    */

    /*
    I structured toString() with a header line for the build name, tier, and total price from
    getPrice(), then a line for each part so it reads like the build summary on the site. Each
    part uses that component's own toString(). For equals(), I return false for null, true if it's
    the same object, then check instanceof PCBuild and compare name, tier, and all four components
    using their equals() methods so two builds match only when every slot and the labels match.
    */

    public PCBuild(String name, Component cpu, Component gpu, Component ram, Component storage, String tier)
    {
        setName(name);
        setCpu(cpu);
        setGpu(gpu);
        setRam(ram);
        setStorage(storage);
        setTier(tier);
    }

    public PCBuild(String name, Component cpu, Component gpu, Component ram, Component storage)
    {
        setName(name);
        setCpu(cpu);
        setGpu(gpu);
        setRam(ram);
        setStorage(storage);
        setTier("Entry");
    }

    public String getName() {
        return name;
    }

    public Component getCpu() {
        return cpu;
    }

    public Component getGpu() {
        return gpu;
    }
    
    public Component getRam() {
        return ram;
    }

    public Component getStorage() {
        return storage;
    }

    public String getTier() {
        return tier;
    }

    private void setName(String name) {
        if(name != null && name.length() > 0)
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setCpu(Component cpu) {
        if(cpu != null)
        {
            this.cpu = cpu;
        }
        else
        {
            throw new IllegalArgumentException("CPU cannot be null");
        }
    }

    private void setGpu(Component gpu) {
        if(gpu != null)
        {
            this.gpu = gpu;
        }
        else
        {
            throw new IllegalArgumentException("GPU cannot be null");
        }
    }

    private void setRam(Component ram) {
        if(ram != null)
        {
            this.ram = ram;
        }
        else
        {
            throw new IllegalArgumentException("RAM cannot be null");
        }
    }

    private void setStorage(Component storage) {
        if(storage != null)
        {
            this.storage = storage;
        }
        else
        {
            throw new IllegalArgumentException("Storage cannot be null");
        }
    }

    private void setTier(String tier) {
        if(tier != null && tier.length() > 0)
        {
            this.tier = tier;
        }
        else
        {
            throw new IllegalArgumentException("Tier cannot be empty");
        }
    }

    public double getPrice()
    {
        return cpu.getPrice() + gpu.getPrice() + ram.getPrice() + storage.getPrice();
    }


    @Override
    public String toString()
    {
        return getName() + "  | Tier: " + getTier() + "  | Price: " + getPrice() + " \n CPU: " + cpu.toString() + " \n GPU: " + gpu.toString() + " \n RAM: " + ram.toString() + " \n Storage: " + storage.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == null)
        {
            return false;
        }
        if(other == this)
        {
            return true;
        }
        if(other instanceof PCBuild)
        {
            PCBuild o = (PCBuild) other;
            return this.name.equals(o.name) && this.tier.equals(o.tier) && this.cpu.equals(o.cpu)
                    && this.gpu.equals(o.gpu) && this.ram.equals(o.ram) && this.storage.equals(o.storage);
        }
        return false;
    }

    public int compareTo(PCBuild other, Component component) 
    {
        if(component == cpu)
        {
            return this.cpu.compareTo(other.cpu);
        }
        else if(component == gpu)
        {
            return this.gpu.compareTo(other.gpu);
        }
        else if(component == ram)
        {
            return this.ram.compareTo(other.ram);
        }
        else if(component == storage)
        {
            return this.storage.compareTo(other.storage);
        }
        else
        {
            throw new IllegalArgumentException("Invalid component");
        }
    }

    public int compareTo(PCBuild other)
    {
        return Double.compare(this.getPrice(), other.getPrice());
    }
}


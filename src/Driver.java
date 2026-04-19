public class Driver {

    public static void main(String[] args) {
        testComponents();
        testPCBuilds();
        testInvalidInputs();
        testBoundaries();
    }

    public static void testComponents() {
        Component cpu = new Component("AMD Ryzen 9 7950X", "CPU", "AMD", 549.99, 16, "Cores");
        Component gpu = new Component("NVIDIA RTX 4090", "GPU", "NVIDIA", 1599.99, 24, "VRAM GB");
        Component ram = new Component("G.Skill Trident Z5 RGB 32GB", "RAM", "G.Skill", 199.99, 32, "GB");
        Component storage = new Component("Samsung 990 PRO 2TB", "Storage", "Samsung", 199.99, 2000, "GB");

        System.out.println(cpu);
        System.out.println(gpu);
        System.out.println(ram);
        System.out.println(storage);
    }

    public static void testPCBuilds() {
        Component cpu = new Component("AMD Ryzen 9 7950X", "CPU", "AMD", 549.99, 16, "Cores");
        Component gpu = new Component("NVIDIA RTX 4090", "GPU", "NVIDIA", 1599.99, 24, "VRAM GB");
        Component ram = new Component("G.Skill Trident Z5 RGB 32GB", "RAM", "G.Skill", 199.99, 32, "GB");
        Component storage = new Component("Samsung 990 PRO 2TB", "Storage", "Samsung", 199.99, 2000, "GB");
        PCBuild zenith = new PCBuild("Zenith V1", cpu, gpu, ram, storage, "Enthusiast");
        System.out.println(zenith);
        System.out.println("Price: " + zenith.getPrice());

        Component cpu2 = new Component("Intel Core i5-13600K", "CPU", "Intel", 299.99, 6, "Cores");
        Component gpu2 = new Component("NVIDIA RTX 3060", "GPU", "NVIDIA", 329.99, 12, "VRAM GB");
        Component ram2 = new Component("Corsair Vengeance 16GB", "RAM", "Corsair", 79.99, 16, "GB");
        Component storage2 = new Component("Kingston 512GB SSD", "Storage", "Kingston", 59.99, 512, "GB");
        PCBuild carbon = new PCBuild("Carbon Stream", cpu2, gpu2, ram2, storage2);
        System.out.println(carbon);
        System.out.println("Default tier: " + carbon.getTier());
        System.out.println("Equals itself: " + carbon.equals(carbon));
        System.out.println("Equals Zenith: " + carbon.equals(zenith));
    }

    public static void testInvalidInputs() {
        try {
            Component bad = new Component("Fake CPU", "CPU", "AMD", -100.0, 8, "Cores");
        } catch (IllegalArgumentException e) {
            System.out.println("Negative price rejected: " + e.getMessage());
        }

        try {
            Component bad = new Component("", "GPU", "NVIDIA", 500.0, 12, "VRAM GB");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty name rejected: " + e.getMessage());
        }

        try {
            Component cpu = new Component("AMD Ryzen 7 7800X3D", "CPU", "AMD", 449.99, 8, "Cores");
            Component ram = new Component("G.Skill 32GB", "RAM", "G.Skill", 139.99, 32, "GB");
            Component storage = new Component("WD Black 2TB", "Storage", "WD", 179.99, 2000, "GB");
            PCBuild bad = new PCBuild("Broken Build", cpu, null, ram, storage, "High-end");
        } catch (IllegalArgumentException e) {
            System.out.println("Null GPU rejected: " + e.getMessage());
        }

        try {
            Component bad = new Component("Bad RAM", "RAM", "Corsair", 99.99, -16, "GB");
        } catch (IllegalArgumentException e) {
            System.out.println("Negative specification rejected: " + e.getMessage());
        }
    }

    public static void testBoundaries() {
        try {
            Component c = new Component("Budget Part", "RAM", "Generic", 0.01, 4, "GB");
            System.out.println("Price 0.01 accepted: " + c.getPrice());
        } catch (IllegalArgumentException e) {
            System.out.println("Price 0.01 rejected: " + e.getMessage());
        }

        try {
            Component c = new Component("Zero Price Part", "RAM", "Generic", 0.0, 4, "GB");
            System.out.println("Price 0.0 accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Price 0.0 rejected: " + e.getMessage());
        }

        try {
            Component c = new Component("Unknown Spec", "Storage", "Generic", 49.99, 0.0, "GB");
            System.out.println("Specification 0.0 accepted: " + c.getSpecification());
        } catch (IllegalArgumentException e) {
            System.out.println("Specification 0.0 rejected: " + e.getMessage());
        }

        try {
            Component c = new Component("Negative Spec", "Storage", "Generic", 49.99, -0.01, "GB");
            System.out.println("Specification -0.01 accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Specification -0.01 rejected: " + e.getMessage());
        }

        try {
            Component c = new Component("GPU", "GPU", "NVIDIA", 1000.0, 16, "VRAM GB");
            c.setDiscount(100.0);
            System.out.println("100% discount accepted (upper boundary)");
        } catch (IllegalArgumentException e) {
            System.out.println("100% discount rejected: " + e.getMessage());
        }

        try {
            Component c = new Component("GPU", "GPU", "NVIDIA", 1000.0, 16, "VRAM GB");
            c.setDiscount(100.1);
            System.out.println("100.1% discount accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("100.1% discount rejected (upper boundary exceeded): " + e.getMessage());
        }
    }
}
